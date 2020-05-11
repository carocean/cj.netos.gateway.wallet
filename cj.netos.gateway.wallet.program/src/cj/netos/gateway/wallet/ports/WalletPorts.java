package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPersonService;
import cj.netos.gateway.wallet.bo.*;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;
import com.rabbitmq.client.AMQP;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjService(name = "/wallet.ports")
public class WalletPorts implements IWalletPorts {
    @CjServiceRef(refByName = "rabbitMQProducer")
    IRabbitMQProducer rabbitMQ;
    @CjServiceRef
    IPersonService personService;
    @CjServiceSite
    IServiceSite site;

    @Override
    public Map<String, Object> rechargeOrder(ISecuritySession securitySession, String currency, long amount, String payChannelID, String note) throws CircuitException {

        if (StringUtil.isEmpty(currency)) {
            currency = "CNY";
        }
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(payChannelID)) {
            throw new CircuitException("404", String.format("支付渠道为空"));
        }
        Map<String, Object> person = (Map<String, Object>) personService.getPersonInfo((String) securitySession.property("accessToken"));
        if (person == null) {
            throw new CircuitException("404", String.format("用户不存在:" + securitySession.principal()));
        }


        RechargeBO rechargeBO = new RechargeBO();
        rechargeBO.setRechargerName((String) person.get("nickName"));
        rechargeBO.setAmount(amount);
        rechargeBO.setAppid((String) securitySession.property("appid"));
        rechargeBO.setCtime(System.currentTimeMillis());
        rechargeBO.setNote(note);
        rechargeBO.setCurrency(currency);
        rechargeBO.setPaymentChannelID(payChannelID);
        rechargeBO.setRecharger(securitySession.principal());
        rechargeBO.setDevice((String) securitySession.property("device"));

        //返回以支付渠道签名的文本
        return callRechargeOrder(rechargeBO);
    }

    protected Map<String, Object> callRechargeOrder(RechargeBO bo) throws CircuitException {
        Map<String, Object> mapargs = new HashMap<>();
        mapargs.put("rechargeBill", bo);
        String text = new Gson().toJson(mapargs);
        RequestBody rb = RequestBody.create(text.getBytes());

        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String portsUrl = site.getProperty("ports.oc.wallet");
        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));
        final Request request = new Request.Builder()
                .url(portsUrl)
                .addHeader("Rest-Command", "rechargeOrder")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .post(rb)
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        map = new Gson().fromJson(json, HashMap.class);
        return map;
    }

    @Override
    public void rechargeDone(ISecuritySession securitySession, String sn, long amount,String code, String message) throws CircuitException {
        if (StringUtil.isEmpty(sn)) {
            throw new CircuitException("404", "订单号为空");
        }
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "rechargeDone");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                        put("sn", sn);
                        put("amount", amount);
                        put("code",code);
                        put("message", message);
                    }
                }).build();
//        byte[] body = new Gson().toJson(rechargeBO).getBytes();
        rabbitMQ.publish(properties, new byte[0]);
    }

    @Override
    public void withdraw(ISecuritySession securitySession, long amount, String paymentChannelPath, String memo) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "withdraw");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                    }
                }).build();

        WithdrawBO withdrawBO = new WithdrawBO();
        withdrawBO.setAmount(amount);
        withdrawBO.setAppid((String) securitySession.property("appid"));
        withdrawBO.setCtime(System.currentTimeMillis());
        withdrawBO.setPaymentChannelPath(paymentChannelPath);
        withdrawBO.setWitchrawer(securitySession.principal());
        byte[] body = new Gson().toJson(withdrawBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

    @Override
    public void payment(ISecuritySession securitySession, long amount, String payee, String type, TradeDetailsBO details) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "payment");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                    }
                }).build();

        PaymentBO paymentBO = new PaymentBO();
        paymentBO.setAmount(amount);
        paymentBO.setAppid((String) securitySession.property("appid"));
        paymentBO.setCtime(System.currentTimeMillis());
        paymentBO.setPayee(payee);
        paymentBO.setPayer(securitySession.principal());
        paymentBO.setDetails(details);
        paymentBO.setType(type);

        byte[] body = new Gson().toJson(paymentBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

    @Override
    public void gathering(ISecuritySession securitySession, long amount, String payer, String type, TradeDetailsBO details) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "gathering");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                    }
                }).build();

        GatheringBO gatheringBO = new GatheringBO();
        gatheringBO.setAmount(amount);
        gatheringBO.setAppid((String) securitySession.property("appid"));
        gatheringBO.setCtime(System.currentTimeMillis());
        gatheringBO.setPayer(payer);
        gatheringBO.setPayee(securitySession.principal());
        gatheringBO.setDetails(details);
        gatheringBO.setType(type);

        byte[] body = new Gson().toJson(gatheringBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

    @Override
    public void transfer(ISecuritySession securitySession, long amount, String payee, TradeDetailsBO details) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "transfer");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                    }
                }).build();

        TransferBO transferBO = new TransferBO();
        transferBO.setAmount(amount);
        transferBO.setAppid((String) securitySession.property("appid"));
        transferBO.setCtime(System.currentTimeMillis());
        transferBO.setPayee(payee);
        transferBO.setPayer(securitySession.principal());
        transferBO.setDetails(details);

        byte[] body = new Gson().toJson(transferBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

    @Override
    public void queryAmount(ISecuritySession securitySession) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "queryAmount");
                        put("device", securitySession.property("device"));
                        put("person", securitySession.principal());
                        put("appid", (String) securitySession.property("appid"));
                    }
                }).build();

        QueryAmountBO queryAmountBO = new QueryAmountBO();
        queryAmountBO.setAppid((String) securitySession.property("appid"));
        queryAmountBO.setCtime(System.currentTimeMillis());
        queryAmountBO.setQueryer(securitySession.principal());

        byte[] body = new Gson().toJson(queryAmountBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

}

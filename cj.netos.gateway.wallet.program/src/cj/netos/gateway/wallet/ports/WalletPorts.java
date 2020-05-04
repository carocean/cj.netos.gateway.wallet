package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.bo.*;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "/wallet.ports")
public class WalletPorts implements IWalletPorts {
    @CjServiceRef(refByName = "rabbitMQProducer")
    IRabbitMQProducer rabbitMQ;

    @Override
    public void recharge(ISecuritySession securitySession, long amount, String paymentChannelPath, String memo) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "recharge");
                        put("device", securitySession.property("device"));
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
                    }
                }).build();

        RechargeBO rechargeBO = new RechargeBO();
        rechargeBO.setAmount(amount);
        rechargeBO.setAppid((String) securitySession.property("appid"));
        rechargeBO.setCtime(System.currentTimeMillis());
        rechargeBO.setMemo(memo);
        rechargeBO.setPaymentChannelPath(paymentChannelPath);
        rechargeBO.setRecharger(securitySession.principal());
        byte[] body = new Gson().toJson(rechargeBO).getBytes();

        rabbitMQ.publish(properties, body);
    }

    @Override
    public void withdraw(ISecuritySession securitySession, long amount, String paymentChannelPath, String memo) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet.ports")
                .headers(new HashMap() {
                    {
                        put("command", "withdraw");
                        put("device", securitySession.property("device"));
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
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
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
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
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
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
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
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
                        put("person",securitySession.principal());
                        put("appid",(String)securitySession.property("appid"));
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

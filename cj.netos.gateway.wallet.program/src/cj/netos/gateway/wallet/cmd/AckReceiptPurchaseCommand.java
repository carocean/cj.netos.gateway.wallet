package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjConsumer(name = "ack")
@CjService(name = "/trade/receipt/ack.mq#purchase")
public class AckReceiptPurchaseCommand implements IConsumerCommand {
    @CjServiceRef
    IRecordService recordService;

    @CjServiceRef
    IServiceSite site;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        OnorderResult result = new Gson().fromJson(new String(body), OnorderResult.class);
        recordService.ackPurchaseRecordOnorder(result);
        if (!"200".equals(result.getStatus())) {
            return;
        }
        //发纹银银行发起承兑
        WenyPurchRecord record = recordService.getPurchaseRecord(result.getSn());
        try {
            call_wenybank_purchase(record);
        } catch (CircuitException e) {
            e.printStackTrace();
        }
    }

    private void call_wenybank_purchase(WenyPurchRecord record) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));
        String portsUrl = site.getProperty("ports.wybank.trade");

        Map<String, Object> content = new HashMap<>();
        content.put("record", record);
        RequestBody requestBody = RequestBody.create(new Gson().toJson(content).getBytes());
        final Request request = new Request.Builder()
                .url(String.format("%s?person=%s", portsUrl, record.getPerson()))
                .addHeader("Rest-Command", "purchase")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .post(requestBody)
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
        String v = (String) map.get("dataText");
    }

}

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "wenyBankTradeCaller")
public class WenyBankTradeCaller implements IWenyBankTradeCaller {
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

    @Override
    public void exchange(WenyExchangeRecord record) throws CircuitException {
        //向纹银银行提交承兑交易(交由oc处理）
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/weny.mq")
                .headers(new HashMap<String, Object>() {{
                    put("command", "exchange");
                    put("person", record.getPerson());
                }})
                .build();
        rabbitMQProducer.publish(properties, new Gson().toJson(record).getBytes());
        //网关通过mq等待command确认
    }

    @Override
    public void purchase(WenyPurchRecord record) throws CircuitException {
        //向纹银银行提交承兑交易(交由oc处理）
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/weny.mq")
                .headers(new HashMap<String, Object>() {{
                    put("command", "purchase");
                    put("person", record.getPerson());
                }})
                .build();
        rabbitMQProducer.publish(properties, new Gson().toJson(record).getBytes());
        //网关通过mq等待command确认
    }
}

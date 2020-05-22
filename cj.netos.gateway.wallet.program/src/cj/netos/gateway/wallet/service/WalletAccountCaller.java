package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IWalletAccountCaller;
import cj.netos.gateway.wallet.bo.OnorderBO;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "walletAccountCaller")
public class WalletAccountCaller implements IWalletAccountCaller {
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;
    @Override
    public void tryPutOnorder(OnorderBO onOrderBO) throws CircuitException {
        //将申购金放到在订单上。包括：检查余额是否足扣；主账号在订单增加；扣减零钱账户余额；返回确认；在确信指令中向纹银银行调起申购
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/onorder.mq")
                .headers(new HashMap<String, Object>() {{
                    put("command", "put");
                    put("person", onOrderBO.getPerson());
                }})
                .build();
        rabbitMQProducer.publish(properties, new Gson().toJson(onOrderBO).getBytes());
    }

    @Override
    public void removeOnorder(OnorderBO onorderBO) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/onorder.mq")
                .headers(new HashMap<String, Object>() {{
                    put("command", "remove");
                    put("person", onorderBO.getPerson());
                }})
                .build();
        rabbitMQProducer.publish(properties, new Gson().toJson(onorderBO).getBytes());
    }
}

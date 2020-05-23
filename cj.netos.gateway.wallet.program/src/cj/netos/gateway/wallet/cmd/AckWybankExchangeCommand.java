package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.IRabbitMQConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

@CjConsumer(name = "ack")
@CjService(name = "/wybank/trade/success#exchange")
public class AckWybankExchangeCommand implements IConsumerCommand {
    @CjServiceRef(refByName = "@.rabbitmq.consumer.wybank")
    IRabbitMQConsumer rabbitMQConsumer;

    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;

    @CjServiceRef
    IRecordService recordService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        System.out.println("---------" + json);

    }

}

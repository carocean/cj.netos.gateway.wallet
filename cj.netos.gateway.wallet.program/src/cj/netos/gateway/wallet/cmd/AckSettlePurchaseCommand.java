package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IPurchaseActivityController;
import cj.netos.gateway.wallet.result.PurchaseResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

@CjConsumer(name = "ack")
@CjService(name = "/trade/settle/ack.mhub#purchase")
public class AckSettlePurchaseCommand implements IConsumerCommand {

    @CjServiceRef
    IPurchaseActivityController purchaseActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        PurchaseResult purchaseResult = new Gson().fromJson(new String(body), PurchaseResult.class);
        purchaseActivityController.ackSettle(purchaseResult);
    }

}

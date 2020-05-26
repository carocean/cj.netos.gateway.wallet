package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IPurchaseActivityController;
import cj.netos.gateway.wallet.result.PurchaseResult;
import cj.netos.gateway.wallet.result.PurchasedResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.LongString;

import java.io.IOException;

@CjConsumer(name = "wybank")
@CjService(name = "/wybank/trade/settle.mq#purchase")//从纹银银行过来
public class BankSettlePurchaseCommand implements IConsumerCommand {

    @CjServiceRef
    IPurchaseActivityController purchaseActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        PurchasedResult result = new Gson().fromJson(json, PurchasedResult.class);

        LongString statusLS = (LongString) properties.getHeaders().get("status");
        LongString messageLS = (LongString) properties.getHeaders().get("message");
        String status = statusLS == null ? "200" : statusLS.toString();
        String message = messageLS == null ? "OK" : messageLS.toString();
        try {
            purchaseActivityController.settle(result, status, message);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

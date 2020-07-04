package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IExchangeActivityController;
import cj.netos.gateway.wallet.IPurchaseActivityController;
import cj.netos.gateway.wallet.result.ExchangedResult;
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

@CjConsumer(name = "fromWybank")
@CjService(name = "/wybank/trade/settle.mq#exchange")//从纹银银行过来
public class BankSettleExchangeCommand implements IConsumerCommand {

    @CjServiceRef
    IExchangeActivityController exchangeActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        ExchangedResult result = new Gson().fromJson(json, ExchangedResult.class);

        LongString statusLS = (LongString) properties.getHeaders().get("status");
        LongString messageLS = (LongString) properties.getHeaders().get("message");
        String status = statusLS == null ? "200" : statusLS.toString();
        String message = messageLS == null ? "OK" : messageLS.toString();
        try {
            exchangeActivityController.settle(result, status, message);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.ITransferShunterActivityController;
import cj.netos.gateway.wallet.result.TransShuntResult;
import cj.netos.gateway.wallet.result.WithdrawShunterResult;
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
@CjService(name = "/wybank/trade/settle.mq#withdraw")//从纹银银行过来
public class BankSettleWithdrawCommand implements IConsumerCommand {

    @CjServiceRef
    ITransferShunterActivityController transferShunterActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        WithdrawShunterResult result = new Gson().fromJson(json, WithdrawShunterResult.class);

        LongString statusLS = (LongString) properties.getHeaders().get("status");
        LongString messageLS = (LongString) properties.getHeaders().get("message");
        String status = statusLS == null ? "200" : statusLS.toString();
        String message = messageLS == null ? "OK" : messageLS.toString();
        try {
            transferShunterActivityController.settle(result, status, message);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

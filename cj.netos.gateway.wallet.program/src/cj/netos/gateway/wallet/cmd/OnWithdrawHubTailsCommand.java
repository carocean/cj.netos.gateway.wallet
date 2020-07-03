package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.AbsorberHubTailsResult;
import cj.netos.gateway.wallet.IDepositHubTailsActivityController;
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

import java.io.IOException;

@CjConsumer(name = "onWithdrawHubTails")
@CjService(name = "/robot/hub.ports#withrawHubTails")//从纹银银行过来
public class OnWithdrawHubTailsCommand implements IConsumerCommand {
    @CjServiceRef
    IDepositHubTailsActivityController depositHubTailsActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        AbsorberHubTailsResult result = new Gson().fromJson(json, AbsorberHubTailsResult.class);
        try {
            depositHubTailsActivityController.doReceipt(result);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IRechargeActivityController;
import cj.netos.gateway.wallet.result.RechargeResult;
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
@CjService(name = "/trade/settle/ack.mhub#recharge")
public class AckSettleRechargeCommand implements IConsumerCommand {

    @CjServiceRef
    IRechargeActivityController rechargeActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        RechargeResult rechargeResult = new Gson().fromJson(new String(body), RechargeResult.class);
        rechargeActivityController.ackSettle(rechargeResult);
    }

}

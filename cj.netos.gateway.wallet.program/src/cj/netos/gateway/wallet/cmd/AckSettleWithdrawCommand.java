package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.result.WithdrawResult;
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
@CjService(name = "/trade/settle/ack.mq#withdraw")
public class AckSettleWithdrawCommand implements IConsumerCommand {
    @CjServiceRef
    IRecordService recordService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        WithdrawResult result = new Gson().fromJson(new String(body), WithdrawResult.class);
        recordService.ackWithdrawRecordSettled(result);
    }

}

package cj.netos.gateway.wallet.service.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.OnorderBO;
import cj.netos.gateway.wallet.result.OnorderResult;
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
@CjService(name = "/trade/receipt/ack.mq#withdraw")
public class AckReceiptWithdrawCommand implements IConsumerCommand {
    @CjServiceRef
    IRecordService recordService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        OnorderResult result = new Gson().fromJson(new String(body), OnorderResult.class);
        recordService.ackWithdrawRecordOnorder(result);
    }

}

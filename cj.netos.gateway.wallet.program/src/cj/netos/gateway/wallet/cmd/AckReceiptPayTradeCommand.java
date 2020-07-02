package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IDepositAbsorbActivityController;
import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.result.DepositAbsorbResult;
import cj.netos.gateway.wallet.result.PayResult;
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
@CjService(name = "/trade/receipt/ack.mhub#payTrade")
public class AckReceiptPayTradeCommand implements IConsumerCommand {
    @CjServiceRef
    IPayActivityController payActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        PayResult result = new Gson().fromJson(new String(body), PayResult.class);
        payActivityController.ackReceipt(result);
    }

}

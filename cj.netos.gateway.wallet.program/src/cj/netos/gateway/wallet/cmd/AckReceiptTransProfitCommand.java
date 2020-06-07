package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.ITransferAbsorbActivityController;
import cj.netos.gateway.wallet.ITransferProfitActivityController;
import cj.netos.gateway.wallet.result.TransAbsorbResult;
import cj.netos.gateway.wallet.result.TransProfitResult;
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
@CjService(name = "/trade/receipt/ack.mhub#transProfit")
public class AckReceiptTransProfitCommand implements IConsumerCommand {
    @CjServiceRef
    ITransferProfitActivityController transferProfitActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        TransProfitResult result = new Gson().fromJson(new String(body), TransProfitResult.class);
        transferProfitActivityController.ackReceipt(result);
    }

}

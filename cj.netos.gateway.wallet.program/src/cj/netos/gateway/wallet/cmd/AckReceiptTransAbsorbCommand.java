package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.ITransferAbsorbActivityController;
import cj.netos.gateway.wallet.result.TransAbsorbResult;
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

@CjConsumer(name = "fromOC_ack_receipt_transAbsorb")
@CjService(name = "/trade/receipt/ack.mhub#transAbsorb")
public class AckReceiptTransAbsorbCommand implements IConsumerCommand {
    @CjServiceRef
    ITransferAbsorbActivityController transferAbsorbActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        TransAbsorbResult result = new Gson().fromJson(new String(body), TransAbsorbResult.class);
        transferAbsorbActivityController.ackReceipt(result);
    }

}

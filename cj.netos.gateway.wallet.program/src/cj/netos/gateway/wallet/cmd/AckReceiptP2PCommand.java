package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IP2PActivityController;
import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.result.P2PResult;
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

@CjConsumer(name = "fromOC_ack_receipt_p2p")
@CjService(name = "/trade/receipt/ack.mhub#p2p")
public class AckReceiptP2PCommand implements IConsumerCommand {
    @CjServiceRef
    IP2PActivityController p2pActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        P2PResult result = new Gson().fromJson(new String(body), P2PResult.class);
        p2pActivityController.ackReceipt(result);
    }

}

package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IDepositTrialFundsActivityController;
import cj.netos.gateway.wallet.result.DepositTrialFundsResult;
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

@CjConsumer(name = "fromOC_ack_receipt_depositTrialFunds")
@CjService(name = "/trade/receipt/ack.mhub#depositTrialFunds")
public class AckReceiptDepositTrialFundsCommand implements IConsumerCommand {
    @CjServiceRef
    IDepositTrialFundsActivityController depositTrialFundsActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        DepositTrialFundsResult result = new Gson().fromJson(new String(body), DepositTrialFundsResult.class);
        depositTrialFundsActivityController.ackReceipt(result);
    }

}

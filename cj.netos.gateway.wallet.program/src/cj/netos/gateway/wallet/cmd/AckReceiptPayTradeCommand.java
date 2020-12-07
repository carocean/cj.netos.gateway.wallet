package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.IDepositTrialFundsActivityController;
import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.result.PayResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

@CjConsumer(name = "fromOC_ack_receipt_payTrade")
@CjService(name = "/trade/receipt/ack.mhub#payTrade")
public class AckReceiptPayTradeCommand implements IConsumerCommand {
    @CjServiceRef
    IPayActivityController payActivityController;
    @CjServiceRef
    IDepositTrialFundsActivityController depositTrialFundsActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json=new String(body);
//        CJSystem.logging().info(getClass(),String.format("body is :\r\n %s",json));

        PayResult result = new Gson().fromJson(json, PayResult.class);
        PayBO bo = payActivityController.ackReceipt(result);

        if ("200".equals(result.getStatus())
                && (bo.getDetails() != null
                && "trialFunds".equals(bo.getDetails().getPayeeType())
        )
        ) {//体验金入账流程
            try {
                depositTrialFundsActivityController.doReceipt(bo);
            } catch (CircuitException e) {
                CJSystem.logging().error(e);
            }
            return;
        }

        try {
            payActivityController.sendPayInfo(result);
        } catch (CircuitException e) {
            CJSystem.logging().error(e);
        }
    }

}

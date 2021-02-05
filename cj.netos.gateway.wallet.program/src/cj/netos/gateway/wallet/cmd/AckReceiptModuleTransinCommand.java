package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IModuleTransInActivityController;
import cj.netos.gateway.wallet.result.ModuleTransinResult;
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

@CjConsumer(name = "fromOC_ack_receipt_module_transin")
@CjService(name = "/trade/receipt/ack.mhub#moduleTransin")
public class AckReceiptModuleTransinCommand implements IConsumerCommand {

    @CjServiceRef(refByName = "moduleTransInActivityController")
    IModuleTransInActivityController moduleTransInActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        ModuleTransinResult result = new Gson().fromJson(new String(body), ModuleTransinResult.class);
        moduleTransInActivityController.ackReceipt(result);
    }

}

package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IDepositTrialFundsActivityController;
import cj.netos.gateway.wallet.IModuleTransInActivityController;
import cj.netos.gateway.wallet.IP2PActivityController;
import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.P2pRecord;
import cj.netos.gateway.wallet.model.TrialFundsConfig;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.util.StringUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.LongString;

import java.io.IOException;
import java.util.Map;

@CjConsumer(name = "on-module-transIn")
@CjService(name = "/wallet/receipt.mhub#transIn")//从洇取机器过来
public class OnModuleTransInCommand implements IConsumerCommand {

    @CjServiceRef(refByName = "moduleTransInActivityController")
    IModuleTransInActivityController moduleTransInActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String moduleId = properties.getHeaders().getOrDefault("module-id", "").toString();
        String moduleTitle = properties.getHeaders().getOrDefault("module-title", "").toString();
        String person = properties.getHeaders().getOrDefault("person", "").toString();
        String nickName = properties.getHeaders().getOrDefault("nick-name", "").toString();
        String payer = properties.getHeaders().getOrDefault("payer", "").toString();
        String payerName = properties.getHeaders().getOrDefault("payer-name", "").toString();
        long amount = (long) properties.getHeaders().getOrDefault("amount", 0L);
        String note = properties.getHeaders().getOrDefault("note", "").toString();
        try {
            moduleTransInActivityController.doReceipt(moduleId, moduleTitle, person, nickName,payer,payerName, amount, note);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.IDepositTrialFundsActivityController;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
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

@CjConsumer(name = "fromRobot_onAddTrialFunds")
@CjService(name = "/robot/hub.ports#addTrialFunds")//从洇取机器过来
public class OnAddTrialFundsCommand implements IConsumerCommand {
    @CjServiceRef
    IDepositTrialFundsActivityController depositTrialFundsActivityController;

    @CjServiceRef
    IPayActivityController payActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        TrialFundsConfig config = depositTrialFundsActivityController.getConfig();
        if (config.getState() == 0 || config.getTrialAmount() == 0 || StringUtil.isEmpty(config.getRemitAccount())) {
            return;
        }

        LongString consumer = (LongString) properties.getHeaders().get("consumer");
        LongString nickName = (LongString) properties.getHeaders().get("nickName");
        LongString qrsliceId = (LongString) properties.getHeaders().get("qrslice-id");
        LongString qrsliceCreator = (LongString) properties.getHeaders().get("qrslice-creator");
        LongString qrsliceCname = (LongString) properties.getHeaders().get("qrslice-cname");

        PayDetailsBO details = new PayDetailsBO();
        details.setNote(String.format("{\"consumer\":\"%s\",\"nickName\":\"%s\"}",consumer==null?"":consumer.toString(),nickName==null?"":nickName.toString()));
        details.setOrderno(qrsliceId == null ? null : qrsliceId.toString());
        details.setOrderTitle(String.format("奖励体验金: %s初次消费了%s的码片", nickName, qrsliceCname));
        details.setPayeeCode(qrsliceCreator == null ? null : qrsliceCreator.toString());
        details.setPayeeName(qrsliceCname == null ? null : qrsliceCname.toString());
        details.setPayeeType("trialFunds");
        details.setServiceid("qrslice");
        details.setServiceName("码片");

        try {
            payActivityController.doReceipt(config.getRemitAccount(), config.getRemitName(), config.getTrialAmount(), 1/*0. qrcode_pay(扫码支付)|1 order_pay(支付订单)*/, details, "体验金划扣");
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

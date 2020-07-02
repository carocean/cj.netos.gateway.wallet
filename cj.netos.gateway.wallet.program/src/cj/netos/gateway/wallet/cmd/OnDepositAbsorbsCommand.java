package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IDepositAbsorbActivityController;
import cj.netos.gateway.wallet.ITransferShunterActivityController;
import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.result.WithdrawShunterResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.LongString;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CjConsumer(name = "onDepositAbsorbs")
@CjService(name = "/robot/hub.ports#distribute")//从纹银银行过来
public class OnDepositAbsorbsCommand implements IConsumerCommand {
    @CjServiceRef
    IDepositAbsorbActivityController depositAbsorbActivityController;

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);

    }

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        HashMap<String, Object> result = new Gson().fromJson(json, HashMap.class);
        Map<String, Object> absorber = (Map<String, Object>) result.get("absorber");
        Map<String, Object> recipients = (Map<String, Object>) result.get("recipients");
        BigDecimal amountFen = new BigDecimal(result.get("amount") + "");//单位为1/10的8次方分，故而可用长整型表示，最大值可容纳9亿2千万，对于一个人的洇金完全足够
        long amount = amountFen.multiply(new BigDecimal("100000000")).longValue();
        String refsn = (String) result.get("refsn");//在洇金机器内的洇取单号
        String sourceCode = (String) absorber.get("id");//源代码指向洇取器的标识
        String sourceTitle = (String) absorber.get("title");//源代码指向洇取器的标题
        String personName = (String) recipients.get("personName");
        String person = (String) recipients.get("person");
        String note = String.format("{\"encourageCode\":\"%s\",\"encourageCause\":\"%s\",\"outTradeSn\":\"%s\"}",
                recipients.get("encourageCode"), recipients.get("encourageCause"), refsn);
        try {
            depositAbsorbActivityController.doReceipt(person, personName, amount, sourceCode, sourceTitle, note);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

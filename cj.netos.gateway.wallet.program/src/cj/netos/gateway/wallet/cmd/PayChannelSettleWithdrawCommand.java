package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IWithdrawActivityController;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CjConsumer(name = "fromPayNotify")
@CjService(name = "/trade/settle.mhub#withdraw")//从纹银银行过来
public class PayChannelSettleWithdrawCommand implements IConsumerCommand {

    @CjServiceRef
    IWithdrawActivityController withdrawActivityController;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        Map<String, Object> settleMap = new Gson().fromJson(json, HashMap.class);
        String status = (String) settleMap.get("status");
        String message = (String) settleMap.get("message");
        String person = (String) settleMap.get("person");
        String record_sn = (String) settleMap.get("record_sn");
        String amount = (String) settleMap.get("amount");
        try {
            withdrawActivityController.settle(person, record_sn, Long.valueOf(amount), status, message);
        } catch (CircuitException e) {
            throw new RabbitMQException(e.getStatus(), e.getMessage());
        }

    }
}

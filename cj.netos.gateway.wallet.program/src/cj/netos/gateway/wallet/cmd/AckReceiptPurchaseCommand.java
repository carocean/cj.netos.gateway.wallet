package cj.netos.gateway.wallet.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
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

@CjConsumer(name = "ack")
@CjService(name = "/trade/receipt/ack.mq#purchase")
public class AckReceiptPurchaseCommand implements IConsumerCommand {
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        OnorderResult result = new Gson().fromJson(new String(body), OnorderResult.class);
        recordService.ackPurchaseRecordOnorder(result);
        if (!"200".equals(result.getStatus())) {
            return;
        }
        //发oc，由oc调起纹银银行发起承兑
        WenyPurchRecord record = recordService.getPurchaseRecord(result.getSn());
        try {
            wenyBankTradeCaller.purchase(record);
        } catch (CircuitException e) {
            throw new RetryCommandException(e.getStatus(), e);
        }
    }

}

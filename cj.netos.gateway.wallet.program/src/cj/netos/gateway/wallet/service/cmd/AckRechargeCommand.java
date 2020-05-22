package cj.netos.gateway.wallet.service.cmd;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.IRabbitMQConsumer;
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
@CjService(name = "/trade/settle/ack.mq#recharge")
public class AckRechargeCommand implements IConsumerCommand {
    @CjServiceRef
    IRecordService recordService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        RechargeResult result = new Gson().fromJson(new String(body), RechargeResult.class);
        recordService.ackRechargeRecord(result);
    }

}

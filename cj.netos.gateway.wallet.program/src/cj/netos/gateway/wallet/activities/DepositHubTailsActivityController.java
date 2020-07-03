package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.DepositAbsorbBO;
import cj.netos.gateway.wallet.bo.DepositHubTailsBO;
import cj.netos.gateway.wallet.model.DepositHubTailsRecord;
import cj.netos.gateway.wallet.result.DepositHubTailsResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "depositHubTailsActivityController")
public class DepositHubTailsActivityController implements IDepositHubTailsActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

    @Override
    public DepositHubTailsRecord doReceipt(AbsorberHubTailsResult result) throws CircuitException {
        DepositHubTailsRecord record = receiptTradeService.depositHubTails(result);
        //发送存入指令
        DepositHubTailsBO depositHubTailsBO = new DepositHubTailsBO();
        depositHubTailsBO.setSn(record.getSn());
        depositHubTailsBO.setCurrency(record.getCurrency());
        depositHubTailsBO.setNote(record.getNote());
        depositHubTailsBO.setPerson(record.getPerson());
        depositHubTailsBO.setPersonName(record.getPersonName());
        depositHubTailsBO.setAmount(record.getAmount());

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "depositHubTails");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(depositHubTailsBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @Override
    public void ackReceipt(DepositHubTailsResult result) {
        recordService.ackDepositHubTails(result);
        CJSystem.logging().info(getClass(),String.format("洇取器尾金已存入：%s %s %s",result.getSn(),result.getStatus(),result.getMessage()));
    }
}

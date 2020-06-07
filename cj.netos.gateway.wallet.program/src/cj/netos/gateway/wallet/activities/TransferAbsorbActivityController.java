package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.ITransferAbsorbActivityController;
import cj.netos.gateway.wallet.bo.TransAbsorbBO;
import cj.netos.gateway.wallet.model.TransAbsorbRecord;
import cj.netos.gateway.wallet.result.TransAbsorbResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "transferAbsorbActivityController")
public class TransferAbsorbActivityController implements ITransferAbsorbActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

    @CjTransaction
    @Override
    public TransAbsorbRecord doReceipt(String principal, String personName,  long amount, String note) throws CircuitException {
        TransAbsorbRecord record = receiptTradeService.transAbsorb(principal, personName, amount, note);

        //发送存入指令
        TransAbsorbBO transAbsorbBO = new TransAbsorbBO();
        transAbsorbBO.setSn(record.getSn());
        transAbsorbBO.setCurrency(record.getCurrency());
        transAbsorbBO.setNote(record.getNote());
        transAbsorbBO.setPerson(principal);
        transAbsorbBO.setPersonName(personName);
        transAbsorbBO.setDemandAmount(amount);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "transAbsorb");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        rabbitMQProducer.publish("oc", properties, new Gson().toJson(transAbsorbBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(TransAbsorbResult result) {
        recordService.ackTransAbsorb(result);
        CJSystem.logging().info(getClass(), String.format("洇金已提取到零钱：%s %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

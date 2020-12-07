package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.DepositAbsorbBO;
import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.result.DepositAbsorbResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.math.BigDecimal;
import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "depositAbsorbActivityController")
public class DepositAbsorbActivityController implements IDepositAbsorbActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef
    IPushToAbsorbChatroom pushToAbsorbChatroom;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_depositAbsorb")
    IRabbitMQProducer toOC_receipt_depositAbsorb;

    @CjTransaction
    @Override
    public DepositAbsorbRecord doReceipt(String principal, String personName, BigDecimal amount, String sourceCode, String sourceTitle, String note) throws CircuitException {
        DepositAbsorbRecord record = receiptTradeService.depositAbsorb(principal, personName, amount, sourceCode, sourceTitle, note);
        //发送存入指令
        DepositAbsorbBO depositAbsorbBO = new DepositAbsorbBO();
        depositAbsorbBO.setSn(record.getSn());
        depositAbsorbBO.setCurrency(record.getCurrency());
        depositAbsorbBO.setNote(record.getNote());
        depositAbsorbBO.setPerson(principal);
        depositAbsorbBO.setPersonName(personName);
        depositAbsorbBO.setDemandAmount(amount);
        depositAbsorbBO.setSourceCode(sourceCode);
        depositAbsorbBO.setSourceTitle(sourceTitle);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "depositAbsorb");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        toOC_receipt_depositAbsorb.publish("oc", properties, new Gson().toJson(depositAbsorbBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(DepositAbsorbResult result) {
        recordService.ackDepositAbsorb(result);
        CJSystem.logging().info(getClass(), String.format("洇金已存入：%s %s %s", result.getSn(), result.getStatus(), result.getMessage()));
        try {
            DepositAbsorbRecord record = recordService.getDepositAbsorbRecordBySn(result.getSn());
            pushToAbsorbChatroom.pushAbsorb(record);
        } catch (Exception e) {
            CJSystem.logging().error(getClass(), String.format("推送洇金通知失败:%s", e));
        }
    }

}

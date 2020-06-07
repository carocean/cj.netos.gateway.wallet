package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.ITransferProfitActivityController;
import cj.netos.gateway.wallet.bo.TransProfitBO;
import cj.netos.gateway.wallet.model.TransProfitRecord;
import cj.netos.gateway.wallet.result.TransProfitResult;
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
@CjService(name = "transferProfitActivityController")
public class TransferProfitActivityController implements ITransferProfitActivityController {
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
    public TransProfitRecord doReceipt(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException {
        TransProfitRecord record = receiptTradeService.transProfit(principal, personName,wenyBankID, amount, note);

        //发送存入指令
        TransProfitBO transProfitBO = new TransProfitBO();
        transProfitBO.setSn(record.getSn());
        transProfitBO.setCurrency(record.getCurrency());
        transProfitBO.setNote(record.getNote());
        transProfitBO.setPerson(principal);
        transProfitBO.setPersonName(personName);
        transProfitBO.setDemandAmount(amount);
        transProfitBO.setWenyBankID(wenyBankID);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "transProfit");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        rabbitMQProducer.publish("oc", properties, new Gson().toJson(transProfitBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(TransProfitResult result) {
        recordService.ackTransProfit(result);
        CJSystem.logging().info(getClass(), String.format("收益已提取到零钱：%s %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

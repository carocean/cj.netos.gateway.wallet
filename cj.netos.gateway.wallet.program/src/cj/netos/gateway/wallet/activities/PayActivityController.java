package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.PayDetails;
import cj.netos.gateway.wallet.model.PayRecord;
import cj.netos.gateway.wallet.result.PayResult;
import cj.netos.gateway.wallet.result.PaymentResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "payActivityController")
public class PayActivityController implements IPayActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_payTrade")
    IRabbitMQProducer toOC_receipt_payTrade;

    @CjServiceRef(refByName = "@.rabbitmq.producer.notifyFinishedPay")
    IRabbitMQProducer notifyFinishedPay;

    @Override
    public PayRecord doReceipt(String principal, String personName, long amount, int type, PayDetailsBO details, String note) throws CircuitException {
        PayRecord record = receiptTradeService.payTrade(principal, personName, amount, type, details, note);

        //发送存入指令
        PayBO payBO = new PayBO();
        payBO.setSn(record.getSn());
        payBO.setCurrency(record.getCurrency());
        payBO.setNote(record.getNote());
        payBO.setPerson(principal);
        payBO.setPersonName(personName);
        payBO.setAmount(amount);
        payBO.setType(type);
        payBO.setDetails(details);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "payTrade");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        toOC_receipt_payTrade.publish("oc", properties, new Gson().toJson(payBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }


    @Override
    public PayBO ackReceipt(PayResult result) {
       return recordService.ackPayTrade(result);
    }

    @Override
    public void sendPayInfo(PayResult result) throws CircuitException {
        PayRecord record = recordService.getPayment(result.getPerson(), result.getSn());
        PayDetails details = recordService.getPayDetails(record.getSn());
        PaymentResult paymentResult = new PaymentResult();
        paymentResult.load(record);
        paymentResult.setDetails(details);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/wallet/trade.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "payTrade");
                    put("payeeType", details.getPayeeType());
                    put("payeeCode", details.getPayeeCode());
                    put("payeeName", details.getPayeeName());
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        notifyFinishedPay.publish(details.getPayeeType(), properties, new Gson().toJson(paymentResult).getBytes());
    }
}

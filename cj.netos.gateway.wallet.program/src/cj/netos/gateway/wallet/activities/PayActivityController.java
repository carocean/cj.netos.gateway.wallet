package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.PayRecord;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "payActivityController")
public class PayActivityController implements IPayActivityController {
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
    public PayRecord doReceipt(String principal, String personName, long amount, int type, PayDetailsBO details, String note) throws CircuitException {
        PayRecord record = receiptTradeService.payable(principal, personName, amount, type, details, note);

        //发送存入指令
        PayBO payBO = new PayBO();
        payBO.setSn(record.getSn());
        payBO.setCurrency(record.getCurrency());
        payBO.setNote(record.getNote());
        payBO.setPerson(principal);
        payBO.setPersonName(personName);
        payBO.setAmount(amount);
        payBO.setType(type);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "payable");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        rabbitMQProducer.publish("oc", properties, new Gson().toJson(payBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @CjTransaction
    @Override
    public void payment(String principal, String payment_sn) throws CircuitException {
        PayRecord record = recordService.getPayment(principal, payment_sn);
        if (record == null) {
            throw new CircuitException("404", "支付单不存在:" + payment_sn);
        }
        if (record.getState() != 0) {
            throw new CircuitException("500", "支付已完成:" + payment_sn);
        }
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "payment");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        PayBO payBO = new PayBO();
        payBO.setSn(record.getSn());
        payBO.setCurrency(record.getCurrency());
        payBO.setNote(record.getNote());
        payBO.setPerson(principal);
        payBO.setPersonName(record.getPersonName());
        payBO.setAmount(record.getAmount());
        payBO.setType(record.getType());
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(payBO).getBytes());
    }

    @CjTransaction
    @Override
    public void refund(String principal, String payment_sn) throws CircuitException {
        PayRecord record = recordService.getPayment(principal, payment_sn);
        if (record == null) {
            throw new CircuitException("404", "支付单不存在:" + payment_sn);
        }
        if (record.getState() != 0) {
            throw new CircuitException("500", "支付已完成:" + payment_sn);
        }
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "refund");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        PayBO payBO = new PayBO();
        payBO.setSn(record.getSn());
        payBO.setCurrency(record.getCurrency());
        payBO.setNote(record.getNote());
        payBO.setPerson(principal);
        payBO.setPersonName(record.getPersonName());
        payBO.setAmount(record.getAmount());
        payBO.setType(record.getType());
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(payBO).getBytes());
    }
}

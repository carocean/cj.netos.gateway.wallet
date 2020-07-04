package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IP2PActivityController;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.P2PBO;
import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.model.P2pRecord;
import cj.netos.gateway.wallet.model.PayRecord;
import cj.netos.gateway.wallet.result.P2PResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "p2pActivityController")
public class P2PActivityController implements IP2PActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_p2p")
    IRabbitMQProducer toOC_receipt_p2p;

    @Override
    public P2pRecord doReceipt(String payer, String payerName, String payee, String payeeName, long amount, int type,String direct, String note) throws CircuitException {
        P2pRecord record = receiptTradeService.p2p(payer, payerName, payee, payeeName, amount, type,direct, note);
        //发送存入指令
        P2PBO p2PBO = new P2PBO();
        p2PBO.setSn(record.getSn());
        p2PBO.setCurrency(record.getCurrency());
        p2PBO.setNote(record.getNote());
        p2PBO.setPayee(payee);
        p2PBO.setPayeeName(payeeName);
        p2PBO.setPayer(payer);
        p2PBO.setPayerName(payerName);
        p2PBO.setAmount(amount);
        p2PBO.setType(type);
        p2PBO.setDirect(direct);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "p2p");
                    put("payer",payer);
                    put("payee", payee);
                    put("record_sn", record.getSn());
                }})
                .build();

        toOC_receipt_p2p.publish("oc", properties, new Gson().toJson(p2PBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @Override
    public void ackReceipt(P2PResult result) {
        recordService.ackP2P(result);
    }
}

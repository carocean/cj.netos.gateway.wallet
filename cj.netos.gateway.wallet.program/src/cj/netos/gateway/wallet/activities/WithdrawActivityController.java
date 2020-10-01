package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.WithdrawBO;
import cj.netos.gateway.wallet.model.WithdrawActivity;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.WithdrawResult;
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
@CjService(name = "withdrawActivityController")
public class WithdrawActivityController implements IWithdrawActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;

    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_withdraw")
    IRabbitMQProducer toOC_receipt_withdraw;

    @CjTransaction
    @Override
    public WithdrawRecord doReceipt(String principal, String personName, long amount, String payChannelID,String personCard, String note) throws CircuitException {
        WithdrawRecord record = receiptTradeService.withdraw(principal, personName, amount, payChannelID,personCard, note);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "withdraw");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        WithdrawBO withdrawBO = WithdrawBO.create(record);
        toOC_receipt_withdraw.publish("oc", properties, new Gson().toJson(withdrawBO).getBytes());
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(WithdrawResult result) {
        recordService.ackWithdrawRecordOnorder(result);
        CJSystem.logging().info(getClass(), String.format("提现单预扣款确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    @CjTransaction
    @Override
    public void settle(String principal, String sn, long amount, String code, String message) throws CircuitException {
        WithdrawRecord record = receiptTradeService.getWithdrawRecord(sn);
        if (record == null) {
            throw new CircuitException("404", "没有下单");
        }
        if (record.getState() > 0) {
            throw new CircuitException("700", "请求被拒绝。订单已完成:" + sn);
        }
        if (record.getStatus() >=300) {
            throw new CircuitException("700", "请求被拒绝。订单已失败:" + sn);
        }
        WithdrawActivity activity = receiptTradeService.getLastWithdrawActivity(sn);
        if (activity.getActivityNo() < 2) {
            throw new CircuitException("700", "请求被拒绝。订单预扣款未完成:" + sn);
        }
        if (!principal.equals(record.getPerson())) {
            throw new CircuitException("800", "非本人订单，拒绝决清");
        }
        settleTradeService.settleWithdraw(record, amount, code, message);
    }

    @CjTransaction
    @Override
    public void ackSettle(WithdrawResult result) {
        recordService.ackWithdrawRecordSettled(result);
        CJSystem.logging().info(getClass(), String.format("提现单决清完成:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.WithdrawBO;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PersonCard;
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
import java.util.Map;

@CjBridge(aspects = "@transaction")
@CjService(name = "withdrawActivityController")
public class WithdrawActivityController implements IWithdrawActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef
    IChannelAccountService channelAccountService;
    @CjServiceRef
    IPersonCardService personCardService;
    @CjServiceRef
    IPayChannelFactory payChannelFactory;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_withdraw")
    IRabbitMQProducer toOC_receipt_withdraw;

    @CjTransaction
    @Override
    public WithdrawRecord doReceipt(String principal, String personName, long amount, ChannelAccount withdrawFromAccount, PersonCard toPersonCard, String note) throws CircuitException {
        WithdrawRecord record = receiptTradeService.withdraw(principal, personName, amount, withdrawFromAccount, toPersonCard, note);
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
    public void ackReceipt(WithdrawResult result) throws CircuitException {
        recordService.ackWithdrawRecordOnorder(result);
        CJSystem.logging().info(getClass(), String.format("提现单预扣款确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
        if ("200".equals(result.getStatus())) {
            deductFromPayChannel(result);
        }
    }

    protected void deductFromPayChannel(WithdrawResult result) throws CircuitException {
        WithdrawRecord record = recordService.getWithdrawRecordBySn(result.getSn());
        ChannelAccount account = channelAccountService.getAccount(record.getPayAccount());
        PersonCard personCard = personCardService.getPersonCardById(record.getPerson(), record.getPersonCard());
        PayChannelTransferResult transferResult = payChannelFactory.transfer(record, account, personCard);
//        CJSystem.logging().info(getClass(),new Gson().toJson(transferResult));
        if ("200".equals(transferResult.getStatus())) {
            CJSystem.logging().info(getClass(), String.format("预扣减渠道成功:%s。结果: %s %s", transferResult.getRecordSn(), transferResult.getStatus(), transferResult.getMessage()));
            recordService.successPreDeductFromPayChannel(transferResult);
            return;
        }
        CJSystem.logging().info(getClass(), String.format("提现单预扣减渠道异常，正在撤销预订单:%s。结果: %s %s", transferResult.getRecordSn(), transferResult.getStatus(), transferResult.getMessage()));
        recordService.cancelPreDeductFromPayChannel(transferResult);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/cancelReceipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "withdraw");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        WithdrawBO withdrawBO = WithdrawBO.create(record);
        toOC_receipt_withdraw.publish("oc", properties, new Gson().toJson(withdrawBO).getBytes());
    }

    @CjTransaction
    @Override
    public void ackCancelReceipt(WithdrawResult result) throws CircuitException {
        recordService.ackCancelPreDeductFromPayChannel(result);
        CJSystem.logging().info(getClass(), String.format("提现单预扣减渠道异常，撤销预订单完成:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
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
        if (record.getStatus() >= 300) {
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

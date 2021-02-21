package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRechargeActivityController;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

@CjBridge(aspects = "@transaction")
@CjService(name = "rechargeActivityController")
public class RechargeActivityController implements IRechargeActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;

    @CjTransaction
    @Override
    public RechargeRecord doReceipt(String principal, String personName, String currency, long amount, PayChannel payChannel,String applyTerminal,String openid, String note) throws CircuitException {
        RechargeRecord record = receiptTradeService.recharge(principal, personName, currency, amount, payChannel,applyTerminal,openid, note);
        return record;
    }

    @CjTransaction
    @Override
    public void settle(String principal, String sn, long amount, String code, String message) throws CircuitException {
        RechargeRecord record = receiptTradeService.getRechargeRecord(sn);
        if (record == null) {
            throw new CircuitException("404", "没有下单");
        }
        if (record.getState() != 0) {
            throw new CircuitException("700", "拒绝订单状态不是待充值的订单");
        }
        if (!principal.equals(record.getPerson())) {
            throw new CircuitException("800", "非本人订单，拒绝决清");
        }
        settleTradeService.settleRecharge(record, amount, code, message);
    }

    @CjTransaction
    @Override
    public void ackSettle(RechargeResult result) {
        recordService.ackRechargeRecord(result);
        CJSystem.logging().info(getClass(), String.format("充值单已确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

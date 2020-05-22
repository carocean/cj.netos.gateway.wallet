package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

@CjService(name = "/trade/settle.ports")
public class SettleTradePorts implements ISettleTradePorts {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ISettleTradeService settleTradeService;

    @Override
    public void recharge(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {
        RechargeRecord record = receiptTradeService.getRechargeRecord(sn);
        if (record == null) {
            throw new CircuitException("404", "没有下单");
        }
        if (record.getState() !=0) {
            throw new CircuitException("700", "拒绝订单状态不是待充值的订单");
        }
        if (!securitySession.principal().equals(record.getPerson())) {
            throw new CircuitException("800", "非本人订单，拒绝决清");
        }
        try {
            settleTradeService.settleRecharge(record, amount, code, message);

        } catch (CircuitException e) {
            throw e;
        }
    }

    @Override
    public void withdraw(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {
        WithdrawRecord record = receiptTradeService.getWithdrawRecord(sn);
        if (record == null) {
            throw new CircuitException("404", "没有下单");
        }
        if (record.getState() >1) {
            throw new CircuitException("700", "请求被拒绝。已决清或已完成");
        }
        if (!securitySession.principal().equals(record.getPerson())) {
            throw new CircuitException("800", "非本人订单，拒绝决清");
        }
        try {
            settleTradeService.settleWithdraw(record, amount, code, message);

        } catch (CircuitException e) {
            throw e;
        }
    }
}

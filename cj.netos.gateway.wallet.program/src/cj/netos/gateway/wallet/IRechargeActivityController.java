package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.PayChannel;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.studio.ecm.net.CircuitException;

public interface IRechargeActivityController {
    RechargeRecord doReceipt(String principal, String personName, String currency, long amount, PayChannel payChannel, String note);

    void settle(String principal,String sn, long amount, String code, String message) throws CircuitException;

    void ackSettle(RechargeResult result);

}

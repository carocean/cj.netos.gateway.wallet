package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.WithdrawResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

public interface IWithdrawActivityController {
    WithdrawRecord doReceipt(String principal, String personName, long amount, String payChannelID, String note) throws CircuitException;

    void settle(String principal, String sn, long amount, String code, String message) throws CircuitException;


    void ackReceipt(WithdrawResult result);

    void ackSettle(WithdrawResult result);
}

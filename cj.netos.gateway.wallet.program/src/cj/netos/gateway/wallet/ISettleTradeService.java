package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.PurchasedResult;
import cj.studio.ecm.net.CircuitException;

public interface ISettleTradeService {
    void settleRecharge(RechargeRecord rechargeRecord, long amount, String code, String message) throws CircuitException;

    void settleWithdraw(WithdrawRecord withdrawRecord, long amount, String code, String message) throws CircuitException;

    void settlePurchased(PurchasedResult result, String status, String message) throws CircuitException;

}
package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.*;

public interface IRecordService {
    WenyPurchRecord getPurchaseRecord(String sn);


    void ackRechargeRecord(RechargeResult result);


    void ackWithdrawRecordOnorder(WithdrawResult result);


    void ackWithdrawRecordSettled(WithdrawResult result);

    void ackPurchasing(PurchaseResult result);

    void ackPurchased(PurchasedBO purchasedBO, String status, String message);


    void ackPurchasedDone(PurchasedBO purchasedBO, String status, String message);

}

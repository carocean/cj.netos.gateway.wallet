package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.*;
import cj.studio.orm.mybatis.annotation.CjTransaction;

public interface IRecordService {
    WenyPurchRecord getPurchaseRecord(String sn);


    void ackRechargeRecord(RechargeResult result);


    void ackWithdrawRecordOnorder(WithdrawResult result);


    void ackWithdrawRecordSettled(WithdrawResult result);

    void ackPurchasing(PurchaseResult result);

    void ackPurchased(PurchasedResult result, String status, String message);


}

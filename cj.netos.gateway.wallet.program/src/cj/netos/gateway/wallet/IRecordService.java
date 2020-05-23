package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.netos.gateway.wallet.result.WithdrawResult;

public interface IRecordService {
    WenyPurchRecord getPurchaseRecord(String sn);

    void ackPutonorder(OnorderResult result);

    void ackRechargeRecord(RechargeResult result);

    void ackWithdrawRecordSettled(WithdrawResult result);

    void ackWithdrawRecordOnorder(OnorderResult result);

    void ackPurchaseRecordOnorder(OnorderResult result);

}

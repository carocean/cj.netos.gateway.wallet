package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.*;

import java.util.List;

public interface IRecordService {
    WenyPurchRecord getPurchaseRecord(String sn);


    void ackRechargeRecord(RechargeResult result);


    void ackWithdrawRecordOnorder(WithdrawResult result);


    void ackWithdrawRecordSettled(WithdrawResult result);

    void ackPurchasing(PurchaseResult result);

    void ackPurchased(PurchasedBO purchasedBO, String status, String message);


    void ackPurchasedDone(PurchasedBO purchasedBO, String status, String message);

    void ackExchange(ExchangingResult result);

    void ackExchangedDone(ExchangingResult result, String status, String message);

    RechargeRecord getRechargeRecord(String principal, String record_sn);

    WithdrawRecord getWithdrawRecord(String principal, String record_sn);

    WenyPurchRecord getPurchaseRecordOfPerson(String principal, String record_sn);

    WenyExchangeRecord getExchangeRecord(String principal, String record_sn);

    List<RechargeRecord> pageRechargeRecord(String principal, int limit, long offset);

    List<WithdrawRecord> pageWithdrawRecord(String principal, int limit, long offset);

    List<WenyPurchRecord> pagePurchaseRecord(String principal, int limit, long offset);

    List<WenyExchangeRecord> pageExchangeRecord(String principal, int limit, long offset);

}

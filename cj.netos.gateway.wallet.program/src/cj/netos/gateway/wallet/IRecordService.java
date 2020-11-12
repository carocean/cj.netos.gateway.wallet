package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.bo.WithdrawShunterBO;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.*;
import cj.studio.orm.mybatis.annotation.CjTransaction;

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

    List<WenyPurchRecord> pagePurchaseRecord(String principal, String wenyBankID, int limit, long offset);

    List<WenyExchangeRecord> pageExchangeRecord(String principal, String wenyBankID, int limit, long offset);

    List<WenyPurchRecord> pagePurchaseRecordOfUnexchanged(String principal, String wenyBankID, int limit, long offset);

    WenyExchangeRecord getExchangeRecordByPurchase(String principal, String purchase_sn);

    List<WenyPurchActivity> getPurchaseActivities(String principal, String record_sn);

    List<WenyExchangeActivity> getExchangeActivities(String principal, String record_sn);

    List<WenyPurchRecord> pagePurchaseRecordOfExchanged(String principal, String wenyBankID, int limit, long offset);

    List<RechargeActivity> getRechargeActivities(String principal, String record_sn);

    List<WithdrawActivity> getWithdrawActivities(String principal, String record_sn);

    PayRecord getPayment(String principal, String payment_sn);

    PayDetails getPayDetails(String payment_sn);

    List<PayActivity> getPayActivities(String record_sn);

    TransProfitRecord getTransProfitRecord(String principal, String record_sn);

    List<TransProfitActivity> getTransProfitActivities(String principal, String record_sn);

    TransAbsorbRecord getTransAbsorbRecord(String principal, String record_sn);

    List<TransAbsorbActivity> getTransAbsorbActivities(String principal, String record_sn);

    DepositAbsorbRecord getDepositAbsorbRecord(String principal, String record_sn);

    @CjTransaction
    DepositAbsorbRecord getDepositAbsorbRecordBySn(String record_sn);

    List<DepositAbsorbActivity> getDepositAbsorbActivities(String principal, String record_sn);

    void ackDepositAbsorb(DepositAbsorbResult result);

    void ackTransAbsorb(TransAbsorbResult result);

    void ackTransProfit(TransProfitResult result);


    void ackTransShunterReceipt(TransShuntResult result);

    void ackTransShuntFromBank(WithdrawShunterBO withdrawShunterBO, String status, String message);

    void ackTransShunterDone(WithdrawShunterBO record, String status, String message);

    void ackPayTrade(PayResult result);

    void ackP2P(P2PResult result);

    void ackDepositHubTails(DepositHubTailsResult result);

    P2pRecord getP2PRecord(String principal, String record_sn);

    List<P2pActivity> getP2PActivities(String record_sn);

    DepositHubTailsRecord getDepositHubTailsRecord(String principal, String record_sn);

    List<DepositHubTailsActivity> getDepositHubTailsActivities(String record_sn);

    TransShunterRecord getTransShunterRecord(String principal, String record_sn);

    List<TransShunterActivity> getTransShunterActivities(String principal, String record_sn);

    String genEvidence(String principal, String nickName, String actor, long expire, long useTimes);

    @CjTransaction
    long countEvidences(String principal, String actor);

    P2pEvidence getEvidence(String evidence);

    @CjTransaction
    void removeEvidence(String evidence);

    long totalP2pEvidenceUsedTimesByPayer(String sn, String payer, String payee);

    WithdrawRecord getWithdrawRecordBySn(String sn);

    void successPreDeductFromPayChannel(PayChannelTransferResult transferResult);

    void cancelPreDeductFromPayChannel(PayChannelTransferResult transferResult);

    void ackCancelPreDeductFromPayChannel(WithdrawResult result);

}

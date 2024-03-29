package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.PurchaseResult;
import cj.netos.gateway.wallet.result.PurchasedResult;
import cj.studio.ecm.net.CircuitException;

public interface IPurchaseActivityController {
    WenyPurchRecord doReceipt(String principal, String personName, String wenyBankID, long amount, String outTradeType, String outTradeSn, String note) throws CircuitException;


    WenyPurchRecord doReceipt2(String principal, String personName, String wenyBankID, long amount, String outTradeType, String outTradeSn, String note) throws CircuitException;

    void ackReceipt(PurchaseResult result);

    void settle(PurchasedResult result, String status, String message) throws CircuitException;


    void ackSettle(PurchaseResult purchaseResult);


}

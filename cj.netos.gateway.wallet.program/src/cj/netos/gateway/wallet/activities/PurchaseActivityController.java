package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.PurchaseResult;
import cj.netos.gateway.wallet.result.PurchasedResult;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjBridge(aspects = "@transaction")
@CjService(name = "purchaseActivityController")
public class PurchaseActivityController implements IPurchaseActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;

    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef
    ISettleTradeService settleTradeService;

    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;

    @CjTransaction
    @Override
    public WenyPurchRecord doReceipt(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException {
        WenyPurchRecord record = receiptTradeService.purchaseWeny(principal, personName, wenyBankID, amount, note);
        wenyBankTradeCaller.purchase(record);
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(PurchaseResult result) {
        recordService.ackPurchasing(result);
        CJSystem.logging().info(getClass(), String.format("申购已回单:%s（回单快过决清也属正常）。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    //从纹银银行来的决清
    @CjTransaction
    @Override
    public void settle(PurchasedResult result, String status, String message) throws CircuitException {
        recordService.ackPurchased(result, status, message);
        settleTradeService.settlePurchased(result, status, message);
        CJSystem.logging().info(getClass(), String.format("申购已决清。%s<-%s", result.getOutTradeSn(), result.getSn()));
    }

}

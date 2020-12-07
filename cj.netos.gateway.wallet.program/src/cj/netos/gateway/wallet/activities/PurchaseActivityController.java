package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.PurchaseBO;
import cj.netos.gateway.wallet.bo.PurchasedBO;
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

    //从零钱扣
    @CjTransaction
    @Override
    public WenyPurchRecord doReceipt(String principal, String personName, String wenyBankID, long amount,String outTradeType, String outTradeSn, String note) throws CircuitException {
        WenyPurchRecord record = receiptTradeService.purchaseWeny(principal, personName, wenyBankID, amount,outTradeType,outTradeSn,0, note);
        wenyBankTradeCaller.purchase(record);
        return record;
    }
    //从体验金扣
    @Override
    @CjTransaction
    public WenyPurchRecord doReceipt2(String principal, String personName, String wenyBankID, long amount, String outTradeType, String outTradeSn, String note) throws CircuitException {
        WenyPurchRecord record = receiptTradeService.purchaseWeny(principal, personName, wenyBankID, amount,outTradeType,outTradeSn,1, note);
        wenyBankTradeCaller.purchase2(record);
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
        PurchasedBO purchasedBO = PurchasedBO.create(result);
        recordService.ackPurchased(purchasedBO, status, message);
        settleTradeService.settlePurchased(purchasedBO, status, message);
        CJSystem.logging().info(getClass(), String.format("申购已收到决清指令。%s<-%s", purchasedBO.getSn(), purchasedBO.getBankPurchSn()));
    }

    @CjTransaction
    @Override
    public void ackSettle(PurchaseResult result) {
        PurchasedBO record=new Gson().fromJson((String)result.getRecord(),PurchasedBO.class);
        recordService.ackPurchasedDone(record,result.getStatus(),result.getMessage());
        CJSystem.logging().info(getClass(), String.format("申购已决清。%s<-%s", record.getSn(), record.getBankPurchSn()));
    }
}

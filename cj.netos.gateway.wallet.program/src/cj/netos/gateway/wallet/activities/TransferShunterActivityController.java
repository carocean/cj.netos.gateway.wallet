package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.WithdrawShunterBO;
import cj.netos.gateway.wallet.model.TransShunterRecord;
import cj.netos.gateway.wallet.result.TransShuntResult;
import cj.netos.gateway.wallet.result.WithdrawShunterResult;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjBridge(aspects = "@transaction")
@CjService(name = "transferShunterActivityController")
public class TransferShunterActivityController implements ITransferShunterActivityController {
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
    public TransShunterRecord doReceipt(String principal, String personName, String wenyBankID, String shunter, long amount, String note) throws CircuitException {
        TransShunterRecord record = receiptTradeService.transShunter(principal, personName, wenyBankID, shunter, amount, note);
        wenyBankTradeCaller.transShunter(record);
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(TransShuntResult result) {
        recordService.ackTransShunterReceipt(result);
        CJSystem.logging().info(getClass(), String.format("转账账金已回单:%s（回单快过决清也属正常）。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    @CjTransaction
    @Override
    public void settle(WithdrawShunterResult result, String status, String message) throws CircuitException {
        WithdrawShunterBO withdrawShunterBO = WithdrawShunterBO.create(result);
        recordService.ackTransShuntFromBank(withdrawShunterBO, status, message);
        settleTradeService.settleTransShunter(withdrawShunterBO, status, message);
        CJSystem.logging().info(getClass(), String.format("转账账金已收到决清指令。%s<-%s", withdrawShunterBO.getSn(), withdrawShunterBO.getOutTradeSn()));
    }

    @CjTransaction
    @Override
    public void ackSettle(TransShuntResult result) {
        WithdrawShunterBO record = new Gson().fromJson((String) result.getRecord(), WithdrawShunterBO.class);
        recordService.ackTransShunterDone(record, result.getStatus(), result.getMessage());
        CJSystem.logging().info(getClass(), String.format("转账账金已决清。%s<-%s", record.getSn(), record.getOutTradeSn()));
    }
}

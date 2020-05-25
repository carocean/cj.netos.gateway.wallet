package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IExchangeActivityController;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

@CjBridge(aspects = "@transaction")
@CjService(name = "exchangeActivityController")
public class ExchangeActivityController implements IExchangeActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;

    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;

    @CjTransaction
    @Override
    public WenyExchangeRecord doReceipt(String principal, String personName, String purchase_sn, String note) throws CircuitException {
        WenyPurchRecord purchRecord = receiptTradeService.getPurchaseRecord(purchase_sn);
        if (purchRecord == null) {
            throw new CircuitException("404", String.format("申购单不存在：%s", purchase_sn));
        }
        if (!principal.equals(purchRecord.getPerson())) {
            throw new CircuitException("800", String.format("非本人申购单，拒绝承兑.申购单号：%s", purchase_sn));
        }
        if (purchRecord.getState() != 1) {
            throw new CircuitException("2000", String.format("申购单未完成或有错.申购单号：%s", purchase_sn));
        }
        WenyExchangeRecord record = receiptTradeService.exchangeWeny(principal, personName, purchRecord, note);
        wenyBankTradeCaller.exchange(record);
        return record;
    }
}

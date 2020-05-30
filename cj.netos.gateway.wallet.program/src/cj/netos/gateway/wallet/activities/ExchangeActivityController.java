package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.ExchangedBO;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.ExchangedResult;
import cj.netos.gateway.wallet.result.ExchangingResult;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "exchangeActivityController")
public class ExchangeActivityController implements IExchangeActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;

    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;
    @CjServiceRef
    ISettleTradeService settleTradeService;
    @CjServiceRef
    IRecordService recordService;

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
        CJSystem.logging().info(getClass(), String.format("承兑已收单，单号：%s，原申购金额：%s，承兑量：%s", record.getSn(), purchRecord.getPurchAmount(), record.getStock()));
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(ExchangingResult result) {
        recordService.ackExchange(result);
        CJSystem.logging().info(getClass(), String.format("承兑已回单，单号：%s，%s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    @CjTransaction
    @Override
    public void settle(ExchangedResult result, String status, String message) throws CircuitException {
        settleTradeService.settleExchange(result, status, message);
        CJSystem.logging().info(getClass(), String.format("承兑收到决清指令，单号：%s<-%s %s %s", result.getOutTradeSn(), result.getSn(), status, message));
    }

    @CjTransaction
    @Override
    public void ackSettle(ExchangingResult result) {
        recordService.ackExchangedDone(result, result.getStatus(), result.getMessage());
        CJSystem.logging().info(getClass(), String.format("承兑已决清。%s %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

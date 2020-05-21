package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.IPersonService;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.ExchangeWenyResult;
import cj.netos.gateway.wallet.result.PurchaseWenyResult;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.netos.gateway.wallet.result.WithdrawResult;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;

import java.util.Map;

@CjService(name = "/trade/receipt.ports")
public class ReceiptTradePorts implements IReceiptTradePorts {

    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    IPersonService personService;
    @CjServiceRef
    IPayChannelService payChannelService;

    @Override
    public RechargeResult recharge(ISecuritySession securitySession, String currency, long amount, String payChannelID, String note) throws CircuitException {
        if (StringUtil.isEmpty(currency)) {
            currency = "CNY";
        }
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(payChannelID)) {
            throw new CircuitException("404", String.format("支付渠道为空"));
        }
        PayChannel payChannel = payChannelService.getPayChannel(payChannelID);
        if (payChannel == null) {
            throw new CircuitException("404", String.format("未知的支付渠道"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        RechargeRecord record = receiptTradeService.recharge(securitySession.principal(), personName, currency, amount, payChannel, note);
        return new Gson().fromJson(new Gson().toJson(record), RechargeResult.class);
    }

    @Override
    public WithdrawResult withdraw(ISecuritySession securitySession, long amount, String payChannelID, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(payChannelID)) {
            throw new CircuitException("404", String.format("支付渠道为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        WithdrawRecord record = receiptTradeService.withdraw(securitySession.principal(), personName, amount, payChannelID, note);
        return new Gson().fromJson(new Gson().toJson(record), WithdrawResult.class);
    }

    @Override
    public PurchaseWenyResult purchaseWeny(ISecuritySession securitySession, String wenyBankID, long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(wenyBankID)) {
            throw new CircuitException("404", String.format("纹银银行行号为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        WenyPurchRecord record = receiptTradeService.purchaseWeny(securitySession.principal(), personName, wenyBankID, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), PurchaseWenyResult.class);
    }

    @Override
    public ExchangeWenyResult exchangeWeny(ISecuritySession securitySession, String purchase_sn, String note) throws CircuitException {
        if (StringUtil.isEmpty(purchase_sn)) {
            throw new CircuitException("404", String.format("申购单号为空"));
        }
        WenyPurchRecord purchRecord = receiptTradeService.getPurchaseRecord(purchase_sn);
        if (purchRecord == null) {
            throw new CircuitException("404", String.format("申购单不存在：%s", purchase_sn));
        }
        if (!securitySession.principal().equals(purchRecord.getPerson())) {
            throw new CircuitException("800", String.format("非本人申购单，拒绝承兑.申购单号：%s", purchase_sn));
        }
        if (purchRecord.getState() != 1) {
            throw new CircuitException("2000", String.format("申购单未完成或有错.申购单号：%s", purchase_sn));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        WenyExchangeRecord record = receiptTradeService.exchangeWeny(securitySession.principal(), personName, purchRecord, note);
        return new Gson().fromJson(new Gson().toJson(record), ExchangeWenyResult.class);
    }
}

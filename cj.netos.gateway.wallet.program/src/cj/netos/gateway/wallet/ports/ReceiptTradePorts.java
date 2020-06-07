package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.*;
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
    IPersonService personService;
    @CjServiceRef
    IPayChannelService payChannelService;
    @CjServiceRef
    IPurchaseActivityController purchaseActivityController;

    @CjServiceRef
    IExchangeActivityController exchangeActivityController;

    @CjServiceRef
    IRechargeActivityController rechargeActivityController;

    @CjServiceRef
    IWithdrawActivityController withdrawActivityController;
    @CjServiceRef
    ITransferProfitActivityController transferProfitActivityController;
    @CjServiceRef
    ITransferAbsorbActivityController transferAbsorbActivityController;
    @CjServiceRef
    IDepositAbsorbActivityController depositAbsorbActivityController;
    @CjServiceRef
    IPayActivityController payActivityController;
    @CjServiceRef
    IRecordService recordService;

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
        RechargeRecord record = rechargeActivityController.doReceipt(securitySession.principal(), personName, currency, amount, payChannel, note);
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
        WithdrawRecord record = withdrawActivityController.doReceipt(securitySession.principal(), personName, amount, payChannelID, note);
        return new Gson().fromJson(new Gson().toJson(record), WithdrawResult.class);
    }

    @Override
    public TransferProfitResult transferProfit(ISecuritySession securitySession,String wenyBankID,  long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(wenyBankID)) {
            throw new CircuitException("404", String.format("行号为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        TransProfitRecord record = transferProfitActivityController.doReceipt(securitySession.principal(), personName,wenyBankID, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), TransferProfitResult.class);
    }

    @Override
    public TransferAbsorbResult transferAbsorb(ISecuritySession securitySession,long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }

        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        TransAbsorbRecord record = transferAbsorbActivityController.doReceipt(securitySession.principal(), personName, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), TransferAbsorbResult.class);
    }

    @Override
    public DepositAbsorbResult depositAbsorb(ISecuritySession securitySession, long amount, String sourceCode, String sourceTitle, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(sourceCode)) {
            throw new CircuitException("404", String.format("洇金来源代码为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        DepositAbsorbRecord record = depositAbsorbActivityController.doReceipt(securitySession.principal(), personName, amount, sourceCode, sourceTitle, note);
        return new Gson().fromJson(new Gson().toJson(record), DepositAbsorbResult.class);
    }

    @Override
    public PayableResult payable(ISecuritySession securitySession, long amount, String payee, int type, PayDetailsBO details, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(payee)) {
            throw new CircuitException("404", String.format("收款人为空"));
        }

        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        PayRecord record = payActivityController.doReceipt(securitySession.principal(), personName, amount, type, details, note);
        return new Gson().fromJson(new Gson().toJson(record), PayableResult.class);
    }

    @Override
    public PurchasingResult purchaseWeny(ISecuritySession securitySession, String wenyBankID, long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(wenyBankID)) {
            throw new CircuitException("404", String.format("纹银银行行号为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        WenyPurchRecord record = purchaseActivityController.doReceipt(securitySession.principal(), personName, wenyBankID, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), PurchasingResult.class);
    }

    @Override
    public ExchangedResult exchangeWeny(ISecuritySession securitySession, String purchase_sn, String note) throws CircuitException {
        if (StringUtil.isEmpty(purchase_sn)) {
            throw new CircuitException("404", String.format("申购单号为空"));
        }
        WenyPurchRecord exists = recordService.getPurchaseRecord(purchase_sn);
        if (exists == null) {
            throw new CircuitException("404", String.format("原申购单不存在:%s", purchase_sn));
        }
        if (!securitySession.principal().equals(exists.getPerson())) {
            throw new CircuitException("404", String.format("不能承兑他人的申购单:%s", purchase_sn));
        }
        if (exists.getState() == 0) {
            throw new CircuitException("500", String.format("原申购未完成:%s", purchase_sn));
        }
        if (exists.getExchangeState() != null && exists.getExchangeState() == 1) {
            throw new CircuitException("500", String.format("原申购正在承兑:%s", purchase_sn));
        }
        if (exists.getExchangeState() != null && exists.getExchangeState() == 2) {
            throw new CircuitException("500", String.format("原申购已承兑:%s", purchase_sn));
        }
        if (exists.getState() != 1 && exists.getStatus() >= 300) {
            throw new CircuitException("500", String.format("原申购出错:%s", purchase_sn));
        }
        WenyExchangeRecord record = exchangeActivityController.doReceipt(exists.getPerson(), exists.getPersonName(), purchase_sn, note);
        return new Gson().fromJson(new Gson().toJson(record), ExchangedResult.class);
    }

    @Override
    public ExchangedResult exchangeWenyOfPerson(ISecuritySession securitySession, String purchase_sn, String note) throws CircuitException {
        if (StringUtil.isEmpty(purchase_sn)) {
            throw new CircuitException("404", String.format("申购单号为空"));
        }
        WenyPurchRecord exists = recordService.getPurchaseRecord(purchase_sn);
        if (exists == null) {
            throw new CircuitException("404", String.format("原申购单不存在:%s", purchase_sn));
        }
        if (!"system.netos".equals(securitySession.principal())) {
            throw new CircuitException("800", String.format("无权承兑:%s", purchase_sn));
        }
        if (exists.getState() == 0) {
            throw new CircuitException("500", String.format("原申购未完成:%s", purchase_sn));
        }
        if (exists.getExchangeState() != null && exists.getExchangeState() == 1) {
            throw new CircuitException("500", String.format("原申购正在承兑:%s", purchase_sn));
        }
        if (exists.getExchangeState() != null && exists.getExchangeState() == 2) {
            throw new CircuitException("500", String.format("原申购已承兑:%s", purchase_sn));
        }
        if (exists.getState() != 1 && exists.getStatus() >= 300) {
            throw new CircuitException("500", String.format("原申购出错:%s", purchase_sn));
        }
        WenyExchangeRecord record = exchangeActivityController.doReceipt(exists.getPerson(), exists.getPersonName(), purchase_sn, note);
        return new Gson().fromJson(new Gson().toJson(record), ExchangedResult.class);
    }
}

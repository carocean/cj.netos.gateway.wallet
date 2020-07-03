package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.*;
import cj.netos.gateway.wallet.util.JwtUtil;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;
import io.jsonwebtoken.Claims;

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
    ITransferShunterActivityController transferShunterActivityController;
    @CjServiceRef
    ITransferAbsorbActivityController transferAbsorbActivityController;
    @CjServiceRef
    IDepositAbsorbActivityController depositAbsorbActivityController;
    @CjServiceRef
    IPayActivityController payActivityController;

    @CjServiceRef(refByName = "p2pActivityController")
    IP2PActivityController p2pActivityController;

    @CjServiceRef
    IRecordService recordService;

    @CjServiceSite
    IServiceSite site;

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
    public TransferProfitResult transferProfit(ISecuritySession securitySession, String wenyBankID, long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(wenyBankID)) {
            throw new CircuitException("404", String.format("行号为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        TransProfitRecord record = transferProfitActivityController.doReceipt(securitySession.principal(), personName, wenyBankID, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), TransferProfitResult.class);
    }

    @Override
    public TransShuntResult transferShunter(ISecuritySession securitySession, String wenyBankID, String shunter, long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(wenyBankID)) {
            throw new CircuitException("404", String.format("行号为空"));
        }
        if (StringUtil.isEmpty(shunter)) {
            throw new CircuitException("404", String.format("账金账户为空"));
        }
        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");

        TransShunterRecord record = transferShunterActivityController.doReceipt(securitySession.principal(), personName, wenyBankID, shunter, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), TransShuntResult.class);
    }

    @Override
    public TransferAbsorbResult transferAbsorb(ISecuritySession securitySession, long amount, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }

        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        TransAbsorbRecord record = transferAbsorbActivityController.doReceipt(securitySession.principal(), personName, amount, note);
        return new Gson().fromJson(new Gson().toJson(record), TransferAbsorbResult.class);
    }

    @Override
    public PaymentResult payTrade(ISecuritySession securitySession, long amount, int type, PayDetailsBO details, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (details == null) {
            throw new CircuitException("404", String.format("付款明细为空"));
        }

        Map<String, Object> personInfo = personService.getPersonInfo((String) securitySession.property("accessToken"));
        String personName = (String) personInfo.get("nickName");
        PayRecord record = payActivityController.doReceipt(securitySession.principal(), personName, amount, type, details, note);
        PayDetails details1 = recordService.getPayDetails(record.getSn());
        PaymentResult result = new PaymentResult();
        result.load(record);
        result.setDetails(details1);
        return result;
    }

    @Override
    public P2PResult transTo(ISecuritySession securitySession, long amount, String payee, int type, String note) throws CircuitException {
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        if (StringUtil.isEmpty(payee)) {
            throw new CircuitException("404", String.format("收款人为空"));
        }

        Map<String, Object> payeeObj = personService.findPerson(payee, (String) securitySession.property("accessToken"));
        P2pRecord record = p2pActivityController.doReceipt(securitySession.principal(), (String) securitySession.property("nickName"), (String) payee, (String) payeeObj.get("nickName"), amount, type, "to", note);
        return new Gson().fromJson(new Gson().toJson(record), P2PResult.class);
    }

    @Override
    public P2PResult transFrom(ISecuritySession securitySession, String payerSignText, int type, String note) throws CircuitException {
        if (StringUtil.isEmpty(payerSignText)) {
            throw new CircuitException("404", String.format("payerSignText为空"));
        }
        String key = site.getProperty("payer.key");
        Claims claims = JwtUtil.parseJWT(key, payerSignText);
        String amountStr = (String) claims.get("amount");
        long amount = Long.valueOf(amountStr);
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        P2pRecord record = p2pActivityController.doReceipt((String) claims.get("payer"), (String) claims.get("name"), securitySession.principal(), (String) securitySession.property("nickName"), amount, type, "from", note);
        return new Gson().fromJson(new Gson().toJson(record), P2PResult.class);
    }

    @Override
    public String genPayerSignText(ISecuritySession securitySession, long amount) throws CircuitException {
        String key = site.getProperty("payer.key");
        String expired = site.getProperty("payer.expired");
        String jwt = JwtUtil.createJWT(key, Long.valueOf(expired), securitySession.principal(), (String) securitySession.property("nickName"), amount);
        return jwt;
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

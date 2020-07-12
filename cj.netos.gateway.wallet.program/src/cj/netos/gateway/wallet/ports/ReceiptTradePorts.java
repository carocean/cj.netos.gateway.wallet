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
        P2pRecord record = p2pActivityController.doReceipt(securitySession.principal(), (String) securitySession.property("nickName"), (String) payee, (String) payeeObj.get("nickName"), amount, type, "to", null, note);
        return new Gson().fromJson(new Gson().toJson(record), P2PResult.class);
    }

    @Override
    public P2pEvidence checkEvidence(ISecuritySession securitySession, String evidence) throws CircuitException {
        if (StringUtil.isEmpty(evidence)) {
            throw new CircuitException("404", String.format("收款凭证参数为空"));
        }
        P2pEvidence p2pEvidence = recordService.getEvidence(evidence);
        if (p2pEvidence.getExpire() != 0 && System.currentTimeMillis() - p2pEvidence.getPubTime() >= p2pEvidence.getExpire()) {
            throw new CircuitException("501", "凭证已过期");
        }
        return p2pEvidence;
    }

    @Override
    public String genReceivableEvidence(ISecuritySession securitySession, long expire, long useTimes) throws CircuitException {
        return recordService.genEvidence(securitySession.principal(), (String) securitySession.property("nickName"), "payee", expire, useTimes);
    }

    @Override
    public String genPayableEvidence(ISecuritySession securitySession, long expire, long useTimes) throws CircuitException {
        return recordService.genEvidence(securitySession.principal(), (String) securitySession.property("nickName"), "payer", expire, useTimes);
    }

    @Override
    public P2PResult payToEvidence(ISecuritySession securitySession, String evidence, long amount, int type, String note) throws CircuitException {
        if (StringUtil.isEmpty(evidence)) {
            throw new CircuitException("404", String.format("收款凭证参数为空"));
        }
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        P2pEvidence p2pEvidence = recordService.getEvidence(evidence);
        if (p2pEvidence == null) {
            throw new CircuitException("404", String.format("凭证不存在"));
        }
        if (!"payee".equals(p2pEvidence.getActor())) {
            throw new CircuitException("500", "不是收款凭证");
        }
        if (p2pEvidence.getExpire() != 0 && System.currentTimeMillis() - p2pEvidence.getPubTime() >= p2pEvidence.getExpire()) {
            throw new CircuitException("501", "收款凭证已过期");
        }
        long usedTimes = recordService.totalP2pEvidenceUsedTimesByPayer(p2pEvidence.getSn(), securitySession.principal(), p2pEvidence.getPrincipal());
        if (p2pEvidence.getUseTimes() != 0 && usedTimes > p2pEvidence.getUseTimes() - 1) {
            throw new CircuitException("502", "收款凭证已超过使用次数");
        }
        P2pRecord record = p2pActivityController.doReceipt(securitySession.principal(), (String) securitySession.property("nickName"), p2pEvidence.getPrincipal(), p2pEvidence.getNickName(), amount, type, "to", evidence, note);
        return new Gson().fromJson(new Gson().toJson(record), P2PResult.class);
    }

    @Override
    public P2PResult receiveFromEvidence(ISecuritySession securitySession, String evidence, long amount, int type, String note) throws CircuitException {
        if (StringUtil.isEmpty(evidence)) {
            throw new CircuitException("404", String.format("付款凭证参数为空"));
        }
        if (amount < 0) {
            throw new CircuitException("500", "金额为负数");
        }
        P2pEvidence p2pEvidence = recordService.getEvidence(evidence);
        if (p2pEvidence == null) {
            throw new CircuitException("404", String.format("凭证不存在"));
        }
        if (!"payer".equals(p2pEvidence.getActor())) {
            throw new CircuitException("500", "不是付款凭证");
        }
        if (p2pEvidence.getExpire() != 0 && System.currentTimeMillis() - p2pEvidence.getPubTime() >= p2pEvidence.getExpire()) {
            throw new CircuitException("501", "付款凭证已过期");
        }
        long usedTimes = recordService.totalP2pEvidenceUsedTimesByPayer(p2pEvidence.getSn(), p2pEvidence.getPrincipal(), securitySession.principal());
        if (p2pEvidence.getUseTimes() != 0 && usedTimes > p2pEvidence.getUseTimes() - 1) {
            throw new CircuitException("502", "付款凭证已超过使用次数");
        }
        P2pRecord record = p2pActivityController.doReceipt(p2pEvidence.getPrincipal(), p2pEvidence.getNickName(), securitySession.principal(), (String) securitySession.property("nickName"), amount, type, "from", evidence, note);

        return new Gson().fromJson(new Gson().toJson(record), P2PResult.class);
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

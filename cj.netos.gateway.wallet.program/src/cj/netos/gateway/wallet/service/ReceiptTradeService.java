package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.AbsorberHubTailsResult;
import cj.netos.gateway.wallet.IChannelAccountSelector;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.mapper.*;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;

@CjBridge(aspects = "@transaction")
@CjService(name = "receiptTradeService")
public class ReceiptTradeService implements IReceiptTradeService {

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeRecordMapper")
    RechargeRecordMapper rechargeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyPurchRecordMapper")
    WenyPurchRecordMapper wenyPurchRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeRecordMapper")
    WenyExchangeRecordMapper wenyExchangeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeActivityMapper")
    RechargeActivityMapper rechargeActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawActivityMapper")
    WithdrawActivityMapper withdrawActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyPurchActivityMapper")
    WenyPurchActivityMapper wenyPurchActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeActivityMapper")
    WenyExchangeActivityMapper wenyExchangeActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositAbsorbRecordMapper")
    DepositAbsorbRecordMapper depositAbsorbRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositAbsorbActivityMapper")
    DepositAbsorbActivityMapper depositAbsorbActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransAbsorbRecordMapper")
    TransAbsorbRecordMapper transAbsorbRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransAbsorbActivityMapper")
    TransAbsorbActivityMapper transAbsorbActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransProfitRecordMapper")
    TransProfitRecordMapper transProfitRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransProfitActivityMapper")
    TransProfitActivityMapper transProfitActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransShunterRecordMapper")
    TransShunterRecordMapper transShunterRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransShunterActivityMapper")
    TransShunterActivityMapper transShunterActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayRecordMapper")
    PayRecordMapper payRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayActivityMapper")
    PayActivityMapper payActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayDetailsMapper")
    PayDetailsMapper payDetailsMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.P2pRecordMapper")
    P2pRecordMapper p2pRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.P2pActivityMapper")
    P2pActivityMapper p2pActivityMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositHubTailsRecordMapper")
    DepositHubTailsRecordMapper depositHubTailsRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositHubTailsActivityMapper")
    DepositHubTailsActivityMapper depositHubTailsActivityMapper;
    @CjServiceRef
    IChannelAccountSelector channelAccountSelector;

    @CjTransaction
    @Override
    public RechargeRecord recharge(String principal, String personName, String currency, long amount, PayChannel payChannel, String note) throws CircuitException {
        ChannelAccount channelAccount = channelAccountSelector.selectSmallestAccount(payChannel);
        if (channelAccount == null) {
            throw new CircuitException("404", String.format("没有选中渠道账户在支付渠道:%s下", payChannel.getName()));
        }
        RechargeRecord record = new RechargeRecord();
        record.setDemandAmount(amount);
        record.setCurrency(currency);
        record.setPayChannel(payChannel.getCode());
        record.setPayAccount(null);
        record.setToChannelAccount(channelAccount.getId());
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        rechargeRecordMapper.insert(record);

        RechargeActivity rechargeActivity = new RechargeActivity();
        rechargeActivity.setActivityName("已收单");
        rechargeActivity.setActivityNo(0);
        rechargeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        rechargeActivity.setId(new IdWorker().nextId());
        rechargeActivity.setMessage(record.getMessage());
        rechargeActivity.setRecordSn(record.getSn());
        rechargeActivity.setStatus(record.getStatus());
        rechargeActivityMapper.insert(rechargeActivity);
        return record;
    }

    @CjTransaction
    @Override
    public WithdrawRecord withdraw(String principal, String personName, long amount, ChannelAccount channelAccount, PersonCard personCard, String note) throws CircuitException {
        WithdrawRecord record = new WithdrawRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setPayChannel(channelAccount.getChannel());
        record.setPayAccount(channelAccount.getId());
        record.setPersonCard(personCard.getId());
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        withdrawRecordMapper.insert(record);
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("已收单");
        withdrawActivity.setActivityNo(0);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(record.getMessage());
        withdrawActivity.setStatus(record.getStatus());
        withdrawActivity.setRecordSn(record.getSn());
        withdrawActivityMapper.insert(withdrawActivity);
        return record;
    }

    @CjTransaction
    @Override
    public DepositAbsorbRecord depositAbsorb(String principal, String personName, BigDecimal amount, String sourceCode, String sourceTitle, String note) {
        DepositAbsorbRecord record = new DepositAbsorbRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setSourceCode(sourceCode);
        record.setSourceTitle(sourceTitle);
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        depositAbsorbRecordMapper.insert(record);

        DepositAbsorbActivity depositAbsorbActivity = new DepositAbsorbActivity();
        depositAbsorbActivity.setActivityName("已收单");
        depositAbsorbActivity.setActivityNo(0);
        depositAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        depositAbsorbActivity.setId(new IdWorker().nextId());
        depositAbsorbActivity.setMessage(record.getMessage());
        depositAbsorbActivity.setStatus(record.getStatus());
        depositAbsorbActivity.setRecordSn(record.getSn());
        depositAbsorbActivityMapper.insert(depositAbsorbActivity);
        return record;
    }

    @CjTransaction
    @Override
    public TransAbsorbRecord transAbsorb(String principal, String personName, long amount, String note) {
        TransAbsorbRecord record = new TransAbsorbRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        transAbsorbRecordMapper.insert(record);

        TransAbsorbActivity transAbsorbActivity = new TransAbsorbActivity();
        transAbsorbActivity.setActivityName("已收单");
        transAbsorbActivity.setActivityNo(0);
        transAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transAbsorbActivity.setId(new IdWorker().nextId());
        transAbsorbActivity.setMessage(record.getMessage());
        transAbsorbActivity.setStatus(record.getStatus());
        transAbsorbActivity.setRecordSn(record.getSn());
        transAbsorbActivityMapper.insert(transAbsorbActivity);
        return record;
    }

    @CjTransaction
    @Override
    public TransProfitRecord transProfit(String principal, String personName, String wenyBankID, long amount, String note) {
        TransProfitRecord record = new TransProfitRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setBankid(wenyBankID);
        transProfitRecordMapper.insert(record);

        TransProfitActivity transProfitActivity = new TransProfitActivity();
        transProfitActivity.setActivityName("已收单");
        transProfitActivity.setActivityNo(0);
        transProfitActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transProfitActivity.setId(new IdWorker().nextId());
        transProfitActivity.setMessage(record.getMessage());
        transProfitActivity.setStatus(record.getStatus());
        transProfitActivity.setRecordSn(record.getSn());
        transProfitActivityMapper.insert(transProfitActivity);
        return record;
    }

    @CjTransaction
    @Override
    public PayRecord payTrade(String principal, String personName, long amount, int type, PayDetailsBO details, String note) {
        PayRecord record = new PayRecord();
        record.setAmount(amount);
        record.setCurrency("CNY");
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setType(type);
        payRecordMapper.insert(record);

        PayDetails payDetails = new PayDetails();
        payDetails.setId(new IdWorker().nextId());
        payDetails.setPayeeCode(details.getPayeeCode());
        payDetails.setPayeeName(details.getPayeeName());
        payDetails.setPayeeType(details.getPayeeType());
        payDetails.setNote(details.getNote());
        payDetails.setOrderNo(details.getOrderno());
        payDetails.setOrderTitle(details.getOrderTitle());
        payDetails.setPaySn(record.getSn());
        payDetails.setServiceId(details.getServiceid());
        payDetails.setServiceName(details.getServiceName());
        payDetailsMapper.insert(payDetails);

        PayActivity payActivity = new PayActivity();
        payActivity.setActivityName("已收单");
        payActivity.setActivityNo(0);
        payActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        payActivity.setId(new IdWorker().nextId());
        payActivity.setMessage(record.getMessage());
        payActivity.setStatus(record.getStatus());
        payActivity.setRecordSn(record.getSn());
        payActivityMapper.insert(payActivity);

        return record;
    }

    @CjTransaction
    @Override
    public WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String outTradeType, String outTradeSn, String note) throws CircuitException {
        WenyPurchRecord record = new WenyPurchRecord();
        record.setPurchAmount(amount);
        record.setCurrency("CNY");
        record.setBankid(wenyBankID);
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setExchangeState(0);
        record.setOutTradeSn(outTradeSn);
        record.setOutTradeType(outTradeType);
        wenyPurchRecordMapper.insert(record);

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已收单");
        wenyPurchActivity.setActivityNo(0);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(record.getMessage());
        wenyPurchActivity.setStatus(200);
        wenyPurchActivity.setRecordSn(record.getSn());
        wenyPurchActivityMapper.insert(wenyPurchActivity);
        return record;
    }

    @CjTransaction
    @Override
    public TransShunterRecord transShunter(String principal, String personName, String wenyBankID, String shunter, long amount, String note) {
        TransShunterRecord record = new TransShunterRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setBankid(wenyBankID);
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setShunter(shunter);
        transShunterRecordMapper.insert(record);

        TransShunterActivity transShunterActivity = new TransShunterActivity();
        transShunterActivity.setActivityName("已收单");
        transShunterActivity.setActivityNo(0);
        transShunterActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transShunterActivity.setId(new IdWorker().nextId());
        transShunterActivity.setMessage(record.getMessage());
        transShunterActivity.setStatus(200);
        transShunterActivity.setRecordSn(record.getSn());
        transShunterActivityMapper.insert(transShunterActivity);
        return record;
    }

    @CjTransaction
    @Override
    public WenyExchangeRecord exchangeWeny(String principal, String personName, WenyPurchRecord purchRecord, String note) throws CircuitException {

        WenyExchangeRecord record = new WenyExchangeRecord();
        record.setPurchAmount(purchRecord.getPurchAmount());
        record.setCurrency("WENY");
        record.setBankid(purchRecord.getBankid());
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setRefsn(purchRecord.getSn());
        record.setSn(new IdWorker().nextId());
        record.setStock(purchRecord.getStock());
        record.setStatus(200);
        record.setMessage("ok");
        record.setBankPurchNo(purchRecord.getBankPurchSn());
        wenyExchangeRecordMapper.insert(record);
        wenyPurchRecordMapper.exchanging(record.getRefsn(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("已收单");
        wenyExchangeActivity.setActivityNo(0);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(record.getMessage());
        wenyExchangeActivity.setStatus(200);
        wenyExchangeActivity.setRecordSn(record.getSn());
        wenyExchangeActivityMapper.insert(wenyExchangeActivity);
        return record;
    }

    @CjTransaction
    @Override
    public P2pRecord p2p(String payer, String payerName, String payee, String payeeName, long amount, int type, String direct, String evidence, String note) {
        P2pRecord record = new P2pRecord();
        record.setAmount(amount);
        record.setCurrency("CNY");
        record.setPayer(payer);
        record.setPayerName(payerName);
        record.setPayee(payee);
        record.setPayeeName(payeeName);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setType(type);
        record.setDirect(direct);
        record.setEvidence(evidence);

        p2pRecordMapper.insert(record);


        P2pActivity p2pActivity = new P2pActivity();
        p2pActivity.setActivityName("已收单");
        p2pActivity.setActivityNo(0);
        p2pActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        p2pActivity.setId(new IdWorker().nextId());
        p2pActivity.setMessage(record.getMessage());
        p2pActivity.setStatus(record.getStatus());
        p2pActivity.setRecordSn(record.getSn());
        p2pActivityMapper.insert(p2pActivity);

        return record;
    }

    @CjTransaction
    @Override
    public DepositHubTailsRecord depositHubTails(AbsorberHubTailsResult result) {
        DepositHubTailsRecord record = new DepositHubTailsRecord();
        record.setAmount(result.getAmount());
        record.setCurrency("CNY");
        record.setPerson(result.getPerson());
        record.setPersonName(result.getPersonName());
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setNote(result.getNote());
        record.setSn(new IdWorker().nextId());
        record.setStatus(200);
        record.setMessage("ok");
        record.setBankid(result.getBankid());

        depositHubTailsRecordMapper.insert(record);


        DepositHubTailsActivity tailsActivity = new DepositHubTailsActivity();
        tailsActivity.setActivityName("已收单");
        tailsActivity.setActivityNo(0);
        tailsActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        tailsActivity.setId(new IdWorker().nextId());
        tailsActivity.setMessage(record.getMessage());
        tailsActivity.setStatus(record.getStatus());
        tailsActivity.setRecordSn(record.getSn());
        depositHubTailsActivityMapper.insert(tailsActivity);

        return record;
    }

    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecord(String purchase_sn) {
        return wenyPurchRecordMapper.selectByPrimaryKey(purchase_sn);
    }

    @CjTransaction
    @Override
    public RechargeRecord getRechargeRecord(String sn) {
        return rechargeRecordMapper.selectByPrimaryKey(sn);
    }

    @CjTransaction
    @Override
    public WithdrawRecord getWithdrawRecord(String sn) {
        return withdrawRecordMapper.selectByPrimaryKey(sn);
    }

    @CjTransaction
    @Override
    public WithdrawActivity getLastWithdrawActivity(String sn) {
        return withdrawActivityMapper.getLastWithdrawActivity(sn);
    }
}

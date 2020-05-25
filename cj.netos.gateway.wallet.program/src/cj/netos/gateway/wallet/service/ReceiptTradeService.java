package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.mapper.*;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

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

    @CjTransaction
    @Override
    public RechargeRecord recharge(String principal, String personName, String currency, long amount, PayChannel payChannel, String note) {
        RechargeRecord record = new RechargeRecord();
        record.setDemandAmount(amount);
        record.setCurrency(currency);
        record.setFromChannel(payChannel.getCode());
        record.setChannelName(payChannel.getChannelName());
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
    public WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note) throws CircuitException {
        WithdrawRecord record = new WithdrawRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setToChannel(payChannelID);
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
    public WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException {
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
        record.setQuatities(purchRecord.getStock());
        record.setStatus(200);
        record.setMessage("ok");
        wenyExchangeRecordMapper.insert(record);

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

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.mapper.RechargeRecordMapper;
import cj.netos.gateway.wallet.mapper.WenyExchangeRecordMapper;
import cj.netos.gateway.wallet.mapper.WenyPurchRecordMapper;
import cj.netos.gateway.wallet.mapper.WithdrawRecordMapper;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
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
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        rechargeRecordMapper.insert(record);
        return record;
    }

    @CjTransaction
    @Override
    public WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note) {
        WithdrawRecord record = new WithdrawRecord();
        record.setDemandAmount(amount);
        record.setCurrency("CNY");
        record.setToChannel(payChannelID);
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        withdrawRecordMapper.insert(record);
        return record;
    }

    @CjTransaction
    @Override
    public WenyPurchRecord purchaseWeny(String principal, String personName, String wenyBankID, long amount, String note) {
        WenyPurchRecord record = new WenyPurchRecord();
        record.setPurchAmount(amount);
        record.setCurrency("CNY");
        record.setBankid(wenyBankID);
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        wenyPurchRecordMapper.insert(record);
        return record;
    }

    @CjTransaction
    @Override
    public WenyExchangeRecord exchangeWeny(String principal, String personName, WenyPurchRecord purchRecord, String note) {
        WenyExchangeRecord record = new WenyExchangeRecord();
        record.setPurchAmount(purchRecord.getPurchAmount());
        record.setCurrency("CNY");
        record.setBankid(purchRecord.getBankid());
        record.setPerson(principal);
        record.setState(0);
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setRefsn(purchRecord.getSn());
        record.setSn(new IdWorker().nextId());
        record.setQuatities(purchRecord.getQuatities());
        wenyExchangeRecordMapper.insert(record);
        return record;
    }

    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecord(String purchase_sn) {
        return wenyPurchRecordMapper.selectByPrimaryKey(purchase_sn);
    }
}

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IWalletAccountCaller;
import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.bo.OnorderBO;
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

    @CjServiceRef
    IWalletAccountCaller walletAccountCaller;

    @CjServiceRef
    IWenyBankTradeCaller wenyBankTradeCaller;


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
    public WithdrawRecord withdraw(String principal, String personName, long amount, String payChannelID, String note) throws CircuitException {
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

        //异步调oc
        OnorderBO onOrderBO = new OnorderBO();
        onOrderBO.setPersonName(record.getPersonName());
        onOrderBO.setAmount(amount);
        onOrderBO.setNote(note);
        onOrderBO.setRefType("withdrawRecord");
        onOrderBO.setPerson(principal);
        onOrderBO.setRefsn(record.getSn());
        onOrderBO.setOrder(2);
        onOrderBO.setCause("预扣款");
        walletAccountCaller.tryPutOnorder(onOrderBO);
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
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setSn(new IdWorker().nextId());
        wenyPurchRecordMapper.insert(record);
        //异步调oc
        OnorderBO onOrderBO = new OnorderBO();
        onOrderBO.setPersonName(record.getPersonName());
        onOrderBO.setRefType("purchaseRecord");
        onOrderBO.setAmount(amount);
        onOrderBO.setNote(note);
        onOrderBO.setPerson(principal);
        onOrderBO.setRefsn(record.getSn());
        onOrderBO.setOrder(8);
        onOrderBO.setCause("预扣款");
        walletAccountCaller.tryPutOnorder(onOrderBO);
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
        record.setCtime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setLutime(WalletUtils.dateTimeToSecond(System.currentTimeMillis()));
        record.setPersonName(personName);
        record.setNote(note);
        record.setRefsn(purchRecord.getSn());
        record.setSn(new IdWorker().nextId());
        record.setQuatities(purchRecord.getQuatities());
        wenyExchangeRecordMapper.insert(record);
        wenyBankTradeCaller.exchange(record);
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
}

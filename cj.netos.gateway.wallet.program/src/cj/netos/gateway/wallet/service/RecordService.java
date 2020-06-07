package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.DepositAbsorbBO;
import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.bo.TransAbsorbBO;
import cj.netos.gateway.wallet.mapper.*;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.*;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "recordService")
public class RecordService implements IRecordService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeRecordMapper")
    RechargeRecordMapper rechargeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyPurchRecordMapper")
    WenyPurchRecordMapper wenyPurchRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeActivityMapper")
    RechargeActivityMapper rechargeActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawActivityMapper")
    WithdrawActivityMapper withdrawActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyPurchActivityMapper")
    WenyPurchActivityMapper wenyPurchActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeActivityMapper")
    WenyExchangeActivityMapper wenyExchangeActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeRecordMapper")
    WenyExchangeRecordMapper wenyExchangeRecordMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayRecordMapper")
    PayRecordMapper payRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayActivityMapper")
    PayActivityMapper payActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayDetailsMapper")
    PayDetailsMapper payDetailsMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransProfitRecordMapper")
    TransProfitRecordMapper transProfitRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransProfitActivityMapper")
    TransProfitActivityMapper transProfitActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransAbsorbRecordMapper")
    TransAbsorbRecordMapper transAbsorbRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransAbsorbActivityMapper")
    TransAbsorbActivityMapper transAbsorbActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositAbsorbRecordMapper")
    DepositAbsorbRecordMapper depositAbsorbRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositAbsorbActivityMapper")
    DepositAbsorbActivityMapper depositAbsorbActivityMapper;

    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecord(String sn) {
        return wenyPurchRecordMapper.selectByPrimaryKey(sn);
    }

    @CjTransaction
    @Override
    public void ackRechargeRecord(RechargeResult result) {
        rechargeRecordMapper.done(result.getSn(), Integer.valueOf(result.getStatus()), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        RechargeActivity rechargeActivity = new RechargeActivity();
        rechargeActivity.setActivityName("已决清");
        rechargeActivity.setActivityNo(2);
        rechargeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        rechargeActivity.setId(new IdWorker().nextId());
        rechargeActivity.setMessage(result.getMessage());
        rechargeActivity.setRecordSn(result.getSn());
        rechargeActivity.setStatus(Integer.valueOf(result.getStatus()));
        rechargeActivityMapper.insert(rechargeActivity);

    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordOnorder(WithdrawResult result) {
        withdrawRecordMapper.update(result.getSn(), Integer.valueOf(result.getStatus()), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("预扣款完成");
        withdrawActivity.setActivityNo(2);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(result.getMessage());
        withdrawActivity.setRecordSn(result.getSn());
        withdrawActivity.setStatus(Integer.valueOf(result.getStatus()));
        withdrawActivityMapper.insert(withdrawActivity);

    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordSettled(WithdrawResult result) {
        withdrawRecordMapper.done(result.getSn(), Integer.valueOf(result.getStatus()), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("已决清");
        withdrawActivity.setActivityNo(3);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(result.getMessage());
        withdrawActivity.setRecordSn(result.getSn());
        withdrawActivity.setStatus(Integer.valueOf(result.getStatus()));
        withdrawActivityMapper.insert(withdrawActivity);

    }


    @CjTransaction
    @Override
    public void ackPurchasing(PurchaseResult result) {
        WenyPurchRecord record = wenyPurchRecordMapper.selectByPrimaryKey(result.getSn());
        if (record == null) {
            return;
        }
        int _status = Float.valueOf(result.getStatus()).intValue();
        PurchasingResult purchasingResult = new Gson().fromJson((String) result.getRecord(), PurchasingResult.class);
        if (purchasingResult != null) {
            wenyPurchRecordMapper.ackPurchasing(
                    purchasingResult.getOutTradeSn(),
                    purchasingResult.getAmount(),
                    purchasingResult.getFeeRatio(),
                    purchasingResult.getServiceFee(),
                    purchasingResult.getPrincipalAmount(),
                    purchasingResult.getPrincipalRatio(),
                    purchasingResult.getTtm(),
                    purchasingResult.getSn(),
                    _status,
                    result.getMessage()
            );
        }

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已回单");
        wenyPurchActivity.setActivityNo(1);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(result.getMessage());
        wenyPurchActivity.setRecordSn(result.getSn());
        wenyPurchActivity.setStatus(_status);
        wenyPurchActivityMapper.insert(wenyPurchActivity);
        int status = _status;
        if (status >= 300) {
            wenyPurchRecordMapper.done(result.getSn(), status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyPurchRecordMapper.updateStatus(result.getSn(), status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackPurchased(PurchasedBO purchasedBO, String status, String message) {
        WenyPurchRecord record = wenyPurchRecordMapper.selectByPrimaryKey(purchasedBO.getSn());
        if (record == null) {
            return;
        }
        wenyPurchRecordMapper.ackPurchased(
                purchasedBO.getSn(),
                purchasedBO.getStock(),
                purchasedBO.getPrice(),
                purchasedBO.getFreeAmount(),
                purchasedBO.getFreeRatio(),
                purchasedBO.getReserveAmount(),
                purchasedBO.getReserveRatio(),
                Float.valueOf(status).intValue(),
                message,
                WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis())
        );

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("决清中");
        wenyPurchActivity.setActivityNo(2);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(message);
        wenyPurchActivity.setRecordSn(purchasedBO.getSn());
        wenyPurchActivity.setStatus(Float.valueOf(status).intValue());
        wenyPurchActivityMapper.insert(wenyPurchActivity);
        int _status = Float.valueOf(status).intValue();
        if (_status >= 300) {
            wenyPurchRecordMapper.done(purchasedBO.getSn(), _status, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyPurchRecordMapper.updateStatus(purchasedBO.getSn(), _status, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackPurchasedDone(PurchasedBO purchasedBO, String status, String message) {
        wenyPurchRecordMapper.done(purchasedBO.getSn(), Float.valueOf(status).intValue(), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已决清");
        wenyPurchActivity.setActivityNo(3);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(message);
        wenyPurchActivity.setRecordSn(purchasedBO.getSn());
        wenyPurchActivity.setStatus(Float.valueOf(status).intValue());
        wenyPurchActivityMapper.insert(wenyPurchActivity);
    }

    @CjTransaction
    @Override
    public void ackExchange(ExchangingResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        if (_status >= 300) {
            wenyExchangeRecordMapper.done(result.getSn(), _status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyExchangeRecordMapper.updateStatus(result.getSn(), _status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }

        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("已回单");
        wenyExchangeActivity.setActivityNo(1);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(result.getMessage());
        wenyExchangeActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        wenyExchangeActivity.setRecordSn(result.getSn());
        wenyExchangeActivityMapper.insert(wenyExchangeActivity);

    }

    @CjTransaction
    @Override
    public void ackExchangedDone(ExchangingResult result, String status, String message) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        wenyExchangeRecordMapper.done(result.getSn(), _status, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        ExchangeResult exchangeResult = new Gson().fromJson((String) result.getRecord(), ExchangeResult.class);
        if (exchangeResult != null) {
            wenyPurchRecordMapper.exchanged(exchangeResult.getWalletPuchaseSn(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("已决清");
        wenyExchangeActivity.setActivityNo(4);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(message);
        wenyExchangeActivity.setRecordSn(result.getSn());
        wenyExchangeActivity.setStatus(_status);
        wenyExchangeActivityMapper.insert(wenyExchangeActivity);
    }

    @CjTransaction
    @Override
    public RechargeRecord getRechargeRecord(String principal, String record_sn) {
        RechargeRecordExample example = new RechargeRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<RechargeRecord> list = rechargeRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<RechargeActivity> getRechargeActivities(String principal, String record_sn) {
        return rechargeActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public WithdrawRecord getWithdrawRecord(String principal, String record_sn) {
        WithdrawRecordExample example = new WithdrawRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<WithdrawRecord> list = withdrawRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<WithdrawActivity> getWithdrawActivities(String principal, String record_sn) {
        return withdrawActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecordOfPerson(String principal, String record_sn) {
        WenyPurchRecordExample example = new WenyPurchRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<WenyPurchRecord> list = wenyPurchRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<WenyPurchActivity> getPurchaseActivities(String principal, String record_sn) {
        return wenyPurchActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public WenyExchangeRecord getExchangeRecord(String principal, String record_sn) {
        WenyExchangeRecordExample example = new WenyExchangeRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<WenyExchangeRecord> list = wenyExchangeRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<WenyExchangeActivity> getExchangeActivities(String principal, String record_sn) {
        return wenyExchangeActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public WenyExchangeRecord getExchangeRecordByPurchase(String principal, String purchase_sn) {
        WenyExchangeRecordExample example = new WenyExchangeRecordExample();
        example.createCriteria().andRefsnEqualTo(purchase_sn).andPersonEqualTo(principal);
        List<WenyExchangeRecord> list = wenyExchangeRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<RechargeRecord> pageRechargeRecord(String principal, int limit, long offset) {
        return rechargeRecordMapper.page(principal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WithdrawRecord> pageWithdrawRecord(String principal, int limit, long offset) {
        return withdrawRecordMapper.page(principal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyPurchRecord> pagePurchaseRecord(String principal, String wenyBankID, int limit, long offset) {
        return wenyPurchRecordMapper.page(principal, wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyPurchRecord> pagePurchaseRecordOfUnexchanged(String principal, String wenyBankID, int limit, long offset) {
        return wenyPurchRecordMapper.pageUnexchanged(principal, wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyPurchRecord> pagePurchaseRecordOfExchanged(String principal, String wenyBankID, int limit, long offset) {
        return wenyPurchRecordMapper.pageExchanged(principal, wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyExchangeRecord> pageExchangeRecord(String principal, String wenyBankID, int limit, long offset) {
        return wenyExchangeRecordMapper.page(principal, wenyBankID, limit, offset);
    }

    @CjTransaction
    @Override
    public PayRecord getPayment(String principal, String payment_sn) {
        PayRecordExample example = new PayRecordExample();
        example.createCriteria().andSnEqualTo(payment_sn).andPersonEqualTo(principal);
        List<PayRecord> list = payRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public PayDetails getPayDetails(String payment_sn) {
        PayDetailsExample example = new PayDetailsExample();
        example.createCriteria().andPaySnEqualTo(payment_sn);
        List<PayDetails> list = payDetailsMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<PayActivity> getPayActivities(String record_sn) {
        return payActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public TransProfitRecord getTransProfitRecord(String principal, String record_sn) {
        TransProfitRecordExample example = new TransProfitRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<TransProfitRecord> list = transProfitRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TransProfitActivity> getTransProfitActivities(String principal, String record_sn) {
        return transProfitActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public TransAbsorbRecord getTransAbsorbRecord(String principal, String record_sn) {
        TransAbsorbRecordExample example = new TransAbsorbRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<TransAbsorbRecord> list = transAbsorbRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<TransAbsorbActivity> getTransAbsorbActivities(String principal, String record_sn) {
        return transAbsorbActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public DepositAbsorbRecord getDepositAbsorbRecord(String principal, String record_sn) {
        DepositAbsorbRecordExample example = new DepositAbsorbRecordExample();
        example.createCriteria().andSnEqualTo(record_sn).andPersonEqualTo(principal);
        List<DepositAbsorbRecord> list = depositAbsorbRecordMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<DepositAbsorbActivity> getDepositAbsorbActivities(String principal, String record_sn) {
        return depositAbsorbActivityMapper.getAllActivities(record_sn);
    }

    @CjTransaction
    @Override
    public void ackDepositAbsorb(DepositAbsorbResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        DepositAbsorbBO bo = new Gson().fromJson((String) result.getRecord(), DepositAbsorbBO.class);
        depositAbsorbRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        DepositAbsorbActivity depositAbsorbActivity = new DepositAbsorbActivity();
        depositAbsorbActivity.setActivityName("已完成");
        depositAbsorbActivity.setActivityNo(1);
        depositAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        depositAbsorbActivity.setId(new IdWorker().nextId());
        depositAbsorbActivity.setMessage(result.getMessage());
        depositAbsorbActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        depositAbsorbActivity.setRecordSn(result.getSn());
        depositAbsorbActivityMapper.insert(depositAbsorbActivity);
    }

    @CjTransaction
    @Override
    public void ackTransAbsorb(TransAbsorbResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        TransAbsorbBO bo = new Gson().fromJson((String) result.getRecord(), TransAbsorbBO.class);
        transAbsorbRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        TransAbsorbActivity transAbsorbActivity = new TransAbsorbActivity();
        transAbsorbActivity.setActivityName("已完成");
        transAbsorbActivity.setActivityNo(1);
        transAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transAbsorbActivity.setId(new IdWorker().nextId());
        transAbsorbActivity.setMessage(result.getMessage());
        transAbsorbActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        transAbsorbActivity.setRecordSn(result.getSn());
        transAbsorbActivityMapper.insert(transAbsorbActivity);
    }

    @CjTransaction
    @Override
    public void ackTransProfit(TransProfitResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        TransAbsorbBO bo = new Gson().fromJson((String) result.getRecord(), TransAbsorbBO.class);
        transProfitRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        TransProfitActivity transProfitActivity = new TransProfitActivity();
        transProfitActivity.setActivityName("已完成");
        transProfitActivity.setActivityNo(1);
        transProfitActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transProfitActivity.setId(new IdWorker().nextId());
        transProfitActivity.setMessage(result.getMessage());
        transProfitActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        transProfitActivity.setRecordSn(result.getSn());
        transProfitActivityMapper.insert(transProfitActivity);
    }
}

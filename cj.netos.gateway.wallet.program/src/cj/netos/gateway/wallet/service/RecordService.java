package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.PurchasedBO;
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
        int _status=Float.valueOf(result.getStatus()).intValue();
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
    public List<WenyPurchRecord> pagePurchaseRecord(String principal, int limit, long offset) {
        return wenyPurchRecordMapper.page(principal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyPurchRecord> pagePurchaseRecordOfUnexchanged(String principal, int limit, long offset) {
        return wenyPurchRecordMapper.pageUnexchanged(principal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<WenyExchangeRecord> pageExchangeRecord(String principal, int limit, long offset) {
        return wenyExchangeRecordMapper.page(principal, limit, offset);
    }
}

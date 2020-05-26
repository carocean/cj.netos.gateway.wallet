package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.mapper.*;
import cj.netos.gateway.wallet.model.RechargeActivity;
import cj.netos.gateway.wallet.model.WenyPurchActivity;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WithdrawActivity;
import cj.netos.gateway.wallet.result.*;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;

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
        PurchasingResult purchasingResult = new Gson().fromJson((String) result.getRecord(), PurchasingResult.class);
        WenyPurchRecord record = wenyPurchRecordMapper.selectByPrimaryKey(purchasingResult.getOutTradeSn());
        if (record == null) {
            return;
        }
        wenyPurchRecordMapper.ackPurchasing(
                purchasingResult.getOutTradeSn(),
                purchasingResult.getAmount(),
                purchasingResult.getFeeRatio(),
                purchasingResult.getServiceFee(),
                purchasingResult.getPrincipalAmount(),
                purchasingResult.getPrincipalRatio(),
                purchasingResult.getTtm(),
                purchasingResult.getSn()
        );

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已回单");
        wenyPurchActivity.setActivityNo(1);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(result.getMessage());
        wenyPurchActivity.setRecordSn(purchasingResult.getOutTradeSn());
        wenyPurchActivity.setStatus(Integer.valueOf(result.getStatus()));
        wenyPurchActivityMapper.insert(wenyPurchActivity);
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
                Integer.valueOf(status),
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
        wenyPurchActivity.setStatus(Integer.valueOf(status));
        wenyPurchActivityMapper.insert(wenyPurchActivity);
    }

    @CjTransaction
    @Override
    public void ackPurchasedDone(PurchasedBO purchasedBO, String status, String message) {
        wenyPurchRecordMapper.done(purchasedBO.getSn(), Integer.valueOf(status), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已决清");
        wenyPurchActivity.setActivityNo(3);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(message);
        wenyPurchActivity.setRecordSn(purchasedBO.getSn());
        wenyPurchActivity.setStatus(Integer.valueOf(status));
        wenyPurchActivityMapper.insert(wenyPurchActivity);
    }
}

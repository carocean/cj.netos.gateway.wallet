package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.mapper.RechargeRecordMapper;
import cj.netos.gateway.wallet.mapper.WenyPurchRecordMapper;
import cj.netos.gateway.wallet.mapper.WithdrawRecordMapper;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.OnorderResult;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.netos.gateway.wallet.result.WithdrawResult;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

@CjBridge(aspects = "@transaction")
@CjService(name = "recordService")
public class RecordService implements IRecordService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeRecordMapper")
    RechargeRecordMapper rechargeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyPurchRecordMapper")
    WenyPurchRecordMapper wenyPurchRecordMapper;
    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecord(String sn) {
        return null;
    }

    @CjTransaction
    @Override
    public void ackPutonorder(OnorderResult result) {

    }

    @CjTransaction
    @Override
    public void ackRechargeRecord(RechargeResult result) {
        int state = "200".equals(result.getStatus()) ? 2 : -2;
        rechargeRecordMapper.done(result.getSn(), state, result.getStatus(), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        CJSystem.logging().info(getClass(), String.format("充值单已确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordOnorder(OnorderResult result) {
        int state = "200".equals(result.getStatus()) ? 1 : -1;
        withdrawRecordMapper.done(result.getSn(), state, result.getStatus(), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        CJSystem.logging().info(getClass(), String.format("提现单预扣款确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordSettled(WithdrawResult result) {
        int state = "200".equals(result.getStatus()) ? 3 : -3;
        withdrawRecordMapper.done(result.getSn(), state, result.getStatus(), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        CJSystem.logging().info(getClass(), String.format("提现单决清完成:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
    @CjTransaction
    @Override
    public void ackPurchaseRecordOnorder(OnorderResult result) {
        int state = "200".equals(result.getStatus()) ? 1 : -1;
        wenyPurchRecordMapper.done(result.getSn(), state, result.getStatus(), result.getMessage(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        CJSystem.logging().info(getClass(), String.format("申购单预扣款确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

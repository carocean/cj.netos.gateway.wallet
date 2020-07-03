package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.*;
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

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransShunterRecordMapper")
    TransShunterRecordMapper transShunterRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TransShunterActivityMapper")
    TransShunterActivityMapper transShunterActivityMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.P2pRecordMapper")
    P2pRecordMapper p2pRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.P2pActivityMapper")
    P2pActivityMapper p2pActivityMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositHubTailsRecordMapper")
    DepositHubTailsRecordMapper depositHubTailsRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.DepositHubTailsActivityMapper")
    DepositHubTailsActivityMapper depositHubTailsActivityMapper;

    @CjTransaction
    @Override
    public WenyPurchRecord getPurchaseRecord(String sn) {
        return wenyPurchRecordMapper.selectByPrimaryKey(sn);
    }

    @CjTransaction
    @Override
    public void ackRechargeRecord(RechargeResult result) {
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        rechargeRecordMapper.done(result.getSn(), Integer.valueOf(result.getStatus()), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        RechargeActivity rechargeActivity = new RechargeActivity();
        rechargeActivity.setActivityName("已决清");
        rechargeActivity.setActivityNo(2);
        rechargeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        rechargeActivity.setId(new IdWorker().nextId());
        rechargeActivity.setMessage(msg);
        rechargeActivity.setRecordSn(result.getSn());
        rechargeActivity.setStatus(Integer.valueOf(result.getStatus()));
        rechargeActivityMapper.insert(rechargeActivity);

    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordOnorder(WithdrawResult result) {
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        withdrawRecordMapper.update(result.getSn(), Integer.valueOf(result.getStatus()), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("预扣款完成");
        withdrawActivity.setActivityNo(2);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(msg);
        withdrawActivity.setRecordSn(result.getSn());
        withdrawActivity.setStatus(Integer.valueOf(result.getStatus()));
        withdrawActivityMapper.insert(withdrawActivity);

    }

    @CjTransaction
    @Override
    public void ackWithdrawRecordSettled(WithdrawResult result) {
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        withdrawRecordMapper.done(result.getSn(), Integer.valueOf(result.getStatus()), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("已决清");
        withdrawActivity.setActivityNo(3);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(msg);
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
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
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
                    msg
            );
        }

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已回单");
        wenyPurchActivity.setActivityNo(1);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(msg);
        wenyPurchActivity.setRecordSn(result.getSn());
        wenyPurchActivity.setStatus(_status);
        wenyPurchActivityMapper.insert(wenyPurchActivity);
        int status = _status;
        if (status >= 300) {
            wenyPurchRecordMapper.done(result.getSn(), status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyPurchRecordMapper.updateStatus(result.getSn(), status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackPurchased(PurchasedBO purchasedBO, String status, String message) {
        WenyPurchRecord record = wenyPurchRecordMapper.selectByPrimaryKey(purchasedBO.getSn());
        if (record == null) {
            return;
        }
        String msg = message;
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
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
                msg,
                WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis())
        );

        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("决清中");
        wenyPurchActivity.setActivityNo(2);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(msg);
        wenyPurchActivity.setRecordSn(purchasedBO.getSn());
        wenyPurchActivity.setStatus(Float.valueOf(status).intValue());
        wenyPurchActivityMapper.insert(wenyPurchActivity);
        int _status = Float.valueOf(status).intValue();
        if (_status >= 300) {
            wenyPurchRecordMapper.done(purchasedBO.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyPurchRecordMapper.updateStatus(purchasedBO.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackPurchasedDone(PurchasedBO purchasedBO, String status, String message) {
        String msg = message;
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        wenyPurchRecordMapper.done(purchasedBO.getSn(), Float.valueOf(status).intValue(), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WenyPurchActivity wenyPurchActivity = new WenyPurchActivity();
        wenyPurchActivity.setActivityName("已决清");
        wenyPurchActivity.setActivityNo(3);
        wenyPurchActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyPurchActivity.setId(new IdWorker().nextId());
        wenyPurchActivity.setMessage(msg);
        wenyPurchActivity.setRecordSn(purchasedBO.getSn());
        wenyPurchActivity.setStatus(Float.valueOf(status).intValue());
        wenyPurchActivityMapper.insert(wenyPurchActivity);
    }

    @CjTransaction
    @Override
    public void ackExchange(ExchangingResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        if (_status >= 300) {
            wenyExchangeRecordMapper.done(result.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyExchangeRecordMapper.updateStatus(result.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }

        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("已回单");
        wenyExchangeActivity.setActivityNo(1);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(msg);
        wenyExchangeActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        wenyExchangeActivity.setRecordSn(result.getSn());
        wenyExchangeActivityMapper.insert(wenyExchangeActivity);

    }

    @CjTransaction
    @Override
    public void ackExchangedDone(ExchangingResult result, String status, String message) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        String msg = message;
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        wenyExchangeRecordMapper.done(result.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        ExchangeResult exchangeResult = new Gson().fromJson((String) result.getRecord(), ExchangeResult.class);
        if (exchangeResult != null) {
            wenyPurchRecordMapper.exchanged(exchangeResult.getWalletPuchaseSn(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("已决清");
        wenyExchangeActivity.setActivityNo(4);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(msg);
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
    public P2pRecord getP2PRecord(String principal, String record_sn) {
        return p2pRecordMapper.selectByPrimaryKey(record_sn);
    }

    @CjTransaction
    @Override
    public List<P2pActivity> getP2PActivities(String record_sn) {
        P2pActivityExample example = new P2pActivityExample();
        example.createCriteria().andRecordSnEqualTo(record_sn);
        return p2pActivityMapper.selectByExample(example);
    }

    @CjTransaction
    @Override
    public DepositHubTailsRecord getDepositHubTailsRecord(String principal, String record_sn) {
        return depositHubTailsRecordMapper.selectByPrimaryKey(record_sn);
    }

    @CjTransaction
    @Override
    public List<DepositHubTailsActivity> getDepositHubTailsActivities(String record_sn) {
        DepositHubTailsActivityExample example = new DepositHubTailsActivityExample();
        example.createCriteria().andRecordSnEqualTo(record_sn);
        return depositHubTailsActivityMapper.selectByExample(example);
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
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        depositAbsorbRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        DepositAbsorbActivity depositAbsorbActivity = new DepositAbsorbActivity();
        depositAbsorbActivity.setActivityName("已完成");
        depositAbsorbActivity.setActivityNo(1);
        depositAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        depositAbsorbActivity.setId(new IdWorker().nextId());
        depositAbsorbActivity.setMessage(msg);
        depositAbsorbActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        depositAbsorbActivity.setRecordSn(result.getSn());
        depositAbsorbActivityMapper.insert(depositAbsorbActivity);
    }

    @CjTransaction
    @Override
    public void ackDepositHubTails(DepositHubTailsResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        DepositHubTailsBO bo = new Gson().fromJson((String) result.getRecord(), DepositHubTailsBO.class);
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        depositHubTailsRecordMapper.done(result.getSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        DepositHubTailsActivity activity = new DepositHubTailsActivity();
        activity.setActivityName("已完成");
        activity.setActivityNo(1);
        activity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        activity.setId(new IdWorker().nextId());
        activity.setMessage(msg);
        activity.setStatus(Float.valueOf(result.getStatus()).intValue());
        activity.setRecordSn(result.getSn());
        depositHubTailsActivityMapper.insert(activity);
    }

    @CjTransaction
    @Override
    public void ackTransAbsorb(TransAbsorbResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        TransAbsorbBO bo = new Gson().fromJson((String) result.getRecord(), TransAbsorbBO.class);
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        transAbsorbRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        TransAbsorbActivity transAbsorbActivity = new TransAbsorbActivity();
        transAbsorbActivity.setActivityName("已完成");
        transAbsorbActivity.setActivityNo(1);
        transAbsorbActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transAbsorbActivity.setId(new IdWorker().nextId());
        transAbsorbActivity.setMessage(msg);
        transAbsorbActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        transAbsorbActivity.setRecordSn(result.getSn());
        transAbsorbActivityMapper.insert(transAbsorbActivity);
    }

    @CjTransaction
    @Override
    public void ackTransProfit(TransProfitResult result) {
        int _status = Float.valueOf(result.getStatus()).intValue();
        TransAbsorbBO bo = new Gson().fromJson((String) result.getRecord(), TransAbsorbBO.class);
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        transProfitRecordMapper.done(result.getSn(), bo.getDemandAmount(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));

        TransProfitActivity transProfitActivity = new TransProfitActivity();
        transProfitActivity.setActivityName("已完成");
        transProfitActivity.setActivityNo(1);
        transProfitActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transProfitActivity.setId(new IdWorker().nextId());
        transProfitActivity.setMessage(msg);
        transProfitActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        transProfitActivity.setRecordSn(result.getSn());
        transProfitActivityMapper.insert(transProfitActivity);
    }

    @CjTransaction
    @Override
    public void ackTransShunterReceipt(TransShuntResult result) {
        TransShunterRecord record = transShunterRecordMapper.selectByPrimaryKey(result.getSn());
        if (record == null) {
            return;
        }
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        int _status = Float.valueOf(result.getStatus()).intValue();
        WithdrawShunterResult withdrawShunterResult = new Gson().fromJson((String) result.getRecord(), WithdrawShunterResult.class);
        if (withdrawShunterResult != null) {
            transShunterRecordMapper.ackReceipt(
                    withdrawShunterResult.getOutTradeSn(),
                    withdrawShunterResult.getSn(),
                    _status,
                    msg,
                    WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis())
            );
        }

        TransShunterActivity transShunterActivity = new TransShunterActivity();
        transShunterActivity.setActivityName("已回单");
        transShunterActivity.setActivityNo(1);
        transShunterActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transShunterActivity.setId(new IdWorker().nextId());
        transShunterActivity.setMessage(msg);
        transShunterActivity.setRecordSn(result.getSn());
        transShunterActivity.setStatus(_status);
        transShunterActivityMapper.insert(transShunterActivity);
        int status = _status;
        if (status >= 300) {
            transShunterRecordMapper.done(result.getSn(), result.getOutTradeSn(), status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            transShunterRecordMapper.updateStatus(result.getSn(), result.getOutTradeSn(), status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackTransShuntFromBank(WithdrawShunterBO bo, String status, String message) {
        TransShunterRecord record = transShunterRecordMapper.selectByPrimaryKey(bo.getSn());
        if (record == null) {
            return;
        }
        String msg = message;
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        transShunterRecordMapper.ackTransShuntFromBank(
                bo.getSn(),
                bo.getRealAmount(),
                Float.valueOf(status).intValue(),
                msg,
                WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis())
        );

        TransShunterActivity transShunterActivity = new TransShunterActivity();
        transShunterActivity.setActivityName("决清中");
        transShunterActivity.setActivityNo(2);
        transShunterActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transShunterActivity.setId(new IdWorker().nextId());
        transShunterActivity.setMessage(msg);
        transShunterActivity.setRecordSn(bo.getSn());
        transShunterActivity.setStatus(Float.valueOf(status).intValue());
        transShunterActivityMapper.insert(transShunterActivity);
        int _status = Float.valueOf(status).intValue();
        if (_status >= 300) {
            transShunterRecordMapper.done(bo.getSn(), bo.getOutTradeSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            transShunterRecordMapper.updateStatus(bo.getSn(), bo.getOutTradeSn(), _status, msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
    }

    @CjTransaction
    @Override
    public void ackTransShunterDone(WithdrawShunterBO bo, String status, String message) {
        String msg = message;
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        transShunterRecordMapper.done(bo.getSn(), bo.getOutTradeSn(), Float.valueOf(status).intValue(), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        TransShunterActivity transShunterActivity = new TransShunterActivity();
        transShunterActivity.setActivityName("已决清");
        transShunterActivity.setActivityNo(3);
        transShunterActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        transShunterActivity.setId(new IdWorker().nextId());
        transShunterActivity.setMessage(msg);
        transShunterActivity.setRecordSn(bo.getSn());
        transShunterActivity.setStatus(Float.valueOf(status).intValue());
        transShunterActivityMapper.insert(transShunterActivity);
    }

    @CjTransaction
    @Override
    public void ackPayTrade(PayResult result) {
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        PayBO bo = new Gson().fromJson((String) result.getRecord(), PayBO.class);
        payRecordMapper.done(bo.getSn(), Float.valueOf(result.getStatus()).intValue(), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        PayActivity payActivity = new PayActivity();
        payActivity.setActivityName("已决清");
        payActivity.setActivityNo(1);
        payActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        payActivity.setId(new IdWorker().nextId());
        payActivity.setMessage(msg);
        payActivity.setRecordSn(bo.getSn());
        payActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        payActivityMapper.insert(payActivity);
    }

    @CjTransaction
    @Override
    public void ackP2P(P2PResult result) {
        String msg = result.getMessage();
        if (msg.length() > 250) {
            msg = msg.substring(0, 250);
        }
        P2PBO bo = new Gson().fromJson((String) result.getRecord(), P2PBO.class);
        p2pRecordMapper.done(bo.getSn(), Float.valueOf(result.getStatus()).intValue(), msg, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        P2pActivity p2pActivity = new P2pActivity();
        p2pActivity.setActivityName("已决清");
        p2pActivity.setActivityNo(1);
        p2pActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        p2pActivity.setId(new IdWorker().nextId());
        p2pActivity.setMessage(msg);
        p2pActivity.setRecordSn(bo.getSn());
        p2pActivity.setStatus(Float.valueOf(result.getStatus()).intValue());
        p2pActivityMapper.insert(p2pActivity);
    }
}

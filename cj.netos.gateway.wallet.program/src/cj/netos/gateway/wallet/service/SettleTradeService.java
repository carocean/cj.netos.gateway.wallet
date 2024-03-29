package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.bo.*;
import cj.netos.gateway.wallet.mapper.*;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.ExchangedResult;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "settleTradeService")
public class SettleTradeService implements ISettleTradeService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeRecordMapper")
    RechargeRecordMapper rechargeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.RechargeActivityMapper")
    RechargeActivityMapper rechargeActivityMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ModuleTransinRecordMapper")
    ModuleTransinRecordMapper moduleTransinRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ModuleTransinActivityMapper")
    ModuleTransinActivityMapper moduleTransinActivityMapper;


    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawActivityMapper")
    WithdrawActivityMapper withdrawActivityMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeRecordMapper")
    WenyExchangeRecordMapper wenyExchangeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WenyExchangeActivityMapper")
    WenyExchangeActivityMapper wenyExchangeActivityMapper;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_settle_exchange")
    IRabbitMQProducer toOC_settle_exchange;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_settle_purchase")
    IRabbitMQProducer toOC_settle_purchase;

    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_settle_recharge")
    IRabbitMQProducer toOC_settle_recharge;



    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_settle_withdraw")
    IRabbitMQProducer toOC_settle_withdraw;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_settle_transShunter")
    IRabbitMQProducer toOC_settle_transShunter;

    @CjTransaction
    @Override
    public void settleRecharge(RechargeRecord record, long amount, String code, String message) throws CircuitException {
        rechargeRecordMapper.settle(record.getSn(), amount, Integer.valueOf(code), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        RechargeActivity activity = new RechargeActivity();
        activity.setStatus(Integer.valueOf(code));
        activity.setMessage(message);
        activity.setRecordSn(record.getSn());
        activity.setId(new IdWorker().nextId());
        activity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        activity.setActivityNo(1);
        activity.setActivityName("决清中");
        rechargeActivityMapper.insert(activity);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "recharge");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                    put("channelCode", record.getPayAccount());
                }})
                .build();
        RechargeBO rechargeBO = RechargeBO.create(record);
        rechargeBO.setRealAmount(amount);
        rechargeBO.setStatus(Integer.valueOf(code));
        rechargeBO.setMessage(message);
        toOC_settle_recharge.publish("oc", properties, new Gson().toJson(rechargeBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settleWithdraw(WithdrawRecord record, long amount, String code, String message) throws CircuitException {
        withdrawRecordMapper.settle(record.getSn(), amount, Integer.valueOf(code), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("决清中");
        withdrawActivity.setActivityNo(3);
        withdrawActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        withdrawActivity.setId(new IdWorker().nextId());
        withdrawActivity.setMessage(message);
        withdrawActivity.setStatus(Integer.valueOf(code));
        withdrawActivity.setRecordSn(record.getSn());
        withdrawActivityMapper.insert(withdrawActivity);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "withdraw");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                    put("channelCode", record.getPayChannel());
                    put("personCard", record.getPersonCard());
                }})
                .build();
        WithdrawBO withdrawBO = WithdrawBO.create(record);
        withdrawBO.setRealAmount(amount);
        withdrawBO.setSettleCode(code);
        withdrawBO.setSettleMsg(message);
        toOC_settle_withdraw.publish("oc", properties, new Gson().toJson(withdrawBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settlePurchased(PurchasedBO purchasedBO, String status, String message) throws CircuitException {
        //决清之后将在订单款真正清除掉,如果实际申购金小于请求金则归还
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "purchase");
                    put("person", purchasedBO.getPerson());
                    put("record_sn", purchasedBO.getSn());
                }})
                .build();
        toOC_settle_purchase.publish("oc", properties, new Gson().toJson(purchasedBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settleExchange(ExchangedResult result, String status, String message) throws CircuitException {
        int _status = Float.valueOf(status).intValue();
        if (_status < 300) {
            wenyExchangeRecordMapper.settle(result.getOutTradeSn(), result.getAmount(), result.getPrice(), result.getProfit(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        } else {
            wenyExchangeRecordMapper.updateStatus(result.getOutTradeSn(), _status, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }
        WenyExchangeActivity wenyExchangeActivity = new WenyExchangeActivity();
        wenyExchangeActivity.setActivityName("收到决清指令");
        wenyExchangeActivity.setActivityNo(3);
        wenyExchangeActivity.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        wenyExchangeActivity.setId(new IdWorker().nextId());
        wenyExchangeActivity.setMessage(message);
        wenyExchangeActivity.setStatus(Integer.valueOf(_status));
        wenyExchangeActivity.setRecordSn(result.getOutTradeSn());
        wenyExchangeActivityMapper.insert(wenyExchangeActivity);

        WenyExchangeRecord record = recordService.getExchangeRecord(result.getExchanger(), result.getOutTradeSn());
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "exchange");
                    put("person", result.getExchanger());
                    put("record_sn", result.getOutTradeSn());
                }})
                .build();
        ExchangedBO bo = new ExchangedBO();
        bo.load(result, record.getRefsn());
        toOC_settle_exchange.publish("oc", properties, new Gson().toJson(bo).getBytes());
    }

    @CjTransaction
    @Override
    public void settleTransShunter(WithdrawShunterBO withdrawShunterBO, String status, String message) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "transShunter");
                    put("person", withdrawShunterBO.getPerson());
                    put("record_sn", withdrawShunterBO.getSn());
                }})
                .build();
        toOC_settle_transShunter.publish("oc", properties, new Gson().toJson(withdrawShunterBO).getBytes());
    }
}

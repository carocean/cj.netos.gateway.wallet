package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.bo.ExchangedBO;
import cj.netos.gateway.wallet.bo.PurchasedBO;
import cj.netos.gateway.wallet.bo.RechargeBO;
import cj.netos.gateway.wallet.bo.WithdrawBO;
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
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

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
                    put("channelCode", record.getFromChannel());
                }})
                .build();
        RechargeBO rechargeBO = RechargeBO.create(record);
        rechargeBO.setRealAmount(amount);
        rechargeBO.setStatus(Integer.valueOf(code));
        rechargeBO.setMessage(message);
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(rechargeBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settleWithdraw(WithdrawRecord record, long amount, String code, String message) throws CircuitException {
        withdrawRecordMapper.settle(record.getSn(), amount, Integer.valueOf(code), message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        WithdrawActivity withdrawActivity = new WithdrawActivity();
        withdrawActivity.setActivityName("决清中");
        withdrawActivity.setActivityNo(1);
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
                    put("channelCode", record.getToChannel());
                }})
                .build();
        WithdrawBO withdrawBO = WithdrawBO.create(record);
        withdrawBO.setRealAmount(amount);
        withdrawBO.setSettleCode(code);
        withdrawBO.setSettleMsg(message);
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(withdrawBO).getBytes());
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
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(purchasedBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settleExchange(ExchangedResult result, String status, String message) throws CircuitException {
        int _status=Integer.valueOf(status);
        if (_status < 300) {
            wenyExchangeRecordMapper.settle(result.getOutTradeSn(), result.getAmount(), result.getPrice(),result.getProfit(), WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        }else{
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

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "exchange");
                    put("person", result.getExchanger());
                    put("record_sn", result.getOutTradeSn());
                }})
                .build();
        ExchangedBO bo = new ExchangedBO();
        bo.load(result);
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(bo).getBytes());
    }
}

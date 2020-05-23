package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.ISettleTradeService;
import cj.netos.gateway.wallet.bo.RechargeBO;
import cj.netos.gateway.wallet.bo.WithdrawBO;
import cj.netos.gateway.wallet.mapper.RechargeRecordMapper;
import cj.netos.gateway.wallet.mapper.WithdrawRecordMapper;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
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
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;

    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

    @CjTransaction
    @Override
    public void settleRecharge(RechargeRecord record, long amount, String code, String message) throws CircuitException {
        rechargeRecordMapper.settle(record.getSn(), amount, code, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mq")
                .headers(new HashMap<String, Object>() {{
                    put("command", "recharge");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                    put("channelCode", record.getFromChannel());
                }})
                .build();
        RechargeBO rechargeBO = RechargeBO.create(record);
        rechargeBO.setRealAmount(amount);
        rechargeBO.setSettleCode(code);
        rechargeBO.setSettleMsg(message);
        rabbitMQProducer.publish("oc",properties, new Gson().toJson(rechargeBO).getBytes());
    }

    @CjTransaction
    @Override
    public void settleWithdraw(WithdrawRecord record, long amount, String code, String message) throws CircuitException {
        withdrawRecordMapper.settle(record.getSn(), amount, code, message, WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/settle.mq")
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
        rabbitMQProducer.publish("oc",properties, new Gson().toJson(withdrawBO).getBytes());
    }
}

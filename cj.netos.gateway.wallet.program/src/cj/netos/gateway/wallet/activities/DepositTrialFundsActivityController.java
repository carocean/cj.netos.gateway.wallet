package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.*;
import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.bo.DepositTrialBO;
import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.model.DepositTrialRecord;
import cj.netos.gateway.wallet.model.TrialFundsConfig;
import cj.netos.gateway.wallet.result.DepositTrialFundsResult;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjBridge(aspects = "@transaction")
@CjService(name = "depositTrialFundsActivityController")
public class DepositTrialFundsActivityController implements IDepositTrialFundsActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    ITrialFundsConfig trialFundsConfig;
    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_depositTrialFunds")
    IRabbitMQProducer toOC_receipt_depositTrialFunds;
    @CjServiceRef
    IRecordService recordService;
    @CjServiceRef
    IPushToAbsorbChatroom pushToAbsorbChatroom;

    @CjTransaction
    @Override
    public TrialFundsConfig getConfig() {
        TrialFundsConfig config = trialFundsConfig.getConfig();
        if (config != null) {
            return config;
        }
        config = new TrialFundsConfig();
        config.setId(new IdWorker().nextId());
        config.setRemitAccount(null);
        config.setRemitName(null);
        config.setState(0);
        config.setTrialAmount(null);
        return config;
    }

    @CjTransaction
    @Override
    public DepositTrialRecord doReceipt(PayBO bo) throws CircuitException {
        DepositTrialRecord record = receiptTradeService.depositTrialFunds(bo);
        //发送存入指令
        DepositTrialBO depositTrialBO = new DepositTrialBO();
        depositTrialBO.setSn(record.getSn());
        depositTrialBO.setCurrency(record.getCurrency());
        depositTrialBO.setNote(record.getNote());
        depositTrialBO.setPerson(record.getPayee());
        depositTrialBO.setPersonName(record.getPayeeName());
        depositTrialBO.setAmount(record.getAmount());

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "depositTrialFunds");
                    put("person", record.getPayee());
                    put("record_sn", record.getSn());
                }})
                .build();

        toOC_receipt_depositTrialFunds.publish("oc", properties, new Gson().toJson(depositTrialBO).getBytes());
        //网关通过mq等待command确认
        return record;
    }

    @CjTransaction
    @Override
    public void ackReceipt(DepositTrialFundsResult result) {
        recordService.ackDepositTrialFunds(result);
        CJSystem.logging().info(getClass(), String.format("体验金已存入：%s %s %s", result.getSn(), result.getStatus(), result.getMessage()));
        try {
            DepositTrialRecord record = recordService.getDepositTrialRecord(result.getSn());
            pushToAbsorbChatroom.pushTrial(record);
        } catch (Exception e) {
            CJSystem.logging().error(getClass(), String.format("推送体验金通知失败:%s", e));
        }
    }
}

package cj.netos.gateway.wallet.activities;

import cj.netos.gateway.wallet.IModuleTransInActivityController;
import cj.netos.gateway.wallet.IReceiptTradeService;
import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.bo.ModuleTransinBO;
import cj.netos.gateway.wallet.model.ModuleTransinRecord;
import cj.netos.gateway.wallet.result.ModuleTransinResult;
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
@CjService(name = "moduleTransInActivityController")
public class ModuleTransInActivityController implements IModuleTransInActivityController {
    @CjServiceRef
    IReceiptTradeService receiptTradeService;
    @CjServiceRef
    IRecordService recordService;

    @CjServiceRef(refByName = "@.rabbitmq.producer.toOC_receipt_module_transin")
    IRabbitMQProducer toOC_receipt_module_transin;

    @CjTransaction
    @Override
    public void doReceipt(String moduleId, String moduleTitle, String person, String nickName, String payer, String payerName, long amount, String note) throws CircuitException {
        ModuleTransinRecord record=receiptTradeService.moduleTransin(moduleId, moduleTitle, person, nickName, payer, payerName, amount, note);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "moduleTransin");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();

        toOC_receipt_module_transin.publish("oc", properties, new Gson().toJson(ModuleTransinBO.create(record)).getBytes());
    }

    @CjTransaction
    @Override
    public void ackReceipt(ModuleTransinResult result) {
        recordService.ackModuleTransinRecord(result);
        CJSystem.logging().info(getClass(), String.format("模块转入单已确认:%s。结果: %s %s", result.getSn(), result.getStatus(), result.getMessage()));
    }
}

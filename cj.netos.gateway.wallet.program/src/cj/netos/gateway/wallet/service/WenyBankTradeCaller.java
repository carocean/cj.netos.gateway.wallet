package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IWenyBankTradeCaller;
import cj.netos.gateway.wallet.bo.ExchangeBO;
import cj.netos.gateway.wallet.bo.ExchangedBO;
import cj.netos.gateway.wallet.bo.PurchaseBO;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.ExchangedResult;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "wenyBankTradeCaller")
public class WenyBankTradeCaller implements IWenyBankTradeCaller {
    @CjServiceRef(refByName = "@.rabbitmq.producer.trade")
    IRabbitMQProducer rabbitMQProducer;

    @Override
    public void exchange(WenyExchangeRecord record) throws CircuitException {
        //向纹银银行提交承兑交易(交由oc处理）
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "exchange");
                    put("person", record.getPerson());
                }})
                .build();
        ExchangeBO exchangeBO = new ExchangeBO();
        exchangeBO.setBankid(record.getBankid());
        exchangeBO.setCurrency(record.getCurrency());
        exchangeBO.setNote(record.getNote());
        exchangeBO.setPerson(record.getPerson());
        exchangeBO.setPersonName(record.getPersonName());
        exchangeBO.setPurchAmount(record.getPurchAmount());
        exchangeBO.setRefsn(record.getRefsn());
        exchangeBO.setBankPurchNo(record.getBankPurchNo());
        exchangeBO.setSn(record.getSn());
        exchangeBO.setStock(record.getStock());
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(exchangeBO).getBytes());
        //网关通过mq等待command确认
    }

    @Override
    public void purchase(WenyPurchRecord record) throws CircuitException {
        PurchaseBO purchaseBO = new PurchaseBO();
        purchaseBO.setSn(record.getSn());
        purchaseBO.setWenyBankID(record.getBankid());
        purchaseBO.setPurchaser(record.getPerson());
        purchaseBO.setPurchaserName(record.getPersonName());
        purchaseBO.setAmount(record.getPurchAmount());
        purchaseBO.setCtime(record.getCtime());
        purchaseBO.setCurrency(record.getCurrency());
        purchaseBO.setNote(record.getNote());
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/trade/receipt.mhub")
                .headers(new HashMap<String, Object>() {{
                    put("command", "purchase");
                    put("person", record.getPerson());
                    put("record_sn", record.getSn());
                }})
                .build();
        rabbitMQProducer.publish("oc", properties, new Gson().toJson(purchaseBO).getBytes());
        //网关通过mq等待command确认
    }

}

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IAlipay;
import cj.netos.gateway.wallet.IPayChannelFactory;
import cj.netos.gateway.wallet.IWechatpay;
import cj.netos.gateway.wallet.PayChannelTransferResult;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

@CjService(name = "payChannelFactory")
public class PayChannelFactory implements IPayChannelFactory {
    @CjServiceRef
    IAlipay alipay;

    @CjServiceRef
    IWechatpay wechatpay;
    @Override
    public PayChannelTransferResult transfer(WithdrawRecord record, ChannelAccount account, PersonCard card) throws CircuitException {
        //将提现记录单提现到公众卡
        PayChannelTransferResult result = new PayChannelTransferResult();
        String payChannelID = record.getPayChannel();
        switch (payChannelID) {
            case "alipay":
                String json = alipay.transfer(record, account, card);
                Map<String, Object> responseMap = new Gson().fromJson(json, HashMap.class);
                Map<String, Object> map = (Map<String, Object>) responseMap.get("alipay_fund_trans_uni_transfer_response");
                String status = (String) map.get("code");
                result.setStatus("10000".equals(status) ? "200" : status);
                result.setMessage((String) map.get("msg"));
                result.setRecordSn(record.getSn());
                result.setResponse(map);
                break;
            default:
                throw new CircuitException("800", String.format("暂不支持支付渠道:%s", payChannelID));
        }
        return result;
    }

    @Override
    public String pay(RechargeRecord record) throws CircuitException {
        String result = null;
        String payChannelID = record.getPayChannel();
        switch (payChannelID) {
            case "alipay":
                result = alipay.pay(record);
                break;
            case "wechatpay":
                result=wechatpay.pay(record);
                break;
            default:
                throw new CircuitException("800", String.format("暂不支持支付渠道:%s", payChannelID));
        }
        return result;
    }
}

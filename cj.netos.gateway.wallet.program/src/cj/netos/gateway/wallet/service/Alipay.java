package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IAlipay;
import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjService(name = "alipay")
public class Alipay implements IAlipay {

    @CjServiceSite
    IServiceSite site;

    @CjServiceRef
    IChannelAccountService channelAccountService;

    @Override
    public String pay(RechargeRecord record) throws CircuitException {
        ChannelAccount account = channelAccountService.getAccount(record.getToChannelAccount());
        AlipayClient alipayClient = getClient(account);

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        Map<String, Object> body = new HashMap<>();
        body.put("channelAccount", record.getToChannelAccount());
        body.put("demandAmount", record.getDemandAmount());
        body.put("person", record.getPerson());
        body.put("personName", record.getPersonName());
        model.setBody(new Gson().toJson(body));
        model.setSubject("向地微充值");//在付款人的支付宝账单中显示的商品说明
        model.setOutTradeNo(record.getSn());//在付款人的支付宝账单中显示的商家订单号
        model.setTimeoutExpress("30m");
        model.setTotalAmount(String.format("%s", (record.getDemandAmount() / 100.00)));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(account.getPayNotifyUrl());//对应我方的决算地址
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            throw new CircuitException(e.getErrCode(), e);
        }
    }

    @Override
    public String transfer(WithdrawRecord record, ChannelAccount account, PersonCard card) throws CircuitException {
        AlipayClient alipayClient = getClient(account);
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
//        CJSystem.logging().info(getClass(),String.format("-------转账通知地址:%s",account.getTransNotifyUrl()));
        request.setNotifyUrl(account.getTransNotifyUrl());//账单状态变更通知，参与阿里云api
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + record.getSn() + "\"," +
                "\"trans_amount\":" + (record.getDemandAmount() / 100.00) + "," +
                "\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
                "\"biz_scene\":\"DIRECT_TRANSFER\"," +
                "\"order_title\":\"地微出款\"," +
                "\"payee_info\":{" +
                "\"identity\":\"" + card.getCardHolder() + "\"," +
                "\"identity_type\":\"ALIPAY_USER_ID\"" +
                "    }," +
                "\"remark\":\"\"," +
                "\"business_params\":\"{\\\"payer_show_name\\\":\\\"地微出款\\\"}\"," +
                "  }");
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = alipayClient.certificateExecute(request);
            return response.getBody();
        } catch (AlipayApiException e) {
            throw new CircuitException("500", e);
        }
    }

    @Override
    public AlipayUserInfoShareResponse getUserInfo(String payChannel, String authCode) throws AlipayApiException, CircuitException {
        ChannelAccount account = channelAccountService.selectAccount(payChannel, Math.abs(authCode.hashCode()));
        if (account == null) {
            throw new CircuitException("404", String.format("在支付渠道%s下未选中支付账号。", payChannel));
        }
        AlipayClient alipayClient = getClient(account);
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest(); //创建API对应的request类
        request.setGrantType("authorization_code");
        request.setCode(authCode);//在app中由调起支付宝返回auth_code
        AlipaySystemOauthTokenResponse response = alipayClient.certificateExecute(request); //通过alipayClient调用API，获得对应的response类
        System.out.print(response.getBody());
        //上面已经获取到了uid，就可以转账了，如还要获取账户资料，可以继续
        String accessToken = response.getAccessToken();
        AlipayUserInfoShareRequest request2 = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response2 = alipayClient.certificateExecute(request2, accessToken);
        return response2;
    }

    private AlipayClient getClient(ChannelAccount account) throws CircuitException {
        AlipayClient alipayClient = null;
        if (account.getUseCert() == 0) {
            String URL = account.getServiceUrl();
            String APP_ID = account.getAppId();
            String APP_PRIVATE_KEY = account.getPrivateKey();
            String ALIPAY_PUBLIC_KEY = account.getPublicKey();
            String CHARSET = "utf-8";
            //实例化客户端
            alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        } else {
            CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
            certAlipayRequest.setServerUrl(account.getServiceUrl());
            certAlipayRequest.setAppId(account.getAppId());
            certAlipayRequest.setPrivateKey(account.getPrivateKey());
            certAlipayRequest.setFormat("json");
            certAlipayRequest.setCharset("utf-8");
            certAlipayRequest.setSignType("RSA2");
            certAlipayRequest.setCertPath(account.getCertPath1());
            certAlipayRequest.setAlipayPublicCertPath(account.getCertPath2());
            certAlipayRequest.setRootCertPath(account.getCertPath3());
            try {
                alipayClient = new DefaultAlipayClient(certAlipayRequest);
            } catch (AlipayApiException e) {
                CJSystem.logging().error(getClass(), e);
                throw new CircuitException("500", e);
            }
        }
        return alipayClient;
    }
}

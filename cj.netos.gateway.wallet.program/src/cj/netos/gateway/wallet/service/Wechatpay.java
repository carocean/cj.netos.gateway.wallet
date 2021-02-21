package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.IWechatpay;
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
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.security.Base64Utils;
import cj.ultimate.util.StringUtil;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjService(name = "wechatpay")
public class Wechatpay implements IWechatpay {

    @CjServiceSite
    IServiceSite site;

    @CjServiceRef
    IChannelAccountService channelAccountService;

    /* 客户端要返回的信息
    参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
              fluwx.payWithWeChat(
                appId: result['appid'],//为微信分配的我方的移动应用号
                partnerId: result['partnerid'],//为商户号，我们是：1606337815
                prepayId: result['prepayid'],//预订单标识
                packageValue: result['package'],//一般为固定：Sign=WXPay
                nonceStr: result['noncestr'],
                timeStamp: result['timestamp'],
                sign: result['sign'],//注意：签名方式一定要与统一下单接口使用的一致
              );
     */
    @Override
    public String pay(RechargeRecord record) throws CircuitException {
        ChannelAccount account = channelAccountService.getAccount(record.getToChannelAccount());
        String applyTerminal = account.getApplyTerminal();
        if (StringUtil.isEmpty(applyTerminal)) {
            applyTerminal = "app";//默认是app支付
        }
        String result = null;
        switch (applyTerminal) {
            case "app":
                result = doAppPay(account, record);
                break;
            case "jsapi":
                result = doJsapiPay(account, record);
                break;
        }
        return result;
    }

    private String doJsapiPay(ChannelAccount account, RechargeRecord record) throws CircuitException {
        CloseableHttpClient httpClient = getClient(account);
        try {
            Map<String, Object> params = createJsapiOrder(httpClient, account, record);
            return new Gson().toJson(params);
        } catch (Exception e) {
            throw new CircuitException("500", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String doAppPay(ChannelAccount account, RechargeRecord record) throws CircuitException {
        CloseableHttpClient httpClient = getClient(account);
        try {
            Map<String, Object> params = createAppOrder(httpClient, account, record);
            return new Gson().toJson(params);
        } catch (Exception e) {
            throw new CircuitException("500", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String transfer(WithdrawRecord record, ChannelAccount account, PersonCard card) throws CircuitException {
        //从节点动力的微信商户向微信零钱转账，需要申请到微信对应接口，等吧
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        Wechatpay wechatpay = new Wechatpay();
//        CloseableHttpClient httpClient = wechatpay.getClient(null);
//        wechatpay.createOrder(httpClient, null);
//    }

    private Map<String, Object> createJsapiOrder(CloseableHttpClient httpClient, ChannelAccount account, RechargeRecord record) throws Exception {
//请求URL
        HttpPost httpPost = new HttpPost(account.getServiceUrl());
        // 请求body参数
        String appId = account.getAppId();
        String mchid = account.getMchId();
        String payNotifyUrl = account.getPayNotifyUrl();
        //由于jsapi是在微信中调用，因此record.getPerson()是微信的统一id,unionid，record需要保存openid供在此调用,
        //因此需要扩展uc中心的账号信息，可以关联第三方的用户号。然后在本项目的验证令牌方法中获取并设置openid，充值时就可得到并可存入RechargeRecord
        String openid = record.getPayOpenid();
        Map<String, String> attach = new HashMap<>();
        attach.put("channelAccount",record.getToChannelAccount());
        attach.put("person",record.getPerson());
        attach.put("nickName",record.getPersonName());
        String reqdata = "{"
//                + "\"time_expire\":\"2018-06-08T10:34:56+08:00\","
                + "\"amount\": {"
                + "\"total\":" + record.getDemandAmount() + ","
                + "\"currency\":\"CNY\""
                + "},"
                + "\"payer\": {"
                + "\"openid\":" + openid
                + "},"
                + "\"mchid\":\"" + mchid + "\","
                + "\"description\":\""+String.format("自-%s",record.getPersonName())+"\","
                + "\"notify_url\":\"" + payNotifyUrl + "\","
                + "\"out_trade_no\":\"" + record.getSn() + "\","
//                + "\"goods_tag\":\"WXG\","
                + "\"appid\":\"" + appId + "\","
                + "\"attach\":\""+new Gson().toJson(attach).replace("\"","'")+"\""
                + "}";
        StringEntity entity = new StringEntity(reqdata, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                String json = EntityUtils.toString(response.getEntity());
//                CJSystem.logging().info("微信支付预定单：success,return body = " + json);
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    CJSystem.logging().info(getClass(),header.getName() + "=" + header.getValue());
                }
                //success,return body = {"prepay_id":"wx20201904064309b4d901e44376330f0000"}
                /* 返回头
                	Server=nginx
                    Date=Sat, 20 Feb 2021 18:47:31 GMT
                    Content-Type=application/json; charset=utf-8
                    Content-Length=52
                    Connection=keep-alive
                    Keep-Alive=timeout=8
                    Cache-Control=no-cache, must-revalidate
                    X-Content-Type-Options=nosniff
                    Request-ID=08C3B2C5810610A80118AF98EC5D208D4E28AD9E01-0
                    Content-Language=zh-CN
                    Wechatpay-Nonce=9ce6aee674b11312c39fe8ab4c2449a7
                    Wechatpay-Signature=OSeWZe+uCTiYLfSC1WrFy0r3+fdtNI4F8wggeAcOYDn/DzFgRjSzhJExskdfrPT7/xyJKzv8vCMyQ9BUTyaLDcmJN7DGshJQNNp1GnBVN3t5KLvIjRh/oJx/7tD8UY2lgwyFTXIpYeRwAr6BDCpmiFVYhW65sNSqWuxg5ipN62H08l2xt78ETJhk8vsabkmQpXqaxuFL+61atwf5vc1g/N3tt4/qjoh7MIihddPmVtKqk6cOpEDyn2K/O1DjyxByt5UALfmNsnjaQRKefJVRQzKZjPqU6moljWEakLxFoqil8T8xiskN8S6muS4oCexUYAw2GO5+pbl8T1sIHx2Rpw==
                    Wechatpay-Timestamp=1613846851
                    Wechatpay-Serial=31017060CA9811043802FC6316792E0B7001AEAE

                 */
                Map<String, Object> result = new Gson().fromJson(json, HashMap.class);
                String prepay_id = (String) result.get("prepay_id");
                String Nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();
                String Signature = response.getFirstHeader("Wechatpay-Signature").getValue();
                String Timestamp = response.getFirstHeader("Wechatpay-Timestamp").getValue();
                Map<String, Object> params = new HashMap<>();
                params.put("appid", account.getAppId());
                params.put("partnerid", account.getMchId());
                params.put("prepayid", prepay_id);
                params.put("noncestr", Nonce);
                params.put("sign", Signature);
                params.put("timestamp", Long.valueOf(Timestamp));
                params.put("package", "Sign=WXPay");
                return params;
            } else if (statusCode == 204) { //处理成功，无返回Body
//                CJSystem.logging().error(getClass(), "success");
                throw new IOException("处理成功，无返回Body");
            } else {
//                CJSystem.logging().error("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            response.close();
        }
    }

    public Map<String, Object> createAppOrder(CloseableHttpClient httpClient, ChannelAccount account, RechargeRecord record) throws Exception {
        //请求URL
        HttpPost httpPost = new HttpPost(account.getServiceUrl());
        // 请求body参数
        String appId = account.getAppId();
        String mchid = account.getMchId();
        String payNotifyUrl = account.getPayNotifyUrl();
        Map<String, String> attach = new HashMap<>();
        attach.put("channelAccount",record.getToChannelAccount());
        attach.put("person",record.getPerson());
        attach.put("nickName",record.getPersonName());
        String reqdata = "{"
//                + "\"time_expire\":\"2018-06-08T10:34:56+08:00\","
                + "\"amount\": {"
                + "\"total\":" + record.getDemandAmount() + ","
                + "\"currency\":\"CNY\""
                + "},"
                + "\"mchid\":\"" + mchid + "\","
                + "\"description\":\""+String.format("自-%s",record.getPersonName())+"\","
                + "\"notify_url\":\"" + payNotifyUrl + "\","
                + "\"out_trade_no\":\"" + record.getSn() + "\","
//                + "\"goods_tag\":\"WXG\","
                + "\"appid\":\"" + appId + "\","
                + "\"attach\":\""+new Gson().toJson(attach).replace("\"","'")+"\""
                + "}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                String json = EntityUtils.toString(response.getEntity());
//                CJSystem.logging().info("微信支付预定单：success,return body = " + json);
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    CJSystem.logging().info(getClass(), header.getName() + "=" + header.getValue());
                }
                //success,return body = {"prepay_id":"wx20201904064309b4d901e44376330f0000"}
                /* 返回头
                	Server=nginx
                    Date=Sat, 20 Feb 2021 18:47:31 GMT
                    Content-Type=application/json; charset=utf-8
                    Content-Length=52
                    Connection=keep-alive
                    Keep-Alive=timeout=8
                    Cache-Control=no-cache, must-revalidate
                    X-Content-Type-Options=nosniff
                    Request-ID=08C3B2C5810610A80118AF98EC5D208D4E28AD9E01-0
                    Content-Language=zh-CN
                    Wechatpay-Nonce=9ce6aee674b11312c39fe8ab4c2449a7
                    Wechatpay-Signature=OSeWZe+uCTiYLfSC1WrFy0r3+fdtNI4F8wggeAcOYDn/DzFgRjSzhJExskdfrPT7/xyJKzv8vCMyQ9BUTyaLDcmJN7DGshJQNNp1GnBVN3t5KLvIjRh/oJx/7tD8UY2lgwyFTXIpYeRwAr6BDCpmiFVYhW65sNSqWuxg5ipN62H08l2xt78ETJhk8vsabkmQpXqaxuFL+61atwf5vc1g/N3tt4/qjoh7MIihddPmVtKqk6cOpEDyn2K/O1DjyxByt5UALfmNsnjaQRKefJVRQzKZjPqU6moljWEakLxFoqil8T8xiskN8S6muS4oCexUYAw2GO5+pbl8T1sIHx2Rpw==
                    Wechatpay-Timestamp=1613846851
                    Wechatpay-Serial=31017060CA9811043802FC6316792E0B7001AEAE

                 */
                Map<String, Object> result = new Gson().fromJson(json, HashMap.class);
                String prepay_id = (String) result.get("prepay_id");
                String Nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();
                String Signature = response.getFirstHeader("Wechatpay-Signature").getValue();
                String Timestamp = response.getFirstHeader("Wechatpay-Timestamp").getValue();
                Map<String, Object> params = new HashMap<>();
                params.put("appid", account.getAppId());
                params.put("partnerid", account.getMchId());
                params.put("prepayid", prepay_id);
                params.put("noncestr", Nonce);
                params.put("sign", Signature);
                params.put("timestamp", Long.valueOf(Timestamp));
                params.put("package", "Sign=WXPay");
                return params;
            } else if (statusCode == 204) { //处理成功，无返回Body
//                CJSystem.logging().error(getClass(), "success");
                throw new IOException("处理成功，无返回Body");
            } else {
//                CJSystem.logging().error("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            response.close();
        }
    }

    private CloseableHttpClient getClient(ChannelAccount account) throws CircuitException {
        String mchId = account.getMchId();
        String mchSerialNo = account.getMchSerialNo();//在商户证书中查看，或到商户平台查看api证书
        String privateKey = account.getPrivateKey();
        String apiV3Key = account.getApiV3Key();
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = null;
        try {
            merchantPrivateKey = PemUtil
                    .loadPrivateKey(new ByteArrayInputStream(privateKey.getBytes("utf-8")));
            // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3秘钥）
            AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)), apiV3Key.getBytes("utf-8"));

            // 初始化httpClient
            CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                    .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                    .withValidator(new WechatPay2Validator(verifier)).build();
            return httpClient;
        } catch (Exception e) {
            throw new CircuitException("500", e);
        }
    }

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径  apiclient_key.pem
     * @return 私钥对象
     */
    public static String getPrivateKey(String filename) throws Exception {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey pkey = kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            return Base64Utils.encode(pkey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }
}

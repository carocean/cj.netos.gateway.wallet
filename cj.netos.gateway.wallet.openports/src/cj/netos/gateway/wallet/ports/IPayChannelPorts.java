package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.ChannelRatio;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.result.PayChannelResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.PKeyInRequest;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "第三方支付渠道配置")
public interface IPayChannelPorts extends IOpenportService {
    @CjOpenport(usage = "加载并更新渠道配置，该方法可以多次调用", command = "post")
    void config(ISecuritySession securitySession,
                @CjOpenportParameter(usage = "配置信息", name = "data", in = PKeyInRequest.content, simpleModelFile = "/pay_channel_config.md") String data
    ) throws CircuitException;

    @CjOpenport(usage = "添加渠道")
    void addPayChannel(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "渠道代码，如alipay,wechat", name = "code") String code,
                       @CjOpenportParameter(usage = "渠道名", name = "name") String name,
                       @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "获取渠道")
    PayChannel getPayChannel(ISecuritySession securitySession,
                             @CjOpenportParameter(usage = "渠道代码", name = "code") String code
    ) throws CircuitException;

    @CjOpenport(usage = "移除渠道")
    void removePayChannel(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "渠道代码", name = "code") String code
    ) throws CircuitException;

    @CjOpenport(usage = "分页查询渠道")
    List<PayChannelResult> pagePayChannel(ISecuritySession securitySession,
                                          @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                          @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "添加公众卡")
    void addPersonCard(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "卡号", name = "cardSn") String cardSn,
                       @CjOpenportParameter(usage = "持卡人", name = "cardHolder") String cardHolder,
                       @CjOpenportParameter(usage = "卡归属行", name = "cardArriBank") String cardArriBank,
                       @CjOpenportParameter(usage = "开户行", name = "cardPubBank") String cardPubBank,
                       @CjOpenportParameter(usage = "卡类型：0- 储蓄卡\n" +
                               "1- 信用卡\n" +
                               "2- 积分卡", name = "cardType") int cardType,
                       @CjOpenportParameter(usage = "开户手机号", name = "cardPhone") String cardPhone,
                       @CjOpenportParameter(usage = "公众账户的支付渠道\n" +
                               "0 chinapay银联（表示为银行卡账户）\n" +
                               "1 alipay支付宝（个人的支付宝账户）\n" +
                               "2 wechatpay 微信（个人的微信账户）", name = "cardPhone") String payChannel,
                       @CjOpenportParameter(usage = "支付密码，如果有", name = "payPwd") String payPwd
    ) throws CircuitException;

    @CjOpenport(usage = "创建公众卡")
    PersonCard createPersonCardByAuthCode(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "卡号", name = "payChannel") String payChannel,
            @CjOpenportParameter(usage = "卡号", name = "authCode") String authCode
    ) throws CircuitException;

    @CjOpenport(usage = "获取公众卡")
    PersonCard getPersonCardById(ISecuritySession securitySession,
                                 @CjOpenportParameter(usage = "公众卡标识", name = "id") String id
    ) throws CircuitException;

    @CjOpenport(usage = "获取公众卡")
    PersonCard getPersonCard(ISecuritySession securitySession,
                             @CjOpenportParameter(usage = "公众账户的支付渠道\n" +
                                     "0 chinapay银联（表示为银行卡账户）\n" +
                                     "1 alipay支付宝（个人的支付宝账户）\n" +
                                     "2 wechatpay 微信（个人的微信账户）", name = "payChannel") String payChannel
    ) throws CircuitException;

    @CjOpenport(usage = "获取公众卡")
    long totalPersonCard(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "分页查询访问者的所有公众卡")
    List<PersonCard> pagePersonCard(ISecuritySession securitySession,
                                    @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                    @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "获取公众卡")
    void removePersonCard(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "公众卡标识", name = "id") String id
    ) throws CircuitException;

    @CjOpenport(usage = "添加渠道账户")
    void addAccount(ISecuritySession securitySession,
                    @CjOpenportParameter(usage = "渠道代码", name = "channel") String channel,
                    @CjOpenportParameter(usage = "渠道颁发的应用标识", name = "appid") String appid,
                    @CjOpenportParameter(usage = "渠道中的商户号", name = "mchid") String mchid,
                    @CjOpenportParameter(usage = "渠道的服务地址", name = "serviceUrl") String serviceUrl,
                    @CjOpenportParameter(usage = "渠道的支付通知回调地址", name = "payNotifyUrl") String payNotifyUrl,
                    @CjOpenportParameter(usage = "渠道的商户（我方）转到渠道个人户通知回调地址", name = "transNotifyUrl") String transNotifyUrl,
                    @CjOpenportParameter(usage = "是否采用证书加签", name = "useCert") int useCert,
                    @CjOpenportParameter(usage = "渠道颁发的应用公钥", name = "publicKey") String publicKey,
                    @CjOpenportParameter(usage = "渠道颁发的应用私钥", name = "privateKey") String privateKey,
                    @CjOpenportParameter(usage = "专门用于微信。商户的apiv3密钥，即在设置商户api密钥时商户自定义的32位串", name = "apiV3Key") String apiV3Key,
                    @CjOpenportParameter(usage = "专用于微信。商户证书序列号，在商户后台账号中心的api安全中点查看证书可见", name = "mchSerialNo") String mchSerialNo,
                    @CjOpenportParameter(usage = "如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储", name = "certPath1") String certPath1,
                    @CjOpenportParameter(usage = "如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储", name = "certPath2") String certPath2,
                    @CjOpenportParameter(usage = "如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储", name = "certPath3") String certPath3,
                    @CjOpenportParameter(usage = "如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储", name = "certPath4") String certPath4,
                    @CjOpenportParameter(usage = "key的发布日期，单位为分,0表示无限额", name = "keyPubtime") String keyPubtime,
                    @CjOpenportParameter(usage = "key的过期时间，单位为分,0表示无限额", name = "keyExpire") long keyExpire,
                    @CjOpenportParameter(usage = "充值到渠道的选举权重", name = "weight") int weight,
                    @CjOpenportParameter(usage = "渠道限额，单位为分,0表示无限额", name = "limitAmount") long limitAmount,
                    @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "移除渠道账户")
    void removeAccount(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "渠道账户标识", name = "accountid") String accountid
    ) throws CircuitException;

    @CjOpenport(usage = "获取渠道账户")
    ChannelAccount getAccount(ISecuritySession securitySession,
                              @CjOpenportParameter(usage = "渠道账户标识", name = "accountid") String accountid
    ) throws CircuitException;

    @CjOpenport(usage = "分页指定渠道下的账户")
    List<ChannelAccount> pageAccountOfChannel(ISecuritySession securitySession,
                                              @CjOpenportParameter(usage = "渠道代码", name = "channel") String channel,
                                              @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                              @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页指定条件下的账户")
    List<ChannelAccount> pageAccountBy(ISecuritySession securitySession,
                                       @CjOpenportParameter(usage = "渠道代码", name = "channel") String channel,
                                       @CjOpenportParameter(usage = "应用于终端。值有:app|jsapi等等", name = "applyTerminal") String applyTerminal,
                                       @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                       @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "列出渠道下的费率（仅提现有）")
    List<ChannelRatio> listFeeRatioOfChannel(ISecuritySession securitySession,
                                             @CjOpenportParameter(usage = "渠道代码", name = "channel") String channel
    ) throws CircuitException;

    @CjOpenport(usage = "分页查询渠道账号，仅列出应用于app的渠道账号")
    List<ChannelAccount> pageAccount(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                     @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页查询渠道账号，指定应用终端下的渠道账号")
    List<ChannelAccount> pageAccountByTerminal(ISecuritySession securitySession,
                                               @CjOpenportParameter(usage = "应用于终端。值有:app|jsapi等等", name = "applyTerminal") String applyTerminal,
                                               @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                               @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计总账")
    long totalAccountBalance(ISecuritySession securitySession,
                             @CjOpenportParameter(usage = "渠道代码，如果为空表示统计所有渠道", name = "channel") String channel
    ) throws CircuitException;
}

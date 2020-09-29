package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;
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

    @CjOpenport(usage = "移除渠道")
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

    @CjOpenport(usage = "添加渠道账户")
    void addAccount(ISecuritySession securitySession,
                    @CjOpenportParameter(usage = "渠道代码", name = "channel") String channel,
                    @CjOpenportParameter(usage = "渠道颁发的应用标识", name = "appid") String appid,
                    @CjOpenportParameter(usage = "渠道的服务地址", name = "serviceUrl") String serviceUrl,
                    @CjOpenportParameter(usage = "渠道的通知回调地址", name = "notifyUrl") String notifyUrl,
                    @CjOpenportParameter(usage = "渠道颁发的应用公钥", name = "publicKey") String publicKey,
                    @CjOpenportParameter(usage = "渠道颁发的应用私钥", name = "privateKey") String privateKey,
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

    @CjOpenport(usage = "分页查询渠道")
    List<ChannelAccount> pageAccount(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                     @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;
}

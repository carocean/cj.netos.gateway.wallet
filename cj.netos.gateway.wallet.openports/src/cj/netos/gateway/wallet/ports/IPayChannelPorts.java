package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.bo.PayChannelBO;
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

    @CjOpenport(usage = "添加渠道", command = "post")
    void addPayChannel(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "配置信息", name = "payChannel", in = PKeyInRequest.content, simpleModelFile = "/pay_channel.md") PayChannelBO payChannel
    ) throws CircuitException;

    @CjOpenport(usage = "移除渠道")
    void removePayChannel(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "支付渠道号", name = "payChannelID") String payChannelID
    ) throws CircuitException;

    @CjOpenport(usage = "分页查询渠道")
    List<PayChannelResult> pagePayChannel(ISecuritySession securitySession,
                                          @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                          @CjOpenportParameter(usage = "偏移位置", name = "offset") long offset
    ) throws CircuitException;
}

package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.bo.PayChannelBO;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.netos.gateway.wallet.result.PayChannelResult;
import cj.netos.gateway.wallet.util.WalletUtils;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.ultimate.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@CjService(name = "/partner/payChannel.ports")
public class PayChannelPorts implements IPayChannelPorts {

    @CjServiceRef
    IPayChannelService payChannelService;

    @Override
    public void addPayChannel(ISecuritySession securitySession, PayChannelBO payChannel) throws CircuitException {
        if (StringUtil.isEmpty(payChannel.getCode())) {
            throw new CircuitException("404", "code 参数为空");
        }
        if (StringUtil.isEmpty(payChannel.getAppid())) {
            throw new CircuitException("404", "appid 参数为空");
        }
        if (StringUtil.isEmpty(payChannel.getChannelName())) {
            throw new CircuitException("404", "channelName 参数为空");
        }
        if (StringUtil.isEmpty(payChannel.getUrl())) {
            throw new CircuitException("404", "url 参数为空");
        }
        PayChannel channel = new PayChannel();
        payChannel.copyTo(channel);
        channel.setCtime(WalletUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        payChannelService.addPayChannel(channel);
    }

    @Override
    public void removePayChannel(ISecuritySession securitySession, String payChannelID) throws CircuitException {
        payChannelService.removePayChannel(payChannelID);
    }

    @Override
    public List<PayChannelResult> pagePayChannel(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        List<PayChannel> list = payChannelService.pagePayChannel(limit, offset);
        List<PayChannelResult> results = new ArrayList<>();
        for (PayChannel channel : list) {
            PayChannelResult result = new PayChannelResult();
            result.copy(channel);
            results.add(result);
        }
        return results;
    }
}

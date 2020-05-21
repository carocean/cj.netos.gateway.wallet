package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.bo.PayChannelBO;
import cj.netos.gateway.wallet.result.PayChannelResult;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

@CjService(name = "/partner/payChannel.ports")
public class PayChannelPorts implements IPayChannelPorts {

    @CjServiceRef
    IPayChannelService payChannelService;

    @Override
    public PayChannelResult addPayChannel(ISecuritySession securitySession, PayChannelBO payChannel) throws CircuitException {
        return null;
    }

    @Override
    public PayChannelResult removePayChannel(ISecuritySession securitySession, String payChannelID) throws CircuitException {
        return null;
    }

    @Override
    public PayChannelResult pagePayChannel(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return null;
    }
}

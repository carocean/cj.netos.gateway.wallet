package cj.netos.gateway.wallet.ports;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

@CjService(name = "/trade/settle.ports")
public class SettleTradePorts implements ISettleTradePorts {
    @Override
    public void recharge(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {

    }

    @Override
    public void withdraw(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {

    }
}

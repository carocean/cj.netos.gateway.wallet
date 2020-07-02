package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IPayActivityController;
import cj.netos.gateway.wallet.IRechargeActivityController;
import cj.netos.gateway.wallet.IWithdrawActivityController;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

@CjService(name = "/trade/settle.ports")
public class SettleTradePorts implements ISettleTradePorts {
    @CjServiceRef
    IRechargeActivityController rechargeActivityController;
    @CjServiceRef
    IWithdrawActivityController withdrawActivityController;
    @CjServiceRef
    IPayActivityController payActivityController;

    @Override
    public void recharge(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {
        rechargeActivityController.settle(securitySession.principal(), sn, amount, code, message);
    }

    @Override
    public void withdraw(ISecuritySession securitySession, String sn, long amount, String code, String message) throws CircuitException {
        withdrawActivityController.settle(securitySession.principal(), sn, amount, code, message);

    }
}

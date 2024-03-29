package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IBalanceBillService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/balance.ports")
public class BalanceBillPorts implements IBalanceBillPorts {
    @CjServiceRef
    IBalanceBillService balanceBillService;

    @Override
    public List<Object> pageBill(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return balanceBillService.pageBill(securitySession.principal(), limit, offset);
    }

    @Override
    public List<Object> pageBillByOrder(ISecuritySession securitySession, int order, int limit, long offset) throws CircuitException {
        return balanceBillService.pageBillByOrder(securitySession.principal(), order,limit, offset);
    }

    @Override
    public List<Object> monthBill(ISecuritySession securitySession, int year, int month, int limit, long offset) throws CircuitException {
        return balanceBillService.monthBill(securitySession.principal(), year, month, limit, offset);
    }
}

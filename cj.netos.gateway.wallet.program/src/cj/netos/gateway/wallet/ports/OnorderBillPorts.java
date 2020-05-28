package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IAbsorbBillService;
import cj.netos.gateway.wallet.IOnorderBillService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/onorder.ports")
public class OnorderBillPorts implements IOnorderBillPorts {
    @CjServiceRef
    IOnorderBillService onorderBillService;

    @Override
    public List<Object> pageBill(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return onorderBillService.pageBill(securitySession.principal(), limit, offset);
    }

    @Override
    public List<Object> monthBill(ISecuritySession securitySession, int year, int month, int limit, long offset) throws CircuitException {
        return onorderBillService.monthBill(securitySession.principal(), year, month, limit, offset);
    }
}

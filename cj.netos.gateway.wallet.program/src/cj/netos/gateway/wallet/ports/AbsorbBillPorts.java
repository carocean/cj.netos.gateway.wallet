package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IAbsorbBillService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/absorb.ports")
public class AbsorbBillPorts implements IAbsorbBillPorts {
    @CjServiceRef
    IAbsorbBillService absorbBillService;

    @Override
    public List<Object> pageBill(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return absorbBillService.pageBill(securitySession.principal(), limit, offset);
    }

    @Override
    public List<Object> monthBill(ISecuritySession securitySession, int year, int month, int limit, long offset) throws CircuitException {
        return absorbBillService.monthBill(securitySession.principal(), year, month, limit, offset);
    }
}

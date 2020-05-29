package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IProfitBillService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/profit.ports")
public class ProfitBillPorts implements IProfitBillPorts {
    @CjServiceRef
    IProfitBillService profitBillService;

    @Override
    public List<Object> pageBill(ISecuritySession securitySession,String wenyBankID, int limit, long offset) throws CircuitException {
        return profitBillService.pageBill(securitySession.principal(),wenyBankID, limit, offset);
    }

    @Override
    public List<Object> monthBill(ISecuritySession securitySession,String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        return profitBillService.monthBill(securitySession.principal(),wenyBankID, year, month, limit, offset);
    }
}

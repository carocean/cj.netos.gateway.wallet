package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IAbsorbBillService;
import cj.netos.gateway.wallet.IStockBillService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/bill/stock.ports")
public class StockBillPorts implements IStockBillPorts {
    @CjServiceRef
    IStockBillService stockBillService;

    @Override
    public List<Object> pageBill(ISecuritySession securitySession, String wenyBankID,int limit, long offset) throws CircuitException {
        return stockBillService.pageBill(securitySession.principal(),wenyBankID, limit, offset);
    }

    @Override
    public List<Object> monthBill(ISecuritySession securitySession,String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        return stockBillService.monthBill(securitySession.principal(),wenyBankID, year, month, limit, offset);
    }
}

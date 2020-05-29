package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IBalanceService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.Map;

@CjService(name = "/balance.ports")
public class BalancePorts implements IBalancePorts {
    @CjServiceRef
    IBalanceService balanceService;

    @Override
    public Map<String, Object> getAllAccount(ISecuritySession securitySession) throws CircuitException {
        return balanceService.getAllAccount(securitySession.principal());
    }

    @Override
    public Map<String, Object> getRootAccount(ISecuritySession securitySession) throws CircuitException {
        return balanceService.getRootAccount(securitySession.principal());
    }

    @Override
    public Map<String, Object> getBalanceAccount(ISecuritySession securitySession) throws CircuitException {
        return balanceService.getBalanceAccount(securitySession.principal());
    }

    @Override
    public Map<String, Object> getAbsorbAccount(ISecuritySession securitySession) throws CircuitException {
        return balanceService.getAbsorbAccount(securitySession.principal());
    }

    @Override
    public Map<String, Object> getFreezenAccount(ISecuritySession securitySession,String wenyBankID) throws CircuitException {
        return balanceService.getFreezenAccount(securitySession.principal(),wenyBankID);
    }

    @Override
    public Map<String, Object> getProfitAccount(ISecuritySession securitySession,String wenyBankID) throws CircuitException {
        return balanceService.getProfitAccount(securitySession.principal(),wenyBankID);
    }

    @Override
    public Map<String, Object> getWenyAccounts(ISecuritySession securitySession) throws CircuitException {
        return balanceService.getWenyAccounts(securitySession.principal());
    }

    @Override
    public Map<String, Object> getStockAccount(ISecuritySession securitySession, String wenyBankID) throws CircuitException {
        return balanceService.getStockAccount(securitySession.principal(), wenyBankID);
    }
}

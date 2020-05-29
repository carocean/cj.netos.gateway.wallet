package cj.netos.gateway.wallet;

import cj.studio.ecm.net.CircuitException;

import java.util.Map;

public interface IBalanceService {
    Map<String, Object> getAllAccount(String principal) throws CircuitException;

    Map<String, Object> getRootAccount(String principal) throws CircuitException;

    Map<String, Object> getBalanceAccount(String principal) throws CircuitException;

    Map<String, Object> getAbsorbAccount(String principal) throws CircuitException;

    Map<String, Object> getFreezenAccount(String principal, String wenyBankID) throws CircuitException;

    Map<String, Object> getProfitAccount(String principal, String wenyBankID) throws CircuitException;


    Map<String, Object> getWenyAccounts(String principal) throws CircuitException;

    Map<String, Object> getStockAccount(String principal, String wenyBankID) throws CircuitException;


}

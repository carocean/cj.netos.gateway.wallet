package cj.netos.gateway.wallet;

import cj.studio.ecm.net.CircuitException;

import java.util.List;
import java.util.Map;

public interface IBalanceService {
    Map<String, Object> getAllAccount(String principal) throws CircuitException;

    Map<String, Object> getRootAccount(String principal);

    Map<String, Object> getBalanceAccount(String principal) throws CircuitException;

    Map<String, Object> getAbsorbAccount(String principal);

    Map<String, Object> getFreezenAccount(String principal);

    Map<String, Object> getProfitAccount(String principal);

    Map<String, Object> listStockAccount(String principal);

    Map<String, Object> getRelatedStockAccount(String principal);

    Map<String, Object> getStockAccount(String principal, String wenyBankID);


}

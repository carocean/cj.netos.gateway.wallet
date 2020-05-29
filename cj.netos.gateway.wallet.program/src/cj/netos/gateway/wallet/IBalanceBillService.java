package cj.netos.gateway.wallet;

import cj.studio.ecm.net.CircuitException;

import java.util.List;

public interface IBalanceBillService {
    List<Object> pageBill(String principal, int limit, long offset) throws CircuitException;

    List<Object> monthBill(String principal, int year, int month, int limit, long offset) throws CircuitException;

}

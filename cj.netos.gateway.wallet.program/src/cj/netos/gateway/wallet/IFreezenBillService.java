package cj.netos.gateway.wallet;

import cj.studio.ecm.net.CircuitException;

import java.util.List;

public interface IFreezenBillService {
    List<Object> pageBill(String principal, String wenyBankID, int limit, long offset) throws CircuitException;

    List<Object> monthBill(String principal, String wenyBankID, int year, int month, int limit, long offset) throws CircuitException;

}

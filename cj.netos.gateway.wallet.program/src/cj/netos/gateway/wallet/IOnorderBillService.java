package cj.netos.gateway.wallet;

import java.util.List;

public interface IOnorderBillService {
    List<Object> pageBill(String principal, int limit, long offset);

    List<Object> monthBill(String principal, int year, int month, int limit, long offset);

}

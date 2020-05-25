package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.studio.ecm.net.CircuitException;

public interface IExchangeActivityController {
    WenyExchangeRecord doReceipt(String principal, String personName, String purchRecord, String note) throws CircuitException;

}

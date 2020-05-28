package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.result.ExchangedResult;
import cj.netos.gateway.wallet.result.ExchangingResult;
import cj.studio.ecm.net.CircuitException;

public interface IExchangeActivityController {
    WenyExchangeRecord doReceipt(String principal, String personName, String purchRecord, String note) throws CircuitException;

    void settle(ExchangedResult result, String status, String message) throws CircuitException;

    void ackReceipt(ExchangingResult result);

    void ackSettle(ExchangingResult result);

}

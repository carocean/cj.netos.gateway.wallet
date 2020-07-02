package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.PayRecord;
import cj.netos.gateway.wallet.result.PayResult;
import cj.studio.ecm.net.CircuitException;

public interface IPayActivityController {
    PayRecord doReceipt(String principal, String personName, long amount, int type, PayDetailsBO details, String note) throws CircuitException;

    void ackReceipt(PayResult result);

}

package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.TransAbsorbRecord;
import cj.netos.gateway.wallet.result.TransAbsorbResult;
import cj.studio.ecm.net.CircuitException;

public interface ITransferAbsorbActivityController {
    TransAbsorbRecord doReceipt(String principal, String personName,  long amount, String note) throws CircuitException;

    void ackReceipt(TransAbsorbResult result);

}

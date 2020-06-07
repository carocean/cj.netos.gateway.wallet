package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.TransProfitRecord;
import cj.netos.gateway.wallet.result.TransProfitResult;
import cj.studio.ecm.net.CircuitException;

public interface ITransferProfitActivityController {
    TransProfitRecord doReceipt(String principal, String personName, String wenyBankID, long amount, String note) throws CircuitException;

    void ackReceipt(TransProfitResult result);

}

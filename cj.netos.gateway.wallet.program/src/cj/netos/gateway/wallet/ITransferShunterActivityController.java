package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.TransShunterRecord;
import cj.netos.gateway.wallet.result.TransShuntResult;
import cj.netos.gateway.wallet.result.WithdrawShunterResult;
import cj.studio.ecm.net.CircuitException;

public interface ITransferShunterActivityController {
    TransShunterRecord doReceipt(String principal, String personName, String wenyBankID, String shunter, long amount, String note) throws CircuitException;


    void ackReceipt(TransShuntResult result);
//
    void settle(WithdrawShunterResult result, String status, String message) throws CircuitException;
//
//
    void ackSettle(TransShuntResult purchaseResult);
}

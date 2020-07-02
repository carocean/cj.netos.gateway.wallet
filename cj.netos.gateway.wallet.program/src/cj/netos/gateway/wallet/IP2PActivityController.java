package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.P2pRecord;
import cj.netos.gateway.wallet.result.P2PResult;
import cj.studio.ecm.net.CircuitException;

public interface IP2PActivityController {
    P2pRecord doReceipt(String payer, String payerName, String payee, String payeeName, long amount, int type,/*转账方向，to|from*/String direct, String note) throws CircuitException;

    void ackReceipt(P2PResult result);

}

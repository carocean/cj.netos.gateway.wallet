package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.result.ModuleTransinResult;
import cj.studio.ecm.net.CircuitException;

public interface IModuleTransInActivityController {
    void doReceipt(String moduleId, String moduleTitle, String person, String nickName,String payer,String payerName, long amount, String note) throws CircuitException;

    void ackReceipt(ModuleTransinResult result);
}

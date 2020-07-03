package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.DepositHubTailsRecord;
import cj.netos.gateway.wallet.result.DepositHubTailsResult;
import cj.studio.ecm.net.CircuitException;

public interface IDepositHubTailsActivityController {
    DepositHubTailsRecord doReceipt(AbsorberHubTailsResult result)throws CircuitException;

    void ackReceipt(DepositHubTailsResult result);

}

package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.model.DepositTrialRecord;
import cj.netos.gateway.wallet.model.TrialFundsConfig;
import cj.netos.gateway.wallet.result.DepositTrialFundsResult;
import cj.studio.ecm.net.CircuitException;

public interface IDepositTrialFundsActivityController {
    TrialFundsConfig getConfig();

    DepositTrialRecord doReceipt(PayBO bo) throws CircuitException;

    void ackReceipt(DepositTrialFundsResult result);

}

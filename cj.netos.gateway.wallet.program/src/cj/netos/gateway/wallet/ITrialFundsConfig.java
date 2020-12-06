package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PayBO;
import cj.netos.gateway.wallet.model.TrialFundsConfig;

public interface ITrialFundsConfig {
    TrialFundsConfig getConfig();

    void configTrial(String remitAccount, String remitName, long trialAmount);

    void updateState(int i);

    void updateRemitAccount(String remitAccount, String remitName);

    void updateTrialAmount(long trialAmount);

}

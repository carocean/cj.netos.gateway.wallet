package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.OnorderBO;
import cj.studio.ecm.net.CircuitException;

public interface IWalletAccountCaller {
    void tryPutOnorder(OnorderBO onorderBO) throws CircuitException;

    void removeOnorder(OnorderBO onorderBO) throws CircuitException;
}

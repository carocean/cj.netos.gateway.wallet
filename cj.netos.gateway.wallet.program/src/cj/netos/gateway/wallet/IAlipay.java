package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.studio.ecm.net.CircuitException;

public interface IAlipay {
    String pay(RechargeRecord record) throws CircuitException;

}

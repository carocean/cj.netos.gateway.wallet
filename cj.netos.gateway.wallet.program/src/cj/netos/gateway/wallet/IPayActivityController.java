package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.PayRecord;
import cj.studio.ecm.net.CircuitException;

public interface IPayActivityController {
    PayRecord doReceipt(String principal, String personName, long amount, int type, PayDetailsBO details, String note) throws CircuitException;

    void payment(String principal, String payment_sn) throws CircuitException;

    void refund(String principal, String payment_sn) throws CircuitException;

}

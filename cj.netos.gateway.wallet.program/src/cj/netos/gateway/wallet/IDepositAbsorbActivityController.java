package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.result.DepositAbsorbResult;
import cj.studio.ecm.net.CircuitException;

import java.math.BigDecimal;

public interface IDepositAbsorbActivityController {
    DepositAbsorbRecord doReceipt(String principal, String personName, BigDecimal amount, String sourceCode, String sourceTitle, String note) throws CircuitException;

    void ackReceipt(DepositAbsorbResult result);

}

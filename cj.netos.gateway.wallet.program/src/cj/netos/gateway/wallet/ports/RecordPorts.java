package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/record.ports")
public class RecordPorts implements IRecordPorts {
    @CjServiceRef
    IRecordService recordService;

    @Override
    public RechargeRecord getRechargeRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getRechargeRecord(securitySession.principal(),record_sn);
    }

    @Override
    public WithdrawRecord getWithdrawRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getWithdrawRecord(securitySession.principal(),record_sn);
    }

    @Override
    public WenyPurchRecord getPurchaseRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getPurchaseRecordOfPerson(securitySession.principal(),record_sn);
    }

    @Override
    public WenyExchangeRecord getExchangeRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getExchangeRecord(securitySession.principal(),record_sn);
    }

    @Override
    public List<RechargeRecord> pageRechargeRecord(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return recordService.pageRechargeRecord(securitySession.principal(),limit,offset);
    }

    @Override
    public List<WithdrawRecord> pageWithdrawRecord(ISecuritySession securitySession,  int limit, long offset) throws CircuitException {
        return recordService.pageWithdrawRecord(securitySession.principal(),limit,offset);
    }

    @Override
    public List<WenyPurchRecord> pagePurchaseRecord(ISecuritySession securitySession,int limit, long offset) throws CircuitException {
        return recordService.pagePurchaseRecord(securitySession.principal(),limit,offset);
    }

    @Override
    public List<WenyExchangeRecord> pageExchangeRecord(ISecuritySession securitySession,  int limit, long offset) throws CircuitException {
        return recordService.pageExchangeRecord(securitySession.principal(),limit,offset);
    }
}

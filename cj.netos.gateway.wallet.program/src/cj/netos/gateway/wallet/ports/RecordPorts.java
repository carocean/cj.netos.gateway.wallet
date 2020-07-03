package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.IRecordService;
import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.PaymentResult;
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
        return recordService.getRechargeRecord(securitySession.principal(), record_sn);
    }

    @Override
    public List<RechargeActivity> getRechargeActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getRechargeActivities(securitySession.principal(), record_sn);
    }

    @Override
    public WithdrawRecord getWithdrawRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getWithdrawRecord(securitySession.principal(), record_sn);
    }

    @Override
    public List<WithdrawActivity> getWithdrawActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getWithdrawActivities(securitySession.principal(), record_sn);
    }

    @Override
    public WenyPurchRecord getPurchaseRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getPurchaseRecordOfPerson(securitySession.principal(), record_sn);
    }

    @Override
    public PaymentResult getPayRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        PayRecord record = recordService.getPayment(securitySession.principal(), record_sn);
        if (record == null) {
            return null;
        }
        PayDetails details = recordService.getPayDetails(record_sn);
        PaymentResult result = new PaymentResult();
        result.load(record);
        result.setDetails(details);
        return result;
    }

    @Override
    public P2pRecord getP2PRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        P2pRecord record = recordService.getP2PRecord(securitySession.principal(), record_sn);
        return record;
    }

    @Override
    public List<P2pActivity> getP2PActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getP2PActivities(record_sn);
    }

    @Override
    public DepositHubTailsRecord getDepositHubTailsRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        DepositHubTailsRecord record = recordService.getDepositHubTailsRecord(securitySession.principal(), record_sn);
        return record;
    }

    @Override
    public List<DepositHubTailsActivity> getDepositHubTailsActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getDepositHubTailsActivities(record_sn);
    }

    @Override
    public List<PayActivity> getPayActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getPayActivities(record_sn);
    }

    @Override
    public List<WenyPurchActivity> getPurchaseActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getPurchaseActivities(securitySession.principal(), record_sn);
    }

    @Override
    public WenyExchangeRecord getExchangeRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getExchangeRecord(securitySession.principal(), record_sn);
    }

    @Override
    public List<WenyExchangeActivity> getExchangeActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getExchangeActivities(securitySession.principal(), record_sn);
    }

    @Override
    public WenyExchangeRecord getExchangeRecordByPurchase(ISecuritySession securitySession, String purchase_sn) throws CircuitException {
        return recordService.getExchangeRecordByPurchase(securitySession.principal(), purchase_sn);
    }

    @Override
    public List<RechargeRecord> pageRechargeRecord(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return recordService.pageRechargeRecord(securitySession.principal(), limit, offset);
    }

    @Override
    public List<WithdrawRecord> pageWithdrawRecord(ISecuritySession securitySession, int limit, long offset) throws CircuitException {
        return recordService.pageWithdrawRecord(securitySession.principal(), limit, offset);
    }

    @Override
    public List<WenyPurchRecord> pagePurchaseRecord(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return recordService.pagePurchaseRecord(securitySession.principal(), wenyBankID, limit, offset);
    }

    @Override
    public List<WenyPurchRecord> pagePurchaseRecordOfUnexchanged(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return recordService.pagePurchaseRecordOfUnexchanged(securitySession.principal(), wenyBankID, limit, offset);
    }

    @Override
    public List<WenyPurchRecord> pagePurchaseRecordOfExchanged(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return recordService.pagePurchaseRecordOfExchanged(securitySession.principal(), wenyBankID, limit, offset);
    }

    @Override
    public List<WenyExchangeRecord> pageExchangeRecord(ISecuritySession securitySession, String wenyBankID, int limit, long offset) throws CircuitException {
        return recordService.pageExchangeRecord(securitySession.principal(), wenyBankID, limit, offset);
    }

    @Override
    public TransProfitRecord getTransProfitRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getTransProfitRecord(securitySession.principal(),record_sn);
    }

    @Override
    public List<TransProfitActivity> getTransProfitActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getTransProfitActivities(securitySession.principal(),record_sn);
    }

    @Override
    public TransAbsorbRecord getTransAbsorbRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getTransAbsorbRecord(securitySession.principal(),record_sn);
    }

    @Override
    public List<TransAbsorbActivity> getTransAbsorbActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getTransAbsorbActivities(securitySession.principal(),record_sn);
    }

    @Override
    public DepositAbsorbRecord getDepositAbsorbRecord(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getDepositAbsorbRecord(securitySession.principal(),record_sn);
    }

    @Override
    public List<DepositAbsorbActivity> getDepositAbsorbActivities(ISecuritySession securitySession, String record_sn) throws CircuitException {
        return recordService.getDepositAbsorbActivities(securitySession.principal(),record_sn);
    }
}

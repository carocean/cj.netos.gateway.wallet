package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.*;
import cj.netos.gateway.wallet.result.PaymentResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "明细流水账服务")
public interface IRecordPorts extends IOpenportService {
    @CjOpenport(usage = "获取充值记录")
    RechargeRecord getRechargeRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取充值步骤")
    List<RechargeActivity> getRechargeActivities(ISecuritySession securitySession,
                                                 @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取提现记录")
    WithdrawRecord getWithdrawRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取提现步骤")
    List<WithdrawActivity> getWithdrawActivities(ISecuritySession securitySession,
                                                 @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取支付记录")
    PaymentResult getPayRecord(ISecuritySession securitySession,
                               @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取支付步骤")
    List<PayActivity> getPayActivities(ISecuritySession securitySession,
                                       @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取系统内转账记录")
    P2pRecord getP2PRecord(ISecuritySession securitySession,
                           @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取系统转账步骤")
    List<P2pActivity> getP2PActivities(ISecuritySession securitySession,
                                       @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器尾金存单")
    DepositHubTailsRecord getDepositHubTailsRecord(ISecuritySession securitySession,
                                                   @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇取器尾金存入步骤")
    List<DepositHubTailsActivity> getDepositHubTailsActivities(ISecuritySession securitySession,
                                                               @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取收益金提取记录")
    TransProfitRecord getTransProfitRecord(ISecuritySession securitySession,
                                           @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取收益金提取步骤")
    List<TransProfitActivity> getTransProfitActivities(ISecuritySession securitySession,
                                                       @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇金提取记录")
    TransAbsorbRecord getTransAbsorbRecord(ISecuritySession securitySession,
                                           @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇金提取步骤")
    List<TransAbsorbActivity> getTransAbsorbActivities(ISecuritySession securitySession,
                                                       @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取账金提取记录")
    TransShunterRecord getTransShunterRecord(ISecuritySession securitySession,
                                             @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取账金提取步骤")
    List<TransShunterActivity> getTransShunterActivities(ISecuritySession securitySession,
                                                         @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取存入洇金记录")
    DepositAbsorbRecord getDepositAbsorbRecord(ISecuritySession securitySession,
                                               @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取存入洇金步骤")
    List<DepositAbsorbActivity> getDepositAbsorbActivities(ISecuritySession securitySession,
                                                           @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;


    @CjOpenport(usage = "获取申购记录")
    WenyPurchRecord getPurchaseRecord(ISecuritySession securitySession,
                                      @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取申购过程步骤")
    List<WenyPurchActivity> getPurchaseActivities(ISecuritySession securitySession,
                                                  @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取承兑记录")
    WenyExchangeRecord getExchangeRecord(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取承兑过程步骤")
    List<WenyExchangeActivity> getExchangeActivities(ISecuritySession securitySession,
                                                     @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "通过申购单获取已承兑记录")
    WenyExchangeRecord getExchangeRecordByPurchase(ISecuritySession securitySession,
                                                   @CjOpenportParameter(usage = "记录标识", name = "purchase_sn") String purchase_sn
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取充值记录")
    List<RechargeRecord> pageRechargeRecord(ISecuritySession securitySession,
                                            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                            @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取提现记录")
    List<WithdrawRecord> pageWithdrawRecord(ISecuritySession securitySession,
                                            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                            @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取申购记录")
    List<WenyPurchRecord> pagePurchaseRecord(ISecuritySession securitySession,
                                             @CjOpenportParameter(usage = "纹银银行行号", name = "wenyBankID") String wenyBankID,
                                             @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                             @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取未承兑的且可以承兑的申购记录")
    List<WenyPurchRecord> pagePurchaseRecordOfUnexchanged(ISecuritySession securitySession,
                                                          @CjOpenportParameter(usage = "纹银银行行号", name = "wenyBankID") String wenyBankID,
                                                          @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                          @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取已承兑的且可以承兑的申购记录")
    List<WenyPurchRecord> pagePurchaseRecordOfExchanged(ISecuritySession securitySession,
                                                        @CjOpenportParameter(usage = "纹银银行行号", name = "wenyBankID") String wenyBankID,
                                                        @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                        @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取承兑记录")
    List<WenyExchangeRecord> pageExchangeRecord(ISecuritySession securitySession,
                                                @CjOpenportParameter(usage = "纹银银行行号", name = "wenyBankID") String wenyBankID,
                                                @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
}

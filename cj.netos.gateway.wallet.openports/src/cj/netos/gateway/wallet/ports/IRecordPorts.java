package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WithdrawRecord;
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

    @CjOpenport(usage = "获取提现记录")
    WithdrawRecord getWithdrawRecord(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取申购记录")
    WenyPurchRecord getPurchaseRecord(ISecuritySession securitySession,
                                      @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
    ) throws CircuitException;

    @CjOpenport(usage = "获取承兑记录")
    WenyExchangeRecord getExchangeRecord(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "记录标识", name = "record_sn") String record_sn
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
                                             @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                             @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "分页获取承兑记录")
    List<WenyExchangeRecord> pageExchangeRecord(ISecuritySession securitySession,
                                                @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
}

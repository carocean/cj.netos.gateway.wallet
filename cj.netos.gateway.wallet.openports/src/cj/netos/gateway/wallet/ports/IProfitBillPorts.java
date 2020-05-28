package cj.netos.gateway.wallet.ports;

import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "收益金出入账单")
public interface IProfitBillPorts extends IOpenportService {
    @CjOpenport(usage = "分页账单，倒序")
    List<Object> pageBill(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                          @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
    @CjOpenport(usage = "月账单，倒序")
    List<Object> monthBill(ISecuritySession securitySession,
                           @CjOpenportParameter(usage = "年", name = "year") int year,
                           @CjOpenportParameter(usage = "月.（java特性，实际用份减1）", name = "month") int month,
                           @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                           @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
}

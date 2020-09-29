package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.ChannelBill;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "支付渠道出入账单")
public interface IPayChannelBillPorts extends IOpenportService {
    @CjOpenport(usage = "分页账单，倒序")
    List<ChannelBill> pageBill(ISecuritySession securitySession,
                               @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                               @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "月账单，倒序")
    List<ChannelBill> monthBill(ISecuritySession securitySession,
                                @CjOpenportParameter(usage = "年", name = "year") int year,
                                @CjOpenportParameter(usage = "月.（java特性，实际用份减1）", name = "month") int month,
                                @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个月充值或提现金额")
    long totalMonthBill(ISecuritySession securitySession,
                        @CjOpenportParameter(usage = "订单类型：0为充值；1为提现；-1为全部类型", name = "order") int order,
                        @CjOpenportParameter(usage = "年", name = "year") int year,
                        @CjOpenportParameter(usage = "月.（java特性，实际用份减1）", name = "month") int month
    ) throws CircuitException;

    @CjOpenport(usage = "分页账单，倒序")
    List<ChannelBill> pageBillByAccount(ISecuritySession securitySession,
                                        @CjOpenportParameter(usage = "渠道账户", name = "channelAccount") String channelAccount,
                                        @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                        @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "月账单，倒序")
    List<ChannelBill> monthBillByAccount(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "渠道账户", name = "channelAccount") String channelAccount,
                                         @CjOpenportParameter(usage = "年", name = "year") int year,
                                         @CjOpenportParameter(usage = "月.（java特性，实际用份减1）", name = "month") int month,
                                         @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                         @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;

    @CjOpenport(usage = "统计一个月充值或提现金额")
    long totalMonthBillByAccount(ISecuritySession securitySession,
                                 @CjOpenportParameter(usage = "渠道账户", name = "channelAccount") String channelAccount,
                                 @CjOpenportParameter(usage = "订单类型：0为充值；1为提现；-1为全部类型", name = "order") int order,
                                 @CjOpenportParameter(usage = "年", name = "year") int year,
                                 @CjOpenportParameter(usage = "月.（java特性，实际用份减1）", name = "month") int month
    ) throws CircuitException;
}

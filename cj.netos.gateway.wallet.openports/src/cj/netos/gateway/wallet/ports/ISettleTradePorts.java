package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.result.PaymentResult;
import cj.netos.gateway.wallet.result.RefundResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.math.BigDecimal;
import java.util.Map;

@CjOpenports(usage = "决清业务开放服务")
public interface ISettleTradePorts extends IOpenportService {


    @CjOpenport(usage = "充值决清。")
    void recharge(ISecuritySession securitySession,
                  @CjOpenportParameter(usage = "订单号", name = "sn") String sn,
                  @CjOpenportParameter(usage = "实际完成充值的金额,单位为分", name = "amount") long amount,
                  @CjOpenportParameter(usage = "订单完成时第三方渠道的返回码", name = "code") String code,
                  @CjOpenportParameter(usage = "订单完成时第三方渠道的返回信息", name = "message") String message
    ) throws CircuitException;


    @CjOpenport(usage = "提现决清")
    void withdraw(ISecuritySession securitySession,
                  @CjOpenportParameter(usage = "订单号", name = "sn") String sn,
                  @CjOpenportParameter(usage = "实际完成提现的金额,单位为分", name = "amount") long amount,
                  @CjOpenportParameter(usage = "订单完成时第三方渠道的返回码", name = "code") String code,
                  @CjOpenportParameter(usage = "订单完成时第三方渠道的返回信息", name = "message") String message
    ) throws CircuitException;

    @CjOpenport(usage = "实际支付")
    void payment(ISecuritySession securitySession,
                 @CjOpenportParameter(usage = "付款单号", name = "payment_sn") String payment_sn
    ) throws CircuitException;

    @CjOpenport(usage = "应付单退款")
    void refund(ISecuritySession securitySession,
                @CjOpenportParameter(usage = "付款单号", name = "payment_sn") String payment_sn
    ) throws CircuitException;
}

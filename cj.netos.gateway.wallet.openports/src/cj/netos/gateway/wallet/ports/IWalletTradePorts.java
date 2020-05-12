package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.bo.TradeDetailsBO;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.PKeyInRequest;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.Map;

@CjOpenports(usage = "钱包交易开放服务")
public interface IWalletTradePorts extends IOpenportService {
    @CjOpenport(usage = "充值下单，从他的其它支付渠道中将款充入余额。返回相应签名渠道的文本")
    Map<String, Object> rechargeOrder(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "币种", name = "currency", defaultValue = "CNY") String currency,
                       @CjOpenportParameter(usage = "欲充值的金额,单位为分", name = "amount") long amount,
                       @CjOpenportParameter(usage = "充值来源渠道号", name = "payChannelID") String payChannelID,
                       @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "充值完成")
    void rechargeDone(ISecuritySession securitySession,
                      @CjOpenportParameter(usage = "订单号", name = "sn") String sn,
                      @CjOpenportParameter(usage = "实际完成充值的金额,单位为分", name = "amount") long amount,
                      @CjOpenportParameter(usage = "订单完成时第三方渠道的返回码", name = "code") String code,
                      @CjOpenportParameter(usage = "订单完成时第三方渠道的返回信息", name = "message") String message
    ) throws CircuitException;

    @CjOpenport(usage = "提现，从余额中将款项提取到其它支付渠道")
    void withdraw(ISecuritySession securitySession,
                  @CjOpenportParameter(usage = "提现金额,单位为分", name = "amount") long amount,
                  @CjOpenportParameter(usage = "提现到我的支付渠道。渠道路径格式，如支付宝为: alipay://0283838838383 ，后为支付宝账号", name = "paymentChannelPath") String paymentChannelPath,
                  @CjOpenportParameter(usage = "备注", name = "memo") String memo
    ) throws CircuitException;

    @CjOpenport(usage = "付款，付给系统内其它用户。注：当余额不足时会失败", command = "post")
    void payment(ISecuritySession securitySession,
                 @CjOpenportParameter(usage = "金额,单位为分", name = "amount") long amount,
                 @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
                 @CjOpenportParameter(usage = "付款类型。二维码付款(qrcode_pay)，支付订单(order_pay)", name = "type") String type,
                 @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content) TradeDetailsBO details
    ) throws CircuitException;

    @CjOpenport(usage = "收款，从系统内其它用户处接收款项。", command = "post")
    void gathering(ISecuritySession securitySession,
                   @CjOpenportParameter(usage = "金额,单位为分", name = "amount")
                           long amount,
                   @CjOpenportParameter(usage = "付款人", name = "payer")
                           String payer,
                   @CjOpenportParameter(usage = "收款类型。二维码收款(qrcode_gather)，售出收款(order_gather)", name = "type")
                           String type,
                   @CjOpenportParameter(usage = "交易明细，如类型是售出收款，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content)
                           TradeDetailsBO details
    ) throws CircuitException;

    @CjOpenport(usage = "转账。一个系统内用户付款到另一系统内用户收款。注：当余额不足时可能会从他的非余额支付渠道中扣款", command = "post")
    void transfer(ISecuritySession securitySession,
                  @CjOpenportParameter(usage = "金额,单位为分", name = "amount") long amount,
                  @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
                  @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content) TradeDetailsBO details
    ) throws CircuitException;

    @CjOpenport(usage = "查询余额")
    void queryAmount(ISecuritySession securitySession
    ) throws CircuitException;
}

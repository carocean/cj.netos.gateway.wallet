package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.result.ExchangeWenyResult;
import cj.netos.gateway.wallet.result.PurchasingResult;
import cj.netos.gateway.wallet.result.RechargeResult;
import cj.netos.gateway.wallet.result.WithdrawResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

@CjOpenports(usage = "收单业务开放服务")
public interface IReceiptTradePorts extends IOpenportService {
    @CjOpenport(usage = "充值下单")
    RechargeResult recharge(ISecuritySession securitySession,
                            @CjOpenportParameter(usage = "币种", name = "currency", defaultValue = "CNY") String currency,
                            @CjOpenportParameter(usage = "欲充值的金额,单位为分", name = "amount") long amount,
                            @CjOpenportParameter(usage = "充值来源渠道号", name = "payChannelID") String payChannelID,
                            @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "提现下单")
    WithdrawResult withdraw(ISecuritySession securitySession,
                            @CjOpenportParameter(usage = "提现金额,单位为分", name = "amount") long amount,
                            @CjOpenportParameter(usage = "提现到指定的渠道号", name = "payChannelID") String payChannelID,
                            @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "申购纹银")
    PurchasingResult purchaseWeny(ISecuritySession securitySession,
                                  @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
                                  @CjOpenportParameter(usage = "委托申购金额,单位为分", name = "amount") long amount,
                                  @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "承兑纹银")
    ExchangeWenyResult exchangeWeny(ISecuritySession securitySession,
                                    @CjOpenportParameter(usage = "要承兑的纹银申购单号", name = "purchase_sn") String purchase_sn,
                                    @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

//    @CjOpenport(usage = "付款，付给系统内其它用户。注：当余额不足时会失败", command = "post")
//    void payment(ISecuritySession securitySession,
//                 @CjOpenportParameter(usage = "金额,单位为分", name = "amount") long amount,
//                 @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
//                 @CjOpenportParameter(usage = "付款类型。二维码付款(qrcode_pay)，支付订单(order_pay)", name = "type") String type,
//                 @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content) TradeDetailsBO details
//    ) throws CircuitException;
//
//    @CjOpenport(usage = "收款，从系统内其它用户处接收款项。", command = "post")
//    void gathering(ISecuritySession securitySession,
//                   @CjOpenportParameter(usage = "金额,单位为分", name = "amount")
//                           long amount,
//                   @CjOpenportParameter(usage = "付款人", name = "payer")
//                           String payer,
//                   @CjOpenportParameter(usage = "收款类型。二维码收款(qrcode_gather)，售出收款(order_gather)", name = "type")
//                           String type,
//                   @CjOpenportParameter(usage = "交易明细，如类型是售出收款，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content)
//                           TradeDetailsBO details
//    ) throws CircuitException;
//
//    @CjOpenport(usage = "转账。一个系统内用户付款到另一系统内用户收款。注：当余额不足时可能会从他的非余额支付渠道中扣款", command = "post")
//    void transfer(ISecuritySession securitySession,
//                  @CjOpenportParameter(usage = "金额,单位为分", name = "amount") long amount,
//                  @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
//                  @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content) TradeDetailsBO details
//    ) throws CircuitException;
//
//    @CjOpenport(usage = "查询余额")
//    void queryAmount(ISecuritySession securitySession
//    ) throws CircuitException;
}

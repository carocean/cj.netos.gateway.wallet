package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.bo.PayDetailsBO;
import cj.netos.gateway.wallet.model.P2pEvidence;
import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.result.*;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.AccessTokenIn;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.PKeyInRequest;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportAppSecurity;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

@CjOpenports(usage = "收单业务开放服务")
public interface IReceiptTradePorts extends IOpenportService {
    @CjOpenport(usage = "充值下单")
    String recharge(ISecuritySession securitySession,
                    @CjOpenportParameter(usage = "币种", name = "currency", defaultValue = "CNY") String currency,
                    @CjOpenportParameter(usage = "欲充值的金额,单位为分", name = "amount") long amount,
                    @CjOpenportParameter(usage = "充值来源渠道号", name = "payChannel") String payChannel,
                    @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "充值下单，指定终端")
    String rechargeTo(ISecuritySession securitySession,
                    @CjOpenportParameter(usage = "币种", name = "currency", defaultValue = "CNY") String currency,
                    @CjOpenportParameter(usage = "欲充值的金额,单位为分", name = "amount") long amount,
                    @CjOpenportParameter(usage = "充值来源渠道号", name = "payChannel") String payChannel,
                    @CjOpenportParameter(usage = "充值来源渠道支持的终端，值有:app||jsapi等等，每个渠道所支持的终端或不同，为空表示默认为app", name = "applyTerminal") String applyTerminal,
                    @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "提现下单")
    WithdrawRecord withdraw(ISecuritySession securitySession,
                            @CjOpenportParameter(usage = "提现金额,单位为分", name = "amount") long amount,
                            @CjOpenportParameter(usage = "提现的目标公众卡号", name = "personCard") String personCard,
                            @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;
//
//    @CjOpenport(usage = "从我的收益账户转入零钱账户")
//    TransferProfitResult transferProfit(ISecuritySession securitySession,
//                                        @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
//                                        @CjOpenportParameter(usage = "转账金额,单位为分", name = "amount") long amount,
//                                        @CjOpenportParameter(usage = "备注", name = "note") String note
//    ) throws CircuitException;

    @CjOpenport(usage = "用于合作伙伴从其账金账户将收入转入到其零钱账户。如果没有账金提取权限则报801异常")
    TransShuntResult transferShunter(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "账金账户所在的的纹银银行id", name = "wenyBankID") String wenyBankID,
            @CjOpenportParameter(usage = "账金账户", name = "shunter") String shunter,
            @CjOpenportParameter(usage = "申请转入的金额,单位为分", name = "amount") long amount,
            @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "从我的洇金账户转入零钱账户")
    TransferAbsorbResult transferAbsorb(ISecuritySession securitySession,
                                        @CjOpenportParameter(usage = "转账金额,单位为分", name = "amount") long amount,
                                        @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "付款交易，即向账务系统外支付（不属于本账务系统的外部系统），如付款给商户、付款给洇取器等等，而针对系统内其它公号的支付请使用转账服务", command = "post")
    PaymentResult payTrade(ISecuritySession securitySession,
                           @CjOpenportParameter(usage = "付款金额,单位为分", name = "amount") long amount,
                           @CjOpenportParameter(usage = "0. qrcode_pay(扫码支付)|1 order_pay(支付订单)", name = "type") int type,
                           @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content, simpleModelFile = "/payable_details.md") PayDetailsBO details,
                           @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "生成收款凭证，返回证凭号")
    String genReceivableEvidence(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "过期时间", name = "expire") long expire,
            @CjOpenportParameter(usage = "可使用的次数", name = "useTimes") long useTimes
    ) throws CircuitException;

    @CjOpenport(usage = "生成付款凭证，返回证凭号")
    String genPayableEvidence(
            ISecuritySession securitySession,
            @CjOpenportParameter(usage = "过期时间", name = "expire") long expire,
            @CjOpenportParameter(usage = "可使用的次数", name = "useTimes") long useTimes
    ) throws CircuitException;

    @CjOpenport(usage = "验证交易凭证")
    P2pEvidence checkEvidence(ISecuritySession securitySession,
                              @CjOpenportParameter(usage = "收款或付款凭证", name = "evidence") String evidence
    ) throws CircuitException;

    @CjOpenport(usage = "向凭证付款，凭证必须是收款凭证，仅用于系统内用户之间互转")
    P2PRecResult payToEvidence(ISecuritySession securitySession,
                               @CjOpenportParameter(usage = "收款凭证", name = "evidence") String evidence,
                               @CjOpenportParameter(usage = "金额", name = "amount") long amount,
                               @CjOpenportParameter(usage = "转账类型：0.p2p(直转)|1 qrcode_pay(扫码付款人)", name = "type") int type,
                               @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "向凭证收款，凭证必须是付款凭证，仅用于系统内用户之间互转")
    P2PRecResult receiveFromEvidence(ISecuritySession securitySession,
                                     @CjOpenportParameter(usage = "付款凭证", name = "evidence") String evidence,
                                     @CjOpenportParameter(usage = "金额", name = "amount") long amount,
                                     @CjOpenportParameter(usage = "转账类型：0.p2p(直转)|1 qrcode_pay(扫码付款人)", name = "type") int type,
                                     @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "我直接转账给对方(P2P)，仅用于系统内用户之间互转")
    P2PRecResult transTo(ISecuritySession securitySession,
                         @CjOpenportParameter(usage = "转账金额,单位为分", name = "amount") long amount,
                         @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
                         @CjOpenportParameter(usage = "转账类型：0.p2p(直转)|1 qrcode_pay(扫码收款人)", name = "type") int type,
                         @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "申购纹银")
    PurchasingResult purchaseWeny(ISecuritySession securitySession,
                                  @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
                                  @CjOpenportParameter(usage = "委托申购金额,单位为分", name = "amount") long amount,
                                  @CjOpenportParameter(usage = "外部订单类型：\n" +
                                          "netflow网流\n" +
                                          "geosphere地圈", name = "outTradeType") String outTradeType,
                                  @CjOpenportParameter(usage = "外部单号：\n" +
                                          "网流：cj@gbera.netos/29939238283\n" +
                                          "地圈：mobiles/9239203920392", name = "outTradeSn") String outTradeSn,
                                  @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "指定付款方式申购纹银")
    PurchasingResult purchaseWeny2(ISecuritySession securitySession,
                                   @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
                                   @CjOpenportParameter(usage = "付款方式：0零钱；1体验金", name = "payMethod") int payMethod,
                                   @CjOpenportParameter(usage = "委托申购金额,单位为分", name = "amount") long amount,
                                   @CjOpenportParameter(usage = "外部订单类型：\n" +
                                           "netflow网流\n" +
                                           "geosphere地圈", name = "outTradeType") String outTradeType,
                                   @CjOpenportParameter(usage = "外部单号：\n" +
                                           "网流：cj@gbera.netos/29939238283\n" +
                                           "地圈：mobiles/9239203920392", name = "outTradeSn") String outTradeSn,
                                   @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "承兑纹银")
    ExchangedResult exchangeWeny(ISecuritySession securitySession,
                                 @CjOpenportParameter(usage = "要承兑的纹银申购单号", name = "purchase_sn") String purchase_sn,
                                 @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenportAppSecurity
    @CjOpenport(usage = "承兑纹银", tokenIn = AccessTokenIn.nope)
    ExchangedResult exchangeWenyOfPerson(ISecuritySession securitySession,
                                         @CjOpenportParameter(usage = "要承兑的纹银申购单号", name = "purchase_sn") String purchase_sn,
                                         @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;
}

package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.bo.PayDetailsBO;
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

    @CjOpenport(usage = "从我的收益账户转入零钱账户")
    TransferProfitResult transferProfit(ISecuritySession securitySession,
                                        @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
                                        @CjOpenportParameter(usage = "转账金额,单位为分", name = "amount") long amount,
                                        @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

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


    @CjOpenport(usage = "存入洇金。在使用gbera服务时洇取的洇金存入到洇金账户")
    DepositAbsorbResult depositAbsorb(ISecuritySession securitySession,
                                      @CjOpenportParameter(usage = "存入金额,单位为分", name = "amount") long amount,
                                      @CjOpenportParameter(usage = "洇金来源代码，一般是来源的模块名", name = "sourceCode") String sourceCode,
                                      @CjOpenportParameter(usage = "洇金来源的显示名", name = "sourceTitle") String sourceTitle,
                                      @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;


    @CjOpenport(usage = "应付款收单。采用付款方主动模式，应付款收单的逻辑是应该向谁付什么款，但并未实际支付，而是执行预扣款。\n" +
            "1。对于收方主动扫付款码收款的情况，收方扫码后得到付方的临时支付令牌，并创建临时收款单，付方轮询到后验证令牌通过后开始调用支付程序，两方是个互动过程。\n" +
            "2。对于付方主动扫收款码的情况，则付方主动调用支付程序，成功后通知收款方即可。\n" +
            "因此对于收付款和转账的接口仅3个，即应付方法、支付方法（实际执行）、退款方法，后两个方法在决清ports中实现", command = "post")
    PayableResult payable(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "付款金额,单位为分", name = "amount") long amount,
                          @CjOpenportParameter(usage = "收款人", name = "payee") String payee,
                          @CjOpenportParameter(usage = "付款类型，有：0 normal_pay(普通无附带属性，如p2p转账)|1 qrcode_payer_scan(我扫码收款方主动支付)|2 qrcode_payeer_scan(我被收款方扫码我方支付)|3 receipt_pay(支付收款单)|4 order_pay(支付订单)", name = "type") int type,
                          @CjOpenportParameter(usage = "交易明细，如类型是支付订单，则明细中有商户、订单号等", name = "details", in = PKeyInRequest.content, simpleModelFile = "/payable_details.md") PayDetailsBO details,
                          @CjOpenportParameter(usage = "备注", name = "note") String note
    ) throws CircuitException;

    @CjOpenport(usage = "申购纹银")
    PurchasingResult purchaseWeny(ISecuritySession securitySession,
                                  @CjOpenportParameter(usage = "要申购的纹银银行id", name = "wenyBankID") String wenyBankID,
                                  @CjOpenportParameter(usage = "委托申购金额,单位为分", name = "amount") long amount,
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

package cj.netos.gateway.wallet.ports;

import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenports;

@CjOpenports(usage = "钱包开放服务")
public interface IWalletPorts extends IOpenportService {
    @CjOpenport(usage = "充值，从他的其它支付渠道中将款充入余额")
    void recharge(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "提现，从余额中将款项提取到其它支付渠道")
    void withdraw(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "付款，付给系统内其它用户。注：当余额不足时可能会从他的非余额支付渠道中扣款")
    void payment(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "收款，从系统内其它用户处接收款项。注：当余额不足时可能会从他的非余额支付渠道中扣款")
    void gathering(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "转账。从一个系统内用户转到另一系统内用户。注：当余额不足时可能会从他的非余额支付渠道中扣款")
    void transfer(ISecuritySession securitySession) throws CircuitException;
}

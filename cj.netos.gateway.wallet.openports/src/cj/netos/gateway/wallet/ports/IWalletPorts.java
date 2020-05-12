package cj.netos.gateway.wallet.ports;

import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.AccessTokenIn;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportAppSecurity;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.Map;

@CjOpenports(usage = "中心钱包开放api")
public interface IWalletPorts extends IOpenportService {

    @CjOpenport(usage = "开通钱包账户")
    Map<String, Object> initWallet(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "是否开通了钱包账户")
    boolean isinitWallet(ISecuritySession securitySession
    ) throws CircuitException;
}

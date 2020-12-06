package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.model.TrialFundsConfig;
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
    Map<String, Object> createWallet(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "是否开通了钱包账户")
    boolean hasWallet(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "配置并开通体验金机制")
    void configTrial(ISecuritySession securitySession,
                   @CjOpenportParameter(usage = "体验金划扣账户，指向某公号",name = "remitAccount")  String remitAccount,
                     @CjOpenportParameter(usage = "划扣账户名，某公号的昵称",name = "remitName")   String remitName,
                     @CjOpenportParameter(usage = "每笔体验金额",name = "trialAmount")   long trialAmount
    ) throws CircuitException;

    @CjOpenport(usage = "获取体验金配置")
    TrialFundsConfig getTrialConfig(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "停止发放体验金")
    void stopTrial(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "重新开启体验金")
    void restartTrial(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "更新体验金扣款账号")
    void updateRemitAccount(ISecuritySession securitySession,
                            @CjOpenportParameter(usage = "体验金划扣账户，指向某公号",name = "remitAccount")  String remitAccount,
                            @CjOpenportParameter(usage = "划扣账户名，某公号的昵称",name = "remitName")   String remitName
    ) throws CircuitException;

    @CjOpenport(usage = "更新体验金扣款账号")
    void updateTrialAmount(ISecuritySession securitySession,
                           @CjOpenportParameter(usage = "每笔体验金额",name = "trialAmount")   long trialAmount
    ) throws CircuitException;
}

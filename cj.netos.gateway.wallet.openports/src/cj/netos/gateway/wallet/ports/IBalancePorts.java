package cj.netos.gateway.wallet.ports;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.Map;

@CjOpenports(usage = "余额类服务")
public interface IBalancePorts extends IOpenportService {

    @CjOpenport(usage = "获取各账户余额")
    Map<String, Object> getAllAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取主账户余额")
    Map<String, Object> getRootAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取零钱账户余额")
    Map<String, Object> getBalanceAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇金账户余额")
    Map<String, Object> getAbsorbAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取冻结账户余额")
    Map<String, Object> getFreezenAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取收益账户余额")
    Map<String, Object> getProfitAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取访问者的所有纹银账户余额")
    Map<String, Object> listStockAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取访问者的纹银关联账户余额。包括，纹银账户、冻结账户、收益账户")
    Map<String, Object> getRelatedStockAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取纹银账户余额")
    Map<String, Object> getStockAccount(ISecuritySession securitySession,
                                        @CjOpenportParameter(usage = "纹银银行行号", name = "wenyBankID") String wenyBankID
    ) throws CircuitException;


}

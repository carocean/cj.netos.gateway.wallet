package cj.netos.gateway.wallet.ports;

import cj.netos.gateway.wallet.TestPublishBeforeInvoker;
import cj.netos.rabbitmq.RabbitMQProducerConfig;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

@CjOpenports(usage = "交易网关开放api。注：必须把交易节点的routingKey添加到本网关的routingKeys列表才能将消息发送到交易节点。对于交易节点，一个交易节点向rabbitmq注册一个routingKey")
public interface IGatewayPorts extends IOpenportService {
    @CjOpenport(usage = "是否正在暂停状态，在暂停状态时会停止发布消息，导致暂停的原因是routingKey列表正在更新")
    boolean isPausing(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "是否已打开网关")
    boolean isOpened(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "获取网关的配置信息")
    RabbitMQProducerConfig config(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "重新打开网关，如果已打开则什么也不做")
    RabbitMQProducerConfig reopen(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "关闭网关")
    void close(ISecuritySession securitySession) throws CircuitException;

    @CjOpenport(usage = "添加rabbitmq的rountingKey,该key是交易节点向rabbitmq注册的routingKey，如果本网关中没有指定那些key则相应的交易节点收不到消息")
    void addRoutingKey(ISecuritySession securitySession,
                       @CjOpenportParameter(usage = "路由key", name = "routingKey") String routingKey) throws CircuitException;

    @CjOpenport(usage = "移除rabbitmq的rountingKey。当交易节点队列在rabbitmq上移除后需要手动移除，否则后续发送的消息全部丢弃")
    void removeRoutingKey(ISecuritySession securitySession,
                          @CjOpenportParameter(usage = "路由key", name = "routingKey") String routingKey) throws CircuitException;

    @CjOpenport(usage = "测试发布", beforeInvoker = TestPublishBeforeInvoker.class)
    void testPublish(ISecuritySession securitySession,
                     @CjOpenportParameter(usage = "消息", name = "message") String message
                     ) throws CircuitException;
}

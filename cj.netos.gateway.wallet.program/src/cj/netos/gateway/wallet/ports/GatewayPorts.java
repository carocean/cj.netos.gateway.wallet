package cj.netos.gateway.wallet.ports;

import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.netos.rabbitmq.RabbitMQProducerConfig;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;
import java.util.Map;

@CjService(name = "/gateway.ports")
public class GatewayPorts implements IGatewayPorts {
    @CjServiceRef(refByName = "rabbitMQProducer")
    IRabbitMQProducer rabbitMQ;

    private void checkRights(ISecuritySession securitySession) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators") && !securitySession.roleIn("tenant:administrators") && !securitySession.roleIn("app:administrators")) {
            throw new CircuitException("801", "无权访问");
        }
    }

    @Override
    public boolean isPausing(ISecuritySession securitySession) throws CircuitException {
        return rabbitMQ.isPausing();
    }

    @Override
    public boolean isOpened(ISecuritySession securitySession) throws CircuitException {
        return rabbitMQ.isOpened();
    }

    @Override
    public RabbitMQProducerConfig config(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        return rabbitMQ.config();
    }


    @Override
    public RabbitMQProducerConfig reopen(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        if (rabbitMQ.isOpened()) {
            rabbitMQ.close();
        }
        rabbitMQ.innerOpen();
        return null;
    }

    @Override
    public void close(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        rabbitMQ.close();
    }

    @Override
    public void addRoutingKey(ISecuritySession securitySession, String routingKey) throws CircuitException {
        checkRights(securitySession);
        rabbitMQ.addRoutingKey(routingKey);
    }

    @Override
    public void removeRoutingKey(ISecuritySession securitySession, String routingKey) throws CircuitException {
        checkRights(securitySession);
        rabbitMQ.removeRoutingKey(routingKey);
    }

    @Override
    public void testPublish(ISecuritySession securitySession, String message) throws CircuitException {
        checkRights(securitySession);
        String timesStr = (String) securitySession.property("Test-Counter");

        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder();
        properties.deliveryMode(2);  // 设置消息是否持久化，1： 非持久化 2：持久化
        Map<String, Object> headers = new HashMap<>();
        headers.put("sender", securitySession.principal());
        headers.put("Test-Counter", timesStr);
        properties.headers(headers);
        rabbitMQ.publish(properties.build(), message.getBytes());
    }
}

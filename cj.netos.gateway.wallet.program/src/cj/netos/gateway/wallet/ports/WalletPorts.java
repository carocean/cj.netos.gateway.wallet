package cj.netos.gateway.wallet.ports;

import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

@CjService(name = "/wallet.ports")
public class WalletPorts implements IWalletPorts {
    @CjServiceRef(refByName = "rabbitMQProducer")
    IRabbitMQProducer rabbitMQ;

    @Override
    public void recharge(ISecuritySession securitySession) throws CircuitException {
//        rabbitMQ.publish(null,);
    }

    @Override
    public void withdraw(ISecuritySession securitySession) throws CircuitException {

    }

    @Override
    public void payment(ISecuritySession securitySession) throws CircuitException {

    }

    @Override
    public void gathering(ISecuritySession securitySession) throws CircuitException {

    }

    @Override
    public void transfer(ISecuritySession securitySession) throws CircuitException {

    }
}

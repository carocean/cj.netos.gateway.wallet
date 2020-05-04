package cj.netos.gateway.wallet;

import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.openport.IOpenportBeforeInvoker;
import cj.studio.openport.ISecuritySession;

public class TestPublishBeforeInvoker implements IOpenportBeforeInvoker {
    @Override
    public void doBefore(boolean isForbiddenCheckAccessToken, ISecuritySession securitySession, Frame frame, Circuit circuit) throws CircuitException {
        String counter = frame.head("OpenportsTester-Counter");
        securitySession.property("Test-Counter",counter);
    }
}

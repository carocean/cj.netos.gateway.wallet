package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.PayChannel;
import cj.studio.ecm.net.CircuitException;

import java.util.List;

public interface IPayChannelService {
    void addPayChannel(PayChannel payChannel) throws CircuitException;

    void removePayChannel(String id) throws CircuitException;

    List<PayChannel> pagePayChannel(int limit, long offset) throws CircuitException;

    PayChannel getPayChannel(String payChannelID);

}

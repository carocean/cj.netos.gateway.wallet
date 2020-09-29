package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;

public interface IChannelAccountSelector {
    ChannelAccount selector(PayChannel payChannel);

}

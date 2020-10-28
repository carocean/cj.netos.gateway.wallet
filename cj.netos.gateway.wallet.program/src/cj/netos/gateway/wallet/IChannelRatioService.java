package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.ChannelRatio;

import java.util.List;

public interface IChannelRatioService {
    List<ChannelRatio> listFeeRatioOfChannel(String channel);

    ChannelRatio getFeeRatio(String channel,  long amount);

}

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IChannelRatioService;
import cj.netos.gateway.wallet.mapper.ChannelRatioMapper;
import cj.netos.gateway.wallet.model.ChannelRatio;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "channelRatioService")
public class ChannelRatioService implements IChannelRatioService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ChannelRatioMapper")
    ChannelRatioMapper channelRatioMapper;

    @CjTransaction
    @Override
    public List<ChannelRatio> listFeeRatioOfChannel(String channel) {
        return channelRatioMapper.listFeeRatio(channel);
    }

    @CjTransaction
    @Override
    public ChannelRatio getFeeRatio(String channel, long amount) {
        List<ChannelRatio> ratios = channelRatioMapper.getFeeRatio(channel, amount);
        if (ratios.isEmpty()) {
            return null;
        }
        return ratios.get(0);
    }
}

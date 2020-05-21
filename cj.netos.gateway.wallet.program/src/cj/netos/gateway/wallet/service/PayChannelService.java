package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.mapper.PayChannelMapper;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "payChannelService")
public class PayChannelService implements IPayChannelService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PayChannelMapper")
    PayChannelMapper payChannelMapper;

    @CjTransaction
    @Override
    public void addPayChannel(PayChannel payChannel) throws CircuitException {
        payChannelMapper.insert(payChannel);
    }

    @CjTransaction
    @Override
    public void removePayChannel(String id) throws CircuitException {
        payChannelMapper.deleteByPrimaryKey(id);
    }

    @CjTransaction
    @Override
    public List<PayChannel> pagePayChannel(int limit, long offset) throws CircuitException {
        return null;
    }

    @CjTransaction
    @Override
    public PayChannel getPayChannel(String payChannelID) {
        return payChannelMapper.selectByPrimaryKey(payChannelID);
    }
}

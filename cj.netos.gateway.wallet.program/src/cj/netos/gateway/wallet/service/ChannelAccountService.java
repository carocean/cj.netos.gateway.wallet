package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.mapper.ChannelAccountMapper;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.ChannelAccountExample;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.util.StringUtil;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "channelAccountService")
public class ChannelAccountService implements IChannelAccountService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.ChannelAccountMapper")
    ChannelAccountMapper channelAccountMapper;

    @CjTransaction
    @Override
    public void addAccount(ChannelAccount account) {
        channelAccountMapper.insert(account);
    }

    @CjTransaction
    @Override
    public void removeAccount(String accountid) {
        channelAccountMapper.deleteByPrimaryKey(accountid);
    }

    @CjTransaction
    @Override
    public ChannelAccount getAccount(String accountid) {
        return channelAccountMapper.selectByPrimaryKey(accountid);
    }

    @CjTransaction
    @Override
    public ChannelAccount selectAccount(String payChannel, int factory) {
        ChannelAccountExample example = new ChannelAccountExample();
        example.createCriteria().andChannelEqualTo(payChannel);
        List<ChannelAccount> list = channelAccountMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        int index = factory % list.size();
        return list.get(index);
    }

    @CjTransaction
    @Override
    public List<ChannelAccount> pageAccountOfChannel(String channel, int limit, long offset) {
        return channelAccountMapper.pageAccountOfChannel(channel, limit, offset);
    }

    @CjTransaction
    @Override
    public List<ChannelAccount> pageAccount(int limit, long offset) {
        return channelAccountMapper.pageAccount(limit, offset);
    }

    @CjTransaction
    @Override
    public List<ChannelAccount> pageAccountBy(String channel, String applyTerminal, int limit, long offset) {
        return channelAccountMapper.pageAccountBy(channel, applyTerminal, limit, offset);
    }

    @CjTransaction
    @Override
    public List<ChannelAccount> pageAccountByTerminal(String applyTerminal, int limit, long offset) {
        return channelAccountMapper.pageAccountByTerminal(applyTerminal, limit, offset);
    }

    @CjTransaction
    @Override
    public long totalAccountBalance(String channel) {
        if (!StringUtil.isEmpty(channel)) {
            return channelAccountMapper.totalBalanceByChannel(channel);
        }
        return channelAccountMapper.totalAllChannelBalance();
    }
}

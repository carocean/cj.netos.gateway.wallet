package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.ChannelAccount;

import java.util.List;

public interface IChannelAccountService {
    void addAccount(ChannelAccount account);

    void removeAccount(String accountid);

    List<ChannelAccount> pageAccountOfChannel(String channel, int limit, long offset);

    List<ChannelAccount> pageAccount(int limit, long offset);

    ChannelAccount getAccount(String accountid);

    long totalAccountBalance(String channel);
}

package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IChannelAccountSelector;
import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

import java.util.List;

@CjService(name = "channelAccountSelector")
public class ChannelAccountSelector implements IChannelAccountSelector {
    @CjServiceRef
    IChannelAccountService channelAccountService;

    @Override
    public ChannelAccount selectSmallestAccount(PayChannel payChannel,String applyTerminal) {
        List<ChannelAccount> accountList = channelAccountService.pageAccountBy(payChannel.getCode(),applyTerminal, Integer.MAX_VALUE, 0);
        if (accountList.isEmpty()) {
            return null;
        }
        if (accountList.size() == 1) {
            return accountList.get(0);
        }
        //最小余额账户被选中
        ChannelAccount minAccount = null;
        for (ChannelAccount account : accountList) {
            if (minAccount == null) {
                minAccount = account;
                continue;
            }
            if (minAccount.getBalanceAmount().compareTo(account.getBalanceAmount()) < 0) {
                minAccount = account;
            }
        }
        return minAccount;
    }

    @Override
    public ChannelAccount selectEnoughAccount(long amount, String payChannel) {
        //选在当前支付渠道中搜账号，如果余额不够再到其它渠道中搜索账号
        List<ChannelAccount> accountList = channelAccountService.pageAccountOfChannel(payChannel, Integer.MAX_VALUE, 0);
        if (accountList.isEmpty()) {
            _searchOtherChannel(amount, payChannel);
        }
        ChannelAccount select = null;
        for (ChannelAccount account : accountList) {
            if (account.getBalanceAmount().compareTo(amount) >= 0) {
                select = account;
                break;
            }
        }
        if (select != null) {
            return select;
        }
        accountList = _searchOtherChannel(amount, payChannel);
        if (accountList.isEmpty()) {
            return null;
        }
        for (ChannelAccount account : accountList) {
            if (account.getBalanceAmount().compareTo(amount) >= 0) {
                select = account;
                break;
            }
        }
        return select;
    }

    private List<ChannelAccount> _searchOtherChannel(long amount, String payChannel) {
        List<ChannelAccount> all = channelAccountService.pageAccount(Integer.MAX_VALUE, 0);
        for (ChannelAccount account : all) {
            if (account.getChannel().equals(payChannel)) {
                continue;
            }
        }
        return all;
    }
}

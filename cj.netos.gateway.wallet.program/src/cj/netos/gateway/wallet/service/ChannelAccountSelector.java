package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IChannelAccountSelector;
import cj.netos.gateway.wallet.IChannelAccountService;
import cj.netos.gateway.wallet.IPayChannelService;
import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@CjService(name = "channelAccountSelector")
public class ChannelAccountSelector implements IChannelAccountSelector {
    @CjServiceRef
    IChannelAccountService channelAccountService;

    @Override
    public ChannelAccount selector(PayChannel payChannel) {
        List<ChannelAccount> accountList = channelAccountService.pageAccountOfChannel(payChannel.getCode(), Integer.MAX_VALUE, 0);
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
}

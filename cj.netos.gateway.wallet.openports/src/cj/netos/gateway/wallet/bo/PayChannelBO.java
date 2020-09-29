package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.PayChannel;

import java.util.List;

public class PayChannelBO extends PayChannel {
    List<ChannelAccount> accounts;

    public List<ChannelAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ChannelAccount> accounts) {
        this.accounts = accounts;
    }

    public PayChannel toPayChannel() {
        PayChannel channel = new PayChannel();
        channel.setCode(getCode());
        channel.setName(getName());
        channel.setCtime(getCtime());
        channel.setNote(getNote());
        return channel;
    }
}

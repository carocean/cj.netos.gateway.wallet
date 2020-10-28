package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.ChannelRatio;
import cj.netos.gateway.wallet.model.PayChannel;

import java.util.List;

public class PayChannelBO extends PayChannel {
    List<ChannelAccount> accounts;
    List<ChannelRatio> feeRatios;

    public List<ChannelAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ChannelAccount> accounts) {
        this.accounts = accounts;
    }

    public List<ChannelRatio> getFeeRatios() {
        return feeRatios;
    }

    public void setFeeRatios(List<ChannelRatio> feeRatios) {
        this.feeRatios = feeRatios;
    }

    public PayChannel toPayChannel() {
        PayChannel channel = new PayChannel();
        channel.setCode(getCode());
        channel.setName(getName());
        channel.setSwitchFeeRatio(getSwitchFeeRatio());
        channel.setCtime(getCtime());
        channel.setNote(getNote());
        return channel;
    }
}

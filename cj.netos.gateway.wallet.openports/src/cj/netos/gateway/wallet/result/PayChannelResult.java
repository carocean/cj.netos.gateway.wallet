package cj.netos.gateway.wallet.result;

import cj.netos.gateway.wallet.model.PayChannel;

public class PayChannelResult extends PayChannel{
    public void copy(PayChannel channel) {
        setCode(channel.getCode());
        setCtime(channel.getCtime());
        setName(channel.getName());
        setNote(channel.getNote());
    }
}

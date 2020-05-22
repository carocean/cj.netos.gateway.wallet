package cj.netos.gateway.wallet.result;

import cj.netos.gateway.wallet.model.PayChannel;

public class PayChannelResult extends PayChannel{
    public void copy(PayChannel channel) {
        setAppid(channel.getAppid());
        setAppsecret(channel.getAppsecret());
        setChannelName(channel.getChannelName());
        setCode(channel.getCode());
        setCtime(channel.getCtime());
        setLimitAmount(channel.getLimitAmount());
        setMchId(channel.getMchId());
        setNote(channel.getNote());
        setUrl(channel.getUrl());
    }
}

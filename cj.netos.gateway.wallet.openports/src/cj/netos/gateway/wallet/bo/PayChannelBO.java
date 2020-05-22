package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.PayChannel;

public class PayChannelBO {
    /**
     * Column: code
     * Remark: 渠道类型代码， WeChat:微信 Alipay:支付宝 yinLan:银联
     */
    private String code;

    /**
     * Column: url
     * Remark: 渠道的服务地址
     */
    private String url;

    /**
     * Column: appid
     * Remark: 渠道给的aped
     */
    private String appid;

    /**
     * Column: appSecret
     * Remark: 渠道给的密钥
     */
    private String appsecret;

    /**
     * Column: mch_id
     * Remark: 商户号
     */
    private String mchId;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    /**
     * Column: limit_amount
     * Remark: 渠道限额
     */
    private Long limitAmount;

    /**
     * Column: channel_name
     * Remark: 支付渠道名称
     */
    private String channelName;
    public void copyTo(PayChannel channel) {
        channel.setAppid(getAppid());
        channel.setAppsecret(getAppsecret());
        channel.setChannelName(getChannelName());
        channel. setCode(getCode());
        channel.setLimitAmount(getLimitAmount());
        channel.setMchId(getMchId());
        channel. setNote(getNote());
        channel. setUrl(getUrl());
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Long limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


}

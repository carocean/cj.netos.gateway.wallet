package cj.netos.gateway.wallet.model;

/**
 * Table: pay_channel
 */
public class PayChannel {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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
        this.channelName = channelName == null ? null : channelName.trim();
    }
}
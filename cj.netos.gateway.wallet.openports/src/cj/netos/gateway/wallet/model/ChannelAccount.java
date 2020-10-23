package cj.netos.gateway.wallet.model;

/**
 * Table: channel_account
 */
public class ChannelAccount {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: app_id
     * Remark: 即渠道给的appid
     */
    private String appId;

    /**
     * Column: channel
     * Remark: 归属的渠道代码，对应表pay_channel的code
     */
    private String channel;

    /**
     * Column: balance_amount
     * Remark: 余额，单位为分
     */
    private Long balanceAmount;

    /**
     * Column: balance_utime
     * Remark: 余额更新时间
     */
    private String balanceUtime;

    /**
     * Column: limit_amount
     * Remark: 渠道限额,单位为分,0表示无限额
     */
    private Long limitAmount;

    /**
     * Column: service_url
     * Remark: 渠道的服务地址 全部以icon表示，但也为了兼容http格式，设定icons的格式为： icon:0xf1d7
     */
    private String serviceUrl;

    /**
     * Column: notify_url
     * Remark: 通知地址
     */
    private String notifyUrl;

    /**
     * Column: key_pubtime
     * Remark: 钥匙的发布时间
     */
    private String keyPubtime;

    /**
     * Column: key_expire
     * Remark: 钥匙的过期时间，用于提醒管理员，如果有，0表示永不过期
     */
    private Long keyExpire;

    /**
     * Column: use_cert
     * Remark: 是否采用证书或采用公钥 0为采用公钥 1为采用证书
     */
    private Integer useCert;

    /**
     * Column: public_key
     * Remark: 渠道给的公钥，一般是应用(app_id)的对应公钥
     */
    private String publicKey;

    /**
     * Column: private_key
     * Remark: 渠道给的私钥，一般是应用(app_id)的对应私钥
     */
    private String privateKey;

    /**
     * Column: cert_path1
     * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
     */
    private String certPath1;

    /**
     * Column: cert_path2
     * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
     */
    private String certPath2;

    /**
     * Column: cert_path3
     * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
     */
    private String certPath3;

    /**
     * Column: cert_path4
     * Remark: 如果第三方渠道采用证书，则不同渠道有不同的类型的证书，比如支付宝要求有：应用证书、支付证书、根证书，可分别用cert_path1,cert_path2，cert_path3来存储
     */
    private String certPath4;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getBalanceUtime() {
        return balanceUtime;
    }

    public void setBalanceUtime(String balanceUtime) {
        this.balanceUtime = balanceUtime == null ? null : balanceUtime.trim();
    }

    public Long getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Long limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl == null ? null : serviceUrl.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getKeyPubtime() {
        return keyPubtime;
    }

    public void setKeyPubtime(String keyPubtime) {
        this.keyPubtime = keyPubtime == null ? null : keyPubtime.trim();
    }

    public Long getKeyExpire() {
        return keyExpire;
    }

    public void setKeyExpire(Long keyExpire) {
        this.keyExpire = keyExpire;
    }

    public Integer getUseCert() {
        return useCert;
    }

    public void setUseCert(Integer useCert) {
        this.useCert = useCert;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public String getCertPath1() {
        return certPath1;
    }

    public void setCertPath1(String certPath1) {
        this.certPath1 = certPath1 == null ? null : certPath1.trim();
    }

    public String getCertPath2() {
        return certPath2;
    }

    public void setCertPath2(String certPath2) {
        this.certPath2 = certPath2 == null ? null : certPath2.trim();
    }

    public String getCertPath3() {
        return certPath3;
    }

    public void setCertPath3(String certPath3) {
        this.certPath3 = certPath3 == null ? null : certPath3.trim();
    }

    public String getCertPath4() {
        return certPath4;
    }

    public void setCertPath4(String certPath4) {
        this.certPath4 = certPath4 == null ? null : certPath4.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}
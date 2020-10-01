package cj.netos.gateway.wallet.model;

/**
 * Table: person_card
 */
public class PersonCard {
    /**
     * Column: id
     * Remark: 标识
     */
    private String id;

    /**
     * Column: person
     * Remark: 绑定人，公号
     */
    private String person;

    /**
     * Column: card_sn
     * Remark: 卡号
     */
    private String cardSn;

    /**
     * Column: card_holder
     * Remark: 持卡人。
     */
    private String cardHolder;

    /**
     * Column: card_attr_bank
     * Remark: 卡归属行行名
     */
    private String cardAttrBank;

    /**
     * Column: card_pub_bank
     * Remark: 卡的开户行，可选
     */
    private String cardPubBank;

    /**
     * Column: card_type
     * Remark: 卡类型 0- 储蓄卡 1- 信用卡 2- 积分卡 - 等等
     */
    private Integer cardType;

    /**
     * Column: card_phone
     * Remark: 手机号，可选
     */
    private String cardPhone;

    /**
     * Column: pay_channel
     * Remark: 公众账户的类型 0 chinapay银联（表示为银行卡账户） 1 alipay支付宝（个人的支付宝账户） 2 wechatpay 微信（个人的微信账户）
     */
    private String payChannel;

    /**
     * Column: pay_pwd
     * Remark: 个人设置的支付或提现密码
     */
    private String payPwd;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getCardSn() {
        return cardSn;
    }

    public void setCardSn(String cardSn) {
        this.cardSn = cardSn == null ? null : cardSn.trim();
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder == null ? null : cardHolder.trim();
    }

    public String getCardAttrBank() {
        return cardAttrBank;
    }

    public void setCardAttrBank(String cardAttrBank) {
        this.cardAttrBank = cardAttrBank == null ? null : cardAttrBank.trim();
    }

    public String getCardPubBank() {
        return cardPubBank;
    }

    public void setCardPubBank(String cardPubBank) {
        this.cardPubBank = cardPubBank == null ? null : cardPubBank.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone == null ? null : cardPhone.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }
}
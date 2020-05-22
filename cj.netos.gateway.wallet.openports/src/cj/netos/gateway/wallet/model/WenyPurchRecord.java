package cj.netos.gateway.wallet.model;

import java.math.BigDecimal;

/**
 * Table: weny_purch_record
 */
public class WenyPurchRecord {
    /**
     * Column: sn
     * Remark: YYYYMMDD+交易流水（每天从1开始）
     */
    private String sn;

    /**
     * Column: person
     * Remark: 公众号
     */
    private String person;

    /**
     * Column: person_name
     * Remark: 公众名
     */
    private String personName;

    /**
     * Column: currency
     * Remark: 币种
     */
    private String currency;

    /**
     * Column: purch_amount
     * Remark: 申购金
     */
    private Long purchAmount;

    /**
     * Column: quatities
     * Remark: 所得纹银
     */
    private BigDecimal quatities;

    /**
     * Column: price
     * Remark: 申购价
     */
    private Long price;

    /**
     * Column: charge
     * Remark: 服务收费金额
     */
    private Long charge;

    /**
     * Column: ratio
     * Remark: 费率
     */
    private BigDecimal ratio;

    /**
     * Column: ttm
     * Remark: 市盈率。
     */
    private BigDecimal ttm;

    /**
     * Column: state
     * Remark: -2 纹银银行处理失败 -1-预申购失败 0新建 1预处理完成 2申购完成
     */
    private Integer state;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    /**
     * Column: lutime
     * Remark: 最新修改时间
     */
    private String lutime;

    /**
     * Column: note
     */
    private String note;

    /**
     * Column: bankid
     * Remark: 纹银银行id
     */
    private String bankid;

    /**
     * Column: code
     * Remark: 返回码
     */
    private String code;

    /**
     * Column: message
     * Remark: 返回消息
     */
    private String message;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getPurchAmount() {
        return purchAmount;
    }

    public void setPurchAmount(Long purchAmount) {
        this.purchAmount = purchAmount;
    }

    public BigDecimal getQuatities() {
        return quatities;
    }

    public void setQuatities(BigDecimal quatities) {
        this.quatities = quatities;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getTtm() {
        return ttm;
    }

    public void setTtm(BigDecimal ttm) {
        this.ttm = ttm;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getLutime() {
        return lutime;
    }

    public void setLutime(String lutime) {
        this.lutime = lutime == null ? null : lutime.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
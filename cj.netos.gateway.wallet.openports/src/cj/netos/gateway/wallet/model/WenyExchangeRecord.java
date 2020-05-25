package cj.netos.gateway.wallet.model;

import java.math.BigDecimal;

/**
 * Table: weny_exchange_record
 */
public class WenyExchangeRecord {
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
     * Column: quatities
     * Remark: 余额	即可用的零钱，单位为分
     */
    private BigDecimal quatities;

    /**
     * Column: amount
     * Remark: 承兑得金
     */
    private Long amount;

    /**
     * Column: price
     * Remark: 承兑价
     */
    private BigDecimal price;

    /**
     * Column: refsn
     * Remark: 跟踪原申购纹银单号
     */
    private String refsn;

    /**
     * Column: purch_amount
     * Remark: 原申购金
     */
    private Long purchAmount;

    /**
     * Column: profit
     * Remark: 收益金
     */
    private Long profit;

    /**
     * Column: state
     * Remark: 为当前的步骤号 是否出错看status，status记录当前步骤的状态
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
     * Remark: 纹银银行行号，为多余字段，便于查询
     */
    private String bankid;

    /**
     * Column: status
     * Remark: 返回码
     */
    private Integer status;

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

    public BigDecimal getQuatities() {
        return quatities;
    }

    public void setQuatities(BigDecimal quatities) {
        this.quatities = quatities;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRefsn() {
        return refsn;
    }

    public void setRefsn(String refsn) {
        this.refsn = refsn == null ? null : refsn.trim();
    }

    public Long getPurchAmount() {
        return purchAmount;
    }

    public void setPurchAmount(Long purchAmount) {
        this.purchAmount = purchAmount;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
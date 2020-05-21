package cj.netos.gateway.wallet.model;

/**
 * Table: recharge_record
 */
public class RechargeRecord {
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
     * Column: demand_amount
     * Remark: 要求冲值金额
     */
    private Long demandAmount;

    /**
     * Column: real_amount
     * Remark: 实际冲值发生额
     */
    private String realAmount;

    /**
     * Column: from_channel
     * Remark: 从什么渠道充值，关联渠道id
     */
    private String fromChannel;

    /**
     * Column: state
     * Remark: 0-待充值 1- 充值成功 2- 充值失败 
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
     * Remark: 备注
     */
    private String note;

    /**
     * Column: done_code
     * Remark: 订单完成时第三方渠道的返回码
     */
    private String doneCode;

    /**
     * Column: done_msg
     * Remark: 订单完成时第三方渠道的返回信息
     */
    private String doneMsg;

    /**
     * Column: channel_name
     * Remark: 渠道名
     */
    private String channelName;

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

    public Long getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(Long demandAmount) {
        this.demandAmount = demandAmount;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount == null ? null : realAmount.trim();
    }

    public String getFromChannel() {
        return fromChannel;
    }

    public void setFromChannel(String fromChannel) {
        this.fromChannel = fromChannel == null ? null : fromChannel.trim();
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

    public String getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(String doneCode) {
        this.doneCode = doneCode == null ? null : doneCode.trim();
    }

    public String getDoneMsg() {
        return doneMsg;
    }

    public void setDoneMsg(String doneMsg) {
        this.doneMsg = doneMsg == null ? null : doneMsg.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }
}
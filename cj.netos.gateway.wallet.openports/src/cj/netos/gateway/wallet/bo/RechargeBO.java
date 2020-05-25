package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.ultimate.gson2.com.google.gson.Gson;

public class RechargeBO  {
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
    private Long realAmount;

    /**
     * Column: from_channel
     * Remark: 从什么渠道充值，关联渠道id
     */
    private String fromChannel;

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
     * Column: status
     * Remark: 订单完成时第三方渠道的返回码
     */
    private Integer status;

    /**
     * Column: message
     * Remark: 订单完成时第三方渠道的返回信息
     */
    private String message;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    /**
     * Column: channel_name
     * Remark: 渠道名
     */
    private String channelName;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(Long demandAmount) {
        this.demandAmount = demandAmount;
    }

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public String getFromChannel() {
        return fromChannel;
    }

    public void setFromChannel(String fromChannel) {
        this.fromChannel = fromChannel;
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
        this.ctime = ctime;
    }

    public String getLutime() {
        return lutime;
    }

    public void setLutime(String lutime) {
        this.lutime = lutime;
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
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public static RechargeBO create(RechargeRecord record) {
        return new Gson().fromJson(new Gson().toJson(record), RechargeBO.class);
    }
}

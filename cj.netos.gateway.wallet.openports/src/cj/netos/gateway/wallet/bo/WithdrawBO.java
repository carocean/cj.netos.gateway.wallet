package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.ultimate.gson2.com.google.gson.Gson;

public class WithdrawBO {
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
     * Remark: 欲申购金
     */
    private Long demandAmount;

    /**
     * Column: real_amount
     * Remark: 实际渠道接收的金额
     */
    private Long realAmount;

    /**
     * Column: to_channel
     * Remark: 提现到渠道
     */
    private String toChannel;

    /**
     * Column: state
     * Remark: -1提现失败 0-待充值 1-渠道已决清
     2- 提现成功
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
     * Column: settle_code
     * Remark: 订单完成时第三方渠道的返回码
     */
    private String settleCode;

    /**
     * Column: settle_msg
     * Remark: 订单完成时第三方渠道的返回信息
     */
    private String settleMsg;

    public static WithdrawBO create(WithdrawRecord record) {
        return new Gson().fromJson(new Gson().toJson(record), WithdrawBO.class);
    }

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

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public String getToChannel() {
        return toChannel;
    }

    public void setToChannel(String toChannel) {
        this.toChannel = toChannel == null ? null : toChannel.trim();
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

    public String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode == null ? null : settleCode.trim();
    }

    public String getSettleMsg() {
        return settleMsg;
    }

    public void setSettleMsg(String settleMsg) {
        this.settleMsg = settleMsg == null ? null : settleMsg.trim();
    }
}

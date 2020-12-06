package cj.netos.gateway.wallet.model;

/**
 * Table: deposit_trial_record
 */
public class DepositTrialRecord {
    /**
     * Column: sn
     * Remark: YYYYMMDD+交易流水（每天从1开始）
     */
    private String sn;

    /**
     * Column: payer
     * Remark: 付款人
     */
    private String payer;

    /**
     * Column: payer_name
     * Remark: 付款人姓名
     */
    private String payerName;

    /**
     * Column: payee
     * Remark: 收款人
     */
    private String payee;

    /**
     * Column: payee_name
     * Remark: 收款人姓名
     */
    private String payeeName;

    /**
     * Column: currency
     * Remark: 币种
     */
    private String currency;

    /**
     * Column: amount
     * Remark: 付款金额
     */
    private Long amount;

    /**
     * Column: state
     * Remark: 0为创建；1为完成 是否出错看status，status记录当前步骤的状态
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
     * Column: qrslice_id
     * Remark: 关联码片标识，因为体验金是激励发码人的，由其触发
     */
    private String qrsliceId;

    /**
     * Column: qrslice_consumer
     * Remark: 是由谁消费了该码片
     */
    private String qrsliceConsumer;

    /**
     * Column: qrslice_cname
     * Remark: 消费者的中文名
     */
    private String qrsliceCname;

    /**
     * Column: note
     * Remark: 备注
     */
    private String note;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getQrsliceId() {
        return qrsliceId;
    }

    public void setQrsliceId(String qrsliceId) {
        this.qrsliceId = qrsliceId == null ? null : qrsliceId.trim();
    }

    public String getQrsliceConsumer() {
        return qrsliceConsumer;
    }

    public void setQrsliceConsumer(String qrsliceConsumer) {
        this.qrsliceConsumer = qrsliceConsumer == null ? null : qrsliceConsumer.trim();
    }

    public String getQrsliceCname() {
        return qrsliceCname;
    }

    public void setQrsliceCname(String qrsliceCname) {
        this.qrsliceCname = qrsliceCname == null ? null : qrsliceCname.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}
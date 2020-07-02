package cj.netos.gateway.wallet.bo;

public class P2PBO {

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
     * Column: note
     * Remark: 备注
     */
    private String note;

    /**
     * Column: type
     * Remark: 付款类型，有： 转账类型： 0.p2p(直转)| 1 qrcode_pay(扫码收款人或付款人)
     */
    private Integer type;

    String direct;

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        this.ctime = ctime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

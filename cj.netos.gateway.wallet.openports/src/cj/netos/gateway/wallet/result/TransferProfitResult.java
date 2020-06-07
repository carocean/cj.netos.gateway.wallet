package cj.netos.gateway.wallet.result;

public class TransferProfitResult {
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
}

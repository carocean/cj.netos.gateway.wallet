package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.result.PurchasedResult;

import java.math.BigDecimal;

public class PurchasedBO {
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
     * Column: stock
     * Remark: 所得纹银
     */
    private BigDecimal stock;

    /**
     * Column: price
     * Remark: 申购价
     */
    private BigDecimal price;

    /**
     * Column: service_fee
     * Remark: 服务收费金额
     */
    private Long serviceFee;

    /**
     * Column: fee_ratio
     * Remark: 费率
     */
    private BigDecimal feeRatio;

    /**
     * Column: ttm
     * Remark: 市盈率。
     */
    private BigDecimal ttm;

    /**
     * Column: state
     * Remark: 为当前的步骤号 是否出错看status，status记录当前步骤的状态
     */
    private Integer state;


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
     * Column: principal_ratio
     * Remark: 本金率
     */
    private BigDecimal principalRatio;
    long principalAmount;

    /**
     * Column: bank_purch_sn
     * Remark: 纹银银行承兑订单号
     */
    private String bankPurchSn;

    /**
     * Column: free_ratio
     * Remark: 自由金率
     */
    private BigDecimal freeRatio;

    /**
     * Column: reserve_ratio
     * Remark: 准备金率
     */
    private BigDecimal reserveRatio;

    /**
     * Column: free_amount
     * Remark: 自由金
     */
    private Long freeAmount;

    /**
     * Column: reserve_amount
     * Remark: 准备金
     */
    private Long reserveAmount;

    public static PurchasedBO create(PurchasedResult result) {
        return new PurchasedBO().load(result);
    }

    private PurchasedBO load(PurchasedResult result) {
        this.bankid = result.getBankid();
        this.bankPurchSn = result.getSn();
        this.sn = result.getOutTradeSn();
        this.currency = result.getCurrency();
        this.feeRatio = result.getFreeRatio();
        this.freeAmount = result.getFreeAmount();
        this.freeRatio = result.getFreeRatio();
        this.note = result.getNote();
        this.person = result.getPurchaser();
        this.personName = result.getPersonName();
        this.price = result.getPrice();
        this.principalRatio = result.getPrincipalRatio();
        this.purchAmount = result.getAmount();
        this.reserveAmount = result.getReserveAmount();
        this.reserveRatio = result.getReserveRatio();
        this.serviceFee = result.getServiceFee();
        this.stock = result.getStock();
        this.ttm = result.getTtm();
        this.principalAmount = result.getPrincipalAmount();
        return this;
    }

    public long getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(long principalAmount) {
        this.principalAmount = principalAmount;
    }

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

    public Long getPurchAmount() {
        return purchAmount;
    }

    public void setPurchAmount(Long purchAmount) {
        this.purchAmount = purchAmount;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public BigDecimal getPrincipalRatio() {
        return principalRatio;
    }

    public void setPrincipalRatio(BigDecimal principalRatio) {
        this.principalRatio = principalRatio;
    }

    public String getBankPurchSn() {
        return bankPurchSn;
    }

    public void setBankPurchSn(String bankPurchSn) {
        this.bankPurchSn = bankPurchSn;
    }

    public BigDecimal getFreeRatio() {
        return freeRatio;
    }

    public void setFreeRatio(BigDecimal freeRatio) {
        this.freeRatio = freeRatio;
    }

    public BigDecimal getReserveRatio() {
        return reserveRatio;
    }

    public void setReserveRatio(BigDecimal reserveRatio) {
        this.reserveRatio = reserveRatio;
    }

    public Long getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Long freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Long getReserveAmount() {
        return reserveAmount;
    }

    public void setReserveAmount(Long reserveAmount) {
        this.reserveAmount = reserveAmount;
    }
}

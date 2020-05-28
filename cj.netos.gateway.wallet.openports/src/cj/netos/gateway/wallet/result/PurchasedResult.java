package cj.netos.gateway.wallet.result;

import java.math.BigDecimal;

public class PurchasedResult {
    String sn;
    String purchaser;
    String personName;
    String currency;
    long amount;
    BigDecimal stock;
    BigDecimal price;
    BigDecimal ttm;
    long ptime;
    int state;
    String note;
    long serviceFee;
    BigDecimal principalRatio;
    long principalAmount;
    long tailAmount;
    BigDecimal freeRatio;
    BigDecimal reserveRatio;
    long freeAmount;
    long reserveAmount;
    String bankid;
    String outTradeSn;

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

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public BigDecimal getTtm() {
        return ttm;
    }

    public void setTtm(BigDecimal ttm) {
        this.ttm = ttm;
    }

    public long getPtime() {
        return ptime;
    }

    public void setPtime(long ptime) {
        this.ptime = ptime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getPrincipalRatio() {
        return principalRatio;
    }

    public void setPrincipalRatio(BigDecimal principalRatio) {
        this.principalRatio = principalRatio;
    }

    public long getTailAmount() {
        return tailAmount;
    }

    public void setTailAmount(long tailAmount) {
        this.tailAmount = tailAmount;
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

    public long getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(long freeAmount) {
        this.freeAmount = freeAmount;
    }

    public long getReserveAmount() {
        return reserveAmount;
    }

    public void setReserveAmount(long reserveAmount) {
        this.reserveAmount = reserveAmount;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn;
    }
}

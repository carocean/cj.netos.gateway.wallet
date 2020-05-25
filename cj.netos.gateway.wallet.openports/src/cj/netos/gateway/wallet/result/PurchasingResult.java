package cj.netos.gateway.wallet.result;

import java.math.BigDecimal;

public class PurchasingResult {
    long serviceFee;
    String note;
    long amount;
    String purchaser;
    BigDecimal principalRatio;
    BigDecimal ttm;
    long principalAmount;
    String personName;
    String outTradeSn;
    String bankid;
    long tailAmount;
    String currency;
    String sn;
    int state;
    long ptime;
    BigDecimal feeRatio;

    public long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public BigDecimal getPrincipalRatio() {
        return principalRatio;
    }

    public void setPrincipalRatio(BigDecimal principalRatio) {
        this.principalRatio = principalRatio;
    }

    public BigDecimal getTtm() {
        return ttm;
    }

    public void setTtm(BigDecimal ttm) {
        this.ttm = ttm;
    }

    public long getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(long principalAmount) {
        this.principalAmount = principalAmount;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public long getTailAmount() {
        return tailAmount;
    }

    public void setTailAmount(long tailAmount) {
        this.tailAmount = tailAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getPtime() {
        return ptime;
    }

    public void setPtime(long ptime) {
        this.ptime = ptime;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }
}

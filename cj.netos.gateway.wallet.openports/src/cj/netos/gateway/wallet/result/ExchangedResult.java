package cj.netos.gateway.wallet.result;

import cj.netos.gateway.wallet.model.WenyExchangeRecord;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangedResult {
    String sn;
    String exchanger;
    String personName;
    String currency;
    long amount;
    BigDecimal stock;
    BigDecimal price;
    BigDecimal ttm;
    String refPurchase;
    long profit;
    long purchaseAmount;
    long principalAmount;
    long serviceFeeamount;
    BigDecimal purchasePrice;
    String ctime;
    int state;
    String note;
    String bankid;
    String outTradeSn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getExchanger() {
        return exchanger;
    }

    public void setExchanger(String exchanger) {
        this.exchanger = exchanger;
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

    public String getRefPurchase() {
        return refPurchase;
    }

    public void setRefPurchase(String refPurchase) {
        this.refPurchase = refPurchase;
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public long getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(long principalAmount) {
        this.principalAmount = principalAmount;
    }

    public long getServiceFeeamount() {
        return serviceFeeamount;
    }

    public void setServiceFeeamount(long serviceFeeamount) {
        this.serviceFeeamount = serviceFeeamount;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
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

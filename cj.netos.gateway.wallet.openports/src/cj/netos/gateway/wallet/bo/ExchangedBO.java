package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.result.ExchangedResult;

import java.math.BigDecimal;

public class ExchangedBO {
    String sn;
    String exchanger;
    String personName;
    String currency;
    long amount;
    BigDecimal stock;
    BigDecimal price;
    BigDecimal ttm;
    long profit;
    long purchaseAmount;
    long principalAmount;
    long serviceFeeamount;
    BigDecimal purchasePrice;
    String ctime;
    int state;
    String note;
    String bankid;
    String bankPurchaseSn;
    String bankExchangeSn;
    String walletPurchaseSn;

    public void load(ExchangedResult result,String refsn) {
        sn = result.getOutTradeSn();
        exchanger = result.getExchanger();
        personName = result.getPersonName();
        currency = result.getCurrency();
        amount = result.getAmount();
        stock = result.getStock();
        price = result.getPrice();
        ttm = result.getTtm();
        profit = result.getProfit();
        purchaseAmount = result.getPurchaseAmount();
        principalAmount = result.getPrincipalAmount();
        serviceFeeamount = result.getServiceFeeamount();
        purchasePrice = result.getPurchasePrice();
        ctime = result.getCtime();
        state = result.getState();
        note = result.getNote();
        bankid = result.getBankid();
        bankExchangeSn = result.getSn();
        bankPurchaseSn = result.getRefPurchase();
        walletPurchaseSn = refsn;
    }

    public String getWalletPurchaseSn() {
        return walletPurchaseSn;
    }

    public void setWalletPurchaseSn(String walletPurchaseSn) {
        this.walletPurchaseSn = walletPurchaseSn;
    }

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

    public String getBankPurchaseSn() {
        return bankPurchaseSn;
    }

    public void setBankPurchaseSn(String bankPurchaseSn) {
        this.bankPurchaseSn = bankPurchaseSn;
    }

    public String getBankExchangeSn() {
        return bankExchangeSn;
    }

    public void setBankExchangeSn(String bankExchangeSn) {
        this.bankExchangeSn = bankExchangeSn;
    }

}

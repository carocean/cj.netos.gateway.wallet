package cj.netos.gateway.wallet.bo;

public class RechargeBO {
    String recharger;
    String rechargerName;
    String appid;
    String currency;
    long amount;
    String paymentChannelID;
    long ctime;
    String device;
    String note;

    public String getRechargerName() {
        return rechargerName;
    }

    public void setRechargerName(String rechargerName) {
        this.rechargerName = rechargerName;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRecharger() {
        return recharger;
    }

    public void setRecharger(String recharger) {
        this.recharger = recharger;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPaymentChannelID() {
        return paymentChannelID;
    }

    public void setPaymentChannelID(String paymentChannelID) {
        this.paymentChannelID = paymentChannelID;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

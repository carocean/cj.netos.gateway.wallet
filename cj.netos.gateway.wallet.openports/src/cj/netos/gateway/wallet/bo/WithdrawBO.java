package cj.netos.gateway.wallet.bo;

public class WithdrawBO {
    String witchrawer;
    String witchrawerName;
    String appid;
    String currency;
    long amount;
    String paymentChannelID;
    long ctime;
    String device;
    String note;

    public String getWitchrawer() {
        return witchrawer;
    }

    public void setWitchrawer(String witchrawer) {
        this.witchrawer = witchrawer;
    }

    public String getWitchrawerName() {
        return witchrawerName;
    }

    public void setWitchrawerName(String witchrawerName) {
        this.witchrawerName = witchrawerName;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

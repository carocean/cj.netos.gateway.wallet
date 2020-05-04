package cj.netos.gateway.wallet.bo;

public class WithdrawBO {
    String witchrawer;
    String appid;
    long amount;
    String paymentChannelPath;
    long ctime;
    String memo;

    public String getWitchrawer() {
        return witchrawer;
    }

    public void setWitchrawer(String witchrawer) {
        this.witchrawer = witchrawer;
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

    public String getPaymentChannelPath() {
        return paymentChannelPath;
    }

    public void setPaymentChannelPath(String paymentChannelPath) {
        this.paymentChannelPath = paymentChannelPath;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}

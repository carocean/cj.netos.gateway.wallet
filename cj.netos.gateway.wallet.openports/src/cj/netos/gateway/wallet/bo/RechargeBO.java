package cj.netos.gateway.wallet.bo;

public class RechargeBO {
    String recharger;
    String appid;
    long amount;
    String paymentChannelPath;
    long ctime;
    String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

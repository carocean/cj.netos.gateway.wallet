package cj.netos.gateway.wallet.bo;

public class GatheringBO {
    String payer;
    String payee;
    String appid;
    long amount;
    String type;
    long ctime;
    TradeDetailsBO details;

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public TradeDetailsBO getDetails() {
        return details;
    }

    public void setDetails(TradeDetailsBO details) {
        this.details = details;
    }
}

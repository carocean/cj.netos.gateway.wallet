package cj.netos.gateway.wallet.bo;

public class QueryAmountBO {
    String appid;
    String queryer;
    long ctime;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getQueryer() {
        return queryer;
    }

    public void setQueryer(String queryer) {
        this.queryer = queryer;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}

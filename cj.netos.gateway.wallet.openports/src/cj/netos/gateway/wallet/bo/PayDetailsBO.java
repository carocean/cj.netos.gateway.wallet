package cj.netos.gateway.wallet.bo;

public class PayDetailsBO {
    String merchid;
    String merchName;
    String orderno;
    String orderTitle;
    String serviceid;//使用什么服务导致的交易，如发红包服务、转账服务等等
    String serviceName;
    String note;

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMerchid() {
        return merchid;
    }

    public void setMerchid(String merchid) {
        this.merchid = merchid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

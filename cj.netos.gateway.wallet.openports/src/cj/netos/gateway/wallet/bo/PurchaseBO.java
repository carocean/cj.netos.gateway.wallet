package cj.netos.gateway.wallet.bo;

public class PurchaseBO {
    String sn;
    String Purchaser;
    String PurchaserName;
    String currency;
    long amount;
    String wenyBankID;
    String ctime;
    String note;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPurchaser() {
        return Purchaser;
    }

    public void setPurchaser(String purchaser) {
        Purchaser = purchaser;
    }

    public String getPurchaserName() {
        return PurchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        PurchaserName = purchaserName;
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

    public String getWenyBankID() {
        return wenyBankID;
    }

    public void setWenyBankID(String wenyBankID) {
        this.wenyBankID = wenyBankID;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.WenyPurchRecord;

public class PurchaseBO {
    String sn;
    String purchaser;
    String purchaserName;
    String currency;
    long amount;
    String wenyBankID;
    String ctime;
    String note;
    String status;
    String message;
    public static PurchaseBO create(WenyPurchRecord record) {
        PurchaseBO bo = new PurchaseBO();
        bo.setSn(record.getSn());
        bo.setPurchaser(record.getPerson());
        bo.setPurchaserName(record.getPersonName());
        bo.setNote(record.getNote());
        bo.setCurrency(record.getCurrency());
        bo.setWenyBankID(record.getBankid());
        bo.setAmount(record.getPurchAmount());
        bo.setCtime(record.getCtime());
        return bo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
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

package cj.netos.gateway.wallet.result;

public class PayResult {
    String sn;
    String wenyBankID;
    String person;
    String status;
    String message;
    Object record;

    public PayResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getWenyBankID() {
        return wenyBankID;
    }

    public void setWenyBankID(String wenyBankID) {
        this.wenyBankID = wenyBankID;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public Object getRecord() {
        return record;
    }

    public void setRecord(Object record) {
        this.record = record;
    }
}

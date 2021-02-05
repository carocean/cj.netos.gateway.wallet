package cj.netos.gateway.wallet.result;

public class ModuleTransinResult {
    String sn;
    String person;
    String status;
    String message;
    String modileId;
    Object record;

    public String getModileId() {
        return modileId;
    }

    public void setModileId(String modileId) {
        this.modileId = modileId;
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

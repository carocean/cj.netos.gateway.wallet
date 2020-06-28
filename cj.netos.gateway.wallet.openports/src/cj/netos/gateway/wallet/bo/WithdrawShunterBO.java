package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.result.WithdrawShunterResult;

public class WithdrawShunterBO {
    String sn;
    String wenyBankID;
    String shunter;
    String person;
    String personName;
    long demandAmount;
    long realAmount;
    String ctime;
    String note;
    String currency;
    String outTradeSn;
    public static WithdrawShunterBO create(WithdrawShunterResult result) {
        WithdrawShunterBO bo = new WithdrawShunterBO();
        bo.setCurrency("CNY");
        bo.setCtime(result.getCtime());
        bo.setDemandAmount(result.getReqAmount());
        bo.setNote(result.getNote());
        bo.setPerson(result.getWithdrawer());
        bo.setPersonName(result.getPersonName());
        bo.setWenyBankID(result.getBankid());
        bo.setShunter(result.getShunter());
        bo.setSn(result.getOutTradeSn());
        bo.setOutTradeSn(result.getSn());
        bo.setRealAmount(result.getRealAmount());
        return bo;
    }

    public long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(long realAmount) {
        this.realAmount = realAmount;
    }

    public String getOutTradeSn() {
        return outTradeSn;
    }

    public void setOutTradeSn(String outTradeSn) {
        this.outTradeSn = outTradeSn;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getShunter() {
        return shunter;
    }

    public void setShunter(String shunter) {
        this.shunter = shunter;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(long demandAmount) {
        this.demandAmount = demandAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

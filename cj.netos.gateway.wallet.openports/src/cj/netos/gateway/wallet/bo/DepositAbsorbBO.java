package cj.netos.gateway.wallet.bo;

import java.math.BigDecimal;

public class DepositAbsorbBO {
    String sn;
    String person;
    String personName;
    String sourceCode;
    String sourceTitle;
    BigDecimal demandAmount;
    String note;
     String currency;

    public DepositAbsorbBO() {
    }

    public DepositAbsorbBO(String sn, String person, String personName, String sourceCode, String sourceTitle, BigDecimal demandAmount, String note, String currency) {
        this.sn = sn;
        this.person = person;
        this.personName = personName;
        this.sourceCode = sourceCode;
        this.sourceTitle = sourceTitle;
        this.demandAmount = demandAmount;
        this.note = note;
        this.currency = currency;
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

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public BigDecimal getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(BigDecimal demandAmount) {
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

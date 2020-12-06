package cj.netos.gateway.wallet.bo;

public class DepositTrialBO {

    private long amount;
    private String sn;
    private String currency;
    private String note;
    private String person;
    private String personName;

    public void setSn(String sn) {

        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setCurrency(String currency) {

        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setNote(String note) {

        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setPerson(String person) {

        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public void setPersonName(String personName) {

        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setAmount(long amount) {

        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}

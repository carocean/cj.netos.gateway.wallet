package cj.netos.gateway.wallet.model;

/**
 * Table: trial_funds_config
 */
public class TrialFundsConfig {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: state
     * Remark: 状态 0为关闭体验金 1为开启体验金
     */
    private Integer state;

    /**
     * Column: remit_account
     * Remark: 划扣账户，必须指定，如果划扣账户零钱为0则不发体验金
     */
    private String remitAccount;

    /**
     * Column: remit_name
     * Remark: 划扣账户名
     */
    private String remitName;

    /**
     * Column: trial_amount
     * Remark: 每笔体验划扣金额
     */
    private Long trialAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemitAccount() {
        return remitAccount;
    }

    public void setRemitAccount(String remitAccount) {
        this.remitAccount = remitAccount == null ? null : remitAccount.trim();
    }

    public String getRemitName() {
        return remitName;
    }

    public void setRemitName(String remitName) {
        this.remitName = remitName == null ? null : remitName.trim();
    }

    public Long getTrialAmount() {
        return trialAmount;
    }

    public void setTrialAmount(Long trialAmount) {
        this.trialAmount = trialAmount;
    }
}
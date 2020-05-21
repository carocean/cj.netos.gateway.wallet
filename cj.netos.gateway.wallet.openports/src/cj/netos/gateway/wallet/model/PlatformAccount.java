package cj.netos.gateway.wallet.model;

/**
 * Table: platform_account
 */
public class PlatformAccount {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: withdraw_amount
     * Remark: 提现总金额
     */
    private Long withdrawAmount;

    /**
     * Column: recharge_amount
     * Remark: 充值总金额
     */
    private Long rechargeAmount;

    /**
     * Column: balance_amount
     * Remark: 余额
     */
    private Long balanceAmount;

    /**
     * Column: utime
     * Remark: 更新时间
     */
    private String utime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Long withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Long getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Long rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime == null ? null : utime.trim();
    }
}
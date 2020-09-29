package cj.netos.gateway.wallet.model;

/**
 * Table: withdraw_from
 */
public class WithdrawFrom {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: channel_account
     * Remark: 出款的平台渠道账户 出款时是任意选择渠道账户出款的，不论收款的方式（用户支付宝账户、用户网联卡）是什么
     */
    private String channelAccount;

    /**
     * Column: amount
     * Remark: 账户内出款金额
     */
    private Long amount;

    /**
     * Column: withdraw_sn
     * Remark: 关联提现记录标识
     */
    private String withdrawSn;

    /**
     * Column: ctime
     * Remark: 出款时间
     */
    private String ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount == null ? null : channelAccount.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getWithdrawSn() {
        return withdrawSn;
    }

    public void setWithdrawSn(String withdrawSn) {
        this.withdrawSn = withdrawSn == null ? null : withdrawSn.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }
}
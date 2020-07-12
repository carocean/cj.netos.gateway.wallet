package cj.netos.gateway.wallet.model;

/**
 * Table: p2p_ evidence
 */
public class P2pEvidence {
    /**
     * Column: sn
     * Remark: 标识，即凭证。为md5值=key+nonce nonce=principal+actor+uuid+currentDate
     */
    private String sn;

    /**
     * Column: principal
     * Remark: 当事人 如果direct=payee是收款人，为payer是付款人
     */
    private String principal;

    /**
     * Column: nick_name
     */
    private String nickName;

    /**
     * Column: actor
     * Remark: 当事人角色：payee收款人；payer付款人
     */
    private String actor;

    /**
     * Column: pub_time
     * Remark: 发布时间
     */
    private Long pubTime;

    /**
     * Column: expire
     * Remark: 过期日期，默认1分钟后，单位毫秒
     */
    private Long expire;

    /**
     * Column: use_times
     * Remark: 可用次数，可以使用此凭证交易的次数，默认是1次，0为不限制即可在过期时间内多次交易（一般用于将收款码张贴出去用作营业收款码）
     */
    private Long useTimes;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor == null ? null : actor.trim();
    }

    public Long getPubTime() {
        return pubTime;
    }

    public void setPubTime(Long pubTime) {
        this.pubTime = pubTime;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Long getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Long useTimes) {
        this.useTimes = useTimes;
    }
}
package cj.netos.gateway.wallet.model;

import java.math.BigDecimal;

/**
 * Table: channel_ratio
 */
public class ChannelRatio {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: channel
     * Remark: 支付渠道标识
     */
    private String channel;

    /**
     * Column: min_bound
     * Remark: 费率的下边界，单位为分 
     */
    private Long minBound;

    /**
     * Column: max_bound
     * Remark: 费率的上边界，单位为分
     */
    private Long maxBound;

    /**
     * Column: fee_ratio
     * Remark: 边界内的费率  费率的作用区间是大于等于下边界且小于上边界
     */
    private BigDecimal feeRatio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Long getMinBound() {
        return minBound;
    }

    public void setMinBound(Long minBound) {
        this.minBound = minBound;
    }

    public Long getMaxBound() {
        return maxBound;
    }

    public void setMaxBound(Long maxBound) {
        this.maxBound = maxBound;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }
}
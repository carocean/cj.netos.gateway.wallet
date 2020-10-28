package cj.netos.gateway.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChannelRatioExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public ChannelRatioExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andMinBoundIsNull() {
            addCriterion("min_bound is null");
            return (Criteria) this;
        }

        public Criteria andMinBoundIsNotNull() {
            addCriterion("min_bound is not null");
            return (Criteria) this;
        }

        public Criteria andMinBoundEqualTo(Long value) {
            addCriterion("min_bound =", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundNotEqualTo(Long value) {
            addCriterion("min_bound <>", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundGreaterThan(Long value) {
            addCriterion("min_bound >", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundGreaterThanOrEqualTo(Long value) {
            addCriterion("min_bound >=", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundLessThan(Long value) {
            addCriterion("min_bound <", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundLessThanOrEqualTo(Long value) {
            addCriterion("min_bound <=", value, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundIn(List<Long> values) {
            addCriterion("min_bound in", values, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundNotIn(List<Long> values) {
            addCriterion("min_bound not in", values, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundBetween(Long value1, Long value2) {
            addCriterion("min_bound between", value1, value2, "minBound");
            return (Criteria) this;
        }

        public Criteria andMinBoundNotBetween(Long value1, Long value2) {
            addCriterion("min_bound not between", value1, value2, "minBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundIsNull() {
            addCriterion("max_bound is null");
            return (Criteria) this;
        }

        public Criteria andMaxBoundIsNotNull() {
            addCriterion("max_bound is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBoundEqualTo(Long value) {
            addCriterion("max_bound =", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundNotEqualTo(Long value) {
            addCriterion("max_bound <>", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundGreaterThan(Long value) {
            addCriterion("max_bound >", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundGreaterThanOrEqualTo(Long value) {
            addCriterion("max_bound >=", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundLessThan(Long value) {
            addCriterion("max_bound <", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundLessThanOrEqualTo(Long value) {
            addCriterion("max_bound <=", value, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundIn(List<Long> values) {
            addCriterion("max_bound in", values, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundNotIn(List<Long> values) {
            addCriterion("max_bound not in", values, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundBetween(Long value1, Long value2) {
            addCriterion("max_bound between", value1, value2, "maxBound");
            return (Criteria) this;
        }

        public Criteria andMaxBoundNotBetween(Long value1, Long value2) {
            addCriterion("max_bound not between", value1, value2, "maxBound");
            return (Criteria) this;
        }

        public Criteria andFeeRatioIsNull() {
            addCriterion("fee_ratio is null");
            return (Criteria) this;
        }

        public Criteria andFeeRatioIsNotNull() {
            addCriterion("fee_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRatioEqualTo(BigDecimal value) {
            addCriterion("fee_ratio =", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioNotEqualTo(BigDecimal value) {
            addCriterion("fee_ratio <>", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioGreaterThan(BigDecimal value) {
            addCriterion("fee_ratio >", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_ratio >=", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioLessThan(BigDecimal value) {
            addCriterion("fee_ratio <", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_ratio <=", value, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioIn(List<BigDecimal> values) {
            addCriterion("fee_ratio in", values, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioNotIn(List<BigDecimal> values) {
            addCriterion("fee_ratio not in", values, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_ratio between", value1, value2, "feeRatio");
            return (Criteria) this;
        }

        public Criteria andFeeRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_ratio not between", value1, value2, "feeRatio");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
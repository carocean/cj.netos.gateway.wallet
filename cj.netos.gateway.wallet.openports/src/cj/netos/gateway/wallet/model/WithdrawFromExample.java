package cj.netos.gateway.wallet.model;

import java.util.ArrayList;
import java.util.List;

public class WithdrawFromExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public WithdrawFromExample() {
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

        public Criteria andChannelAccountIsNull() {
            addCriterion("channel_account is null");
            return (Criteria) this;
        }

        public Criteria andChannelAccountIsNotNull() {
            addCriterion("channel_account is not null");
            return (Criteria) this;
        }

        public Criteria andChannelAccountEqualTo(String value) {
            addCriterion("channel_account =", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountNotEqualTo(String value) {
            addCriterion("channel_account <>", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountGreaterThan(String value) {
            addCriterion("channel_account >", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountGreaterThanOrEqualTo(String value) {
            addCriterion("channel_account >=", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountLessThan(String value) {
            addCriterion("channel_account <", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountLessThanOrEqualTo(String value) {
            addCriterion("channel_account <=", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountLike(String value) {
            addCriterion("channel_account like", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountNotLike(String value) {
            addCriterion("channel_account not like", value, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountIn(List<String> values) {
            addCriterion("channel_account in", values, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountNotIn(List<String> values) {
            addCriterion("channel_account not in", values, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountBetween(String value1, String value2) {
            addCriterion("channel_account between", value1, value2, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andChannelAccountNotBetween(String value1, String value2) {
            addCriterion("channel_account not between", value1, value2, "channelAccount");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnIsNull() {
            addCriterion("withdraw_sn is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnIsNotNull() {
            addCriterion("withdraw_sn is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnEqualTo(String value) {
            addCriterion("withdraw_sn =", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnNotEqualTo(String value) {
            addCriterion("withdraw_sn <>", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnGreaterThan(String value) {
            addCriterion("withdraw_sn >", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_sn >=", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnLessThan(String value) {
            addCriterion("withdraw_sn <", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnLessThanOrEqualTo(String value) {
            addCriterion("withdraw_sn <=", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnLike(String value) {
            addCriterion("withdraw_sn like", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnNotLike(String value) {
            addCriterion("withdraw_sn not like", value, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnIn(List<String> values) {
            addCriterion("withdraw_sn in", values, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnNotIn(List<String> values) {
            addCriterion("withdraw_sn not in", values, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnBetween(String value1, String value2) {
            addCriterion("withdraw_sn between", value1, value2, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andWithdrawSnNotBetween(String value1, String value2) {
            addCriterion("withdraw_sn not between", value1, value2, "withdrawSn");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(String value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(String value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(String value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(String value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(String value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(String value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLike(String value) {
            addCriterion("ctime like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotLike(String value) {
            addCriterion("ctime not like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<String> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<String> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(String value1, String value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(String value1, String value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
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
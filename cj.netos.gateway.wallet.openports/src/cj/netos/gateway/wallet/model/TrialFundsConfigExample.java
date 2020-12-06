package cj.netos.gateway.wallet.model;

import java.util.ArrayList;
import java.util.List;

public class TrialFundsConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public TrialFundsConfigExample() {
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

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRemitAccountIsNull() {
            addCriterion("remit_account is null");
            return (Criteria) this;
        }

        public Criteria andRemitAccountIsNotNull() {
            addCriterion("remit_account is not null");
            return (Criteria) this;
        }

        public Criteria andRemitAccountEqualTo(String value) {
            addCriterion("remit_account =", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountNotEqualTo(String value) {
            addCriterion("remit_account <>", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountGreaterThan(String value) {
            addCriterion("remit_account >", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountGreaterThanOrEqualTo(String value) {
            addCriterion("remit_account >=", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountLessThan(String value) {
            addCriterion("remit_account <", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountLessThanOrEqualTo(String value) {
            addCriterion("remit_account <=", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountLike(String value) {
            addCriterion("remit_account like", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountNotLike(String value) {
            addCriterion("remit_account not like", value, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountIn(List<String> values) {
            addCriterion("remit_account in", values, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountNotIn(List<String> values) {
            addCriterion("remit_account not in", values, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountBetween(String value1, String value2) {
            addCriterion("remit_account between", value1, value2, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitAccountNotBetween(String value1, String value2) {
            addCriterion("remit_account not between", value1, value2, "remitAccount");
            return (Criteria) this;
        }

        public Criteria andRemitNameIsNull() {
            addCriterion("remit_name is null");
            return (Criteria) this;
        }

        public Criteria andRemitNameIsNotNull() {
            addCriterion("remit_name is not null");
            return (Criteria) this;
        }

        public Criteria andRemitNameEqualTo(String value) {
            addCriterion("remit_name =", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameNotEqualTo(String value) {
            addCriterion("remit_name <>", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameGreaterThan(String value) {
            addCriterion("remit_name >", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameGreaterThanOrEqualTo(String value) {
            addCriterion("remit_name >=", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameLessThan(String value) {
            addCriterion("remit_name <", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameLessThanOrEqualTo(String value) {
            addCriterion("remit_name <=", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameLike(String value) {
            addCriterion("remit_name like", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameNotLike(String value) {
            addCriterion("remit_name not like", value, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameIn(List<String> values) {
            addCriterion("remit_name in", values, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameNotIn(List<String> values) {
            addCriterion("remit_name not in", values, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameBetween(String value1, String value2) {
            addCriterion("remit_name between", value1, value2, "remitName");
            return (Criteria) this;
        }

        public Criteria andRemitNameNotBetween(String value1, String value2) {
            addCriterion("remit_name not between", value1, value2, "remitName");
            return (Criteria) this;
        }

        public Criteria andTrialAmountIsNull() {
            addCriterion("trial_amount is null");
            return (Criteria) this;
        }

        public Criteria andTrialAmountIsNotNull() {
            addCriterion("trial_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTrialAmountEqualTo(Long value) {
            addCriterion("trial_amount =", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountNotEqualTo(Long value) {
            addCriterion("trial_amount <>", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountGreaterThan(Long value) {
            addCriterion("trial_amount >", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("trial_amount >=", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountLessThan(Long value) {
            addCriterion("trial_amount <", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountLessThanOrEqualTo(Long value) {
            addCriterion("trial_amount <=", value, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountIn(List<Long> values) {
            addCriterion("trial_amount in", values, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountNotIn(List<Long> values) {
            addCriterion("trial_amount not in", values, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountBetween(Long value1, Long value2) {
            addCriterion("trial_amount between", value1, value2, "trialAmount");
            return (Criteria) this;
        }

        public Criteria andTrialAmountNotBetween(Long value1, Long value2) {
            addCriterion("trial_amount not between", value1, value2, "trialAmount");
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
package cj.netos.gateway.wallet.model;

import java.util.ArrayList;
import java.util.List;

public class RechargeRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public RechargeRecordExample() {
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

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andPersonIsNull() {
            addCriterion("person is null");
            return (Criteria) this;
        }

        public Criteria andPersonIsNotNull() {
            addCriterion("person is not null");
            return (Criteria) this;
        }

        public Criteria andPersonEqualTo(String value) {
            addCriterion("person =", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotEqualTo(String value) {
            addCriterion("person <>", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThan(String value) {
            addCriterion("person >", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThanOrEqualTo(String value) {
            addCriterion("person >=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThan(String value) {
            addCriterion("person <", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThanOrEqualTo(String value) {
            addCriterion("person <=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLike(String value) {
            addCriterion("person like", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotLike(String value) {
            addCriterion("person not like", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonIn(List<String> values) {
            addCriterion("person in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotIn(List<String> values) {
            addCriterion("person not in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonBetween(String value1, String value2) {
            addCriterion("person between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotBetween(String value1, String value2) {
            addCriterion("person not between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andDemandAmountIsNull() {
            addCriterion("demand_amount is null");
            return (Criteria) this;
        }

        public Criteria andDemandAmountIsNotNull() {
            addCriterion("demand_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDemandAmountEqualTo(Long value) {
            addCriterion("demand_amount =", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountNotEqualTo(Long value) {
            addCriterion("demand_amount <>", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountGreaterThan(Long value) {
            addCriterion("demand_amount >", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("demand_amount >=", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountLessThan(Long value) {
            addCriterion("demand_amount <", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountLessThanOrEqualTo(Long value) {
            addCriterion("demand_amount <=", value, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountIn(List<Long> values) {
            addCriterion("demand_amount in", values, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountNotIn(List<Long> values) {
            addCriterion("demand_amount not in", values, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountBetween(Long value1, Long value2) {
            addCriterion("demand_amount between", value1, value2, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andDemandAmountNotBetween(Long value1, Long value2) {
            addCriterion("demand_amount not between", value1, value2, "demandAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountIsNull() {
            addCriterion("real_amount is null");
            return (Criteria) this;
        }

        public Criteria andRealAmountIsNotNull() {
            addCriterion("real_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRealAmountEqualTo(Long value) {
            addCriterion("real_amount =", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotEqualTo(Long value) {
            addCriterion("real_amount <>", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountGreaterThan(Long value) {
            addCriterion("real_amount >", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("real_amount >=", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountLessThan(Long value) {
            addCriterion("real_amount <", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountLessThanOrEqualTo(Long value) {
            addCriterion("real_amount <=", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountIn(List<Long> values) {
            addCriterion("real_amount in", values, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotIn(List<Long> values) {
            addCriterion("real_amount not in", values, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountBetween(Long value1, Long value2) {
            addCriterion("real_amount between", value1, value2, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotBetween(Long value1, Long value2) {
            addCriterion("real_amount not between", value1, value2, "realAmount");
            return (Criteria) this;
        }

        public Criteria andFromChannelIsNull() {
            addCriterion("from_channel is null");
            return (Criteria) this;
        }

        public Criteria andFromChannelIsNotNull() {
            addCriterion("from_channel is not null");
            return (Criteria) this;
        }

        public Criteria andFromChannelEqualTo(String value) {
            addCriterion("from_channel =", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelNotEqualTo(String value) {
            addCriterion("from_channel <>", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelGreaterThan(String value) {
            addCriterion("from_channel >", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelGreaterThanOrEqualTo(String value) {
            addCriterion("from_channel >=", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelLessThan(String value) {
            addCriterion("from_channel <", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelLessThanOrEqualTo(String value) {
            addCriterion("from_channel <=", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelLike(String value) {
            addCriterion("from_channel like", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelNotLike(String value) {
            addCriterion("from_channel not like", value, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelIn(List<String> values) {
            addCriterion("from_channel in", values, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelNotIn(List<String> values) {
            addCriterion("from_channel not in", values, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelBetween(String value1, String value2) {
            addCriterion("from_channel between", value1, value2, "fromChannel");
            return (Criteria) this;
        }

        public Criteria andFromChannelNotBetween(String value1, String value2) {
            addCriterion("from_channel not between", value1, value2, "fromChannel");
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

        public Criteria andLutimeIsNull() {
            addCriterion("lutime is null");
            return (Criteria) this;
        }

        public Criteria andLutimeIsNotNull() {
            addCriterion("lutime is not null");
            return (Criteria) this;
        }

        public Criteria andLutimeEqualTo(String value) {
            addCriterion("lutime =", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeNotEqualTo(String value) {
            addCriterion("lutime <>", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeGreaterThan(String value) {
            addCriterion("lutime >", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeGreaterThanOrEqualTo(String value) {
            addCriterion("lutime >=", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeLessThan(String value) {
            addCriterion("lutime <", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeLessThanOrEqualTo(String value) {
            addCriterion("lutime <=", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeLike(String value) {
            addCriterion("lutime like", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeNotLike(String value) {
            addCriterion("lutime not like", value, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeIn(List<String> values) {
            addCriterion("lutime in", values, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeNotIn(List<String> values) {
            addCriterion("lutime not in", values, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeBetween(String value1, String value2) {
            addCriterion("lutime between", value1, value2, "lutime");
            return (Criteria) this;
        }

        public Criteria andLutimeNotBetween(String value1, String value2) {
            addCriterion("lutime not between", value1, value2, "lutime");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andSettleCodeIsNull() {
            addCriterion("settle_code is null");
            return (Criteria) this;
        }

        public Criteria andSettleCodeIsNotNull() {
            addCriterion("settle_code is not null");
            return (Criteria) this;
        }

        public Criteria andSettleCodeEqualTo(String value) {
            addCriterion("settle_code =", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeNotEqualTo(String value) {
            addCriterion("settle_code <>", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeGreaterThan(String value) {
            addCriterion("settle_code >", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("settle_code >=", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeLessThan(String value) {
            addCriterion("settle_code <", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeLessThanOrEqualTo(String value) {
            addCriterion("settle_code <=", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeLike(String value) {
            addCriterion("settle_code like", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeNotLike(String value) {
            addCriterion("settle_code not like", value, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeIn(List<String> values) {
            addCriterion("settle_code in", values, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeNotIn(List<String> values) {
            addCriterion("settle_code not in", values, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeBetween(String value1, String value2) {
            addCriterion("settle_code between", value1, value2, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleCodeNotBetween(String value1, String value2) {
            addCriterion("settle_code not between", value1, value2, "settleCode");
            return (Criteria) this;
        }

        public Criteria andSettleMsgIsNull() {
            addCriterion("settle_msg is null");
            return (Criteria) this;
        }

        public Criteria andSettleMsgIsNotNull() {
            addCriterion("settle_msg is not null");
            return (Criteria) this;
        }

        public Criteria andSettleMsgEqualTo(String value) {
            addCriterion("settle_msg =", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgNotEqualTo(String value) {
            addCriterion("settle_msg <>", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgGreaterThan(String value) {
            addCriterion("settle_msg >", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgGreaterThanOrEqualTo(String value) {
            addCriterion("settle_msg >=", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgLessThan(String value) {
            addCriterion("settle_msg <", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgLessThanOrEqualTo(String value) {
            addCriterion("settle_msg <=", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgLike(String value) {
            addCriterion("settle_msg like", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgNotLike(String value) {
            addCriterion("settle_msg not like", value, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgIn(List<String> values) {
            addCriterion("settle_msg in", values, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgNotIn(List<String> values) {
            addCriterion("settle_msg not in", values, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgBetween(String value1, String value2) {
            addCriterion("settle_msg between", value1, value2, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andSettleMsgNotBetween(String value1, String value2) {
            addCriterion("settle_msg not between", value1, value2, "settleMsg");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andOcCodeIsNull() {
            addCriterion("oc_code is null");
            return (Criteria) this;
        }

        public Criteria andOcCodeIsNotNull() {
            addCriterion("oc_code is not null");
            return (Criteria) this;
        }

        public Criteria andOcCodeEqualTo(String value) {
            addCriterion("oc_code =", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeNotEqualTo(String value) {
            addCriterion("oc_code <>", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeGreaterThan(String value) {
            addCriterion("oc_code >", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeGreaterThanOrEqualTo(String value) {
            addCriterion("oc_code >=", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeLessThan(String value) {
            addCriterion("oc_code <", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeLessThanOrEqualTo(String value) {
            addCriterion("oc_code <=", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeLike(String value) {
            addCriterion("oc_code like", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeNotLike(String value) {
            addCriterion("oc_code not like", value, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeIn(List<String> values) {
            addCriterion("oc_code in", values, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeNotIn(List<String> values) {
            addCriterion("oc_code not in", values, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeBetween(String value1, String value2) {
            addCriterion("oc_code between", value1, value2, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcCodeNotBetween(String value1, String value2) {
            addCriterion("oc_code not between", value1, value2, "ocCode");
            return (Criteria) this;
        }

        public Criteria andOcMsgIsNull() {
            addCriterion("oc_msg is null");
            return (Criteria) this;
        }

        public Criteria andOcMsgIsNotNull() {
            addCriterion("oc_msg is not null");
            return (Criteria) this;
        }

        public Criteria andOcMsgEqualTo(String value) {
            addCriterion("oc_msg =", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgNotEqualTo(String value) {
            addCriterion("oc_msg <>", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgGreaterThan(String value) {
            addCriterion("oc_msg >", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgGreaterThanOrEqualTo(String value) {
            addCriterion("oc_msg >=", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgLessThan(String value) {
            addCriterion("oc_msg <", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgLessThanOrEqualTo(String value) {
            addCriterion("oc_msg <=", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgLike(String value) {
            addCriterion("oc_msg like", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgNotLike(String value) {
            addCriterion("oc_msg not like", value, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgIn(List<String> values) {
            addCriterion("oc_msg in", values, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgNotIn(List<String> values) {
            addCriterion("oc_msg not in", values, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgBetween(String value1, String value2) {
            addCriterion("oc_msg between", value1, value2, "ocMsg");
            return (Criteria) this;
        }

        public Criteria andOcMsgNotBetween(String value1, String value2) {
            addCriterion("oc_msg not between", value1, value2, "ocMsg");
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
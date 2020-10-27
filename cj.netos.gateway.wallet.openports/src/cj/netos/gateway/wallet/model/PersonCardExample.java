package cj.netos.gateway.wallet.model;

import java.util.ArrayList;
import java.util.List;

public class PersonCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public PersonCardExample() {
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

        public Criteria andCardSnIsNull() {
            addCriterion("card_sn is null");
            return (Criteria) this;
        }

        public Criteria andCardSnIsNotNull() {
            addCriterion("card_sn is not null");
            return (Criteria) this;
        }

        public Criteria andCardSnEqualTo(String value) {
            addCriterion("card_sn =", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnNotEqualTo(String value) {
            addCriterion("card_sn <>", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnGreaterThan(String value) {
            addCriterion("card_sn >", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnGreaterThanOrEqualTo(String value) {
            addCriterion("card_sn >=", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnLessThan(String value) {
            addCriterion("card_sn <", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnLessThanOrEqualTo(String value) {
            addCriterion("card_sn <=", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnLike(String value) {
            addCriterion("card_sn like", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnNotLike(String value) {
            addCriterion("card_sn not like", value, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnIn(List<String> values) {
            addCriterion("card_sn in", values, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnNotIn(List<String> values) {
            addCriterion("card_sn not in", values, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnBetween(String value1, String value2) {
            addCriterion("card_sn between", value1, value2, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardSnNotBetween(String value1, String value2) {
            addCriterion("card_sn not between", value1, value2, "cardSn");
            return (Criteria) this;
        }

        public Criteria andCardHolderIsNull() {
            addCriterion("card_holder is null");
            return (Criteria) this;
        }

        public Criteria andCardHolderIsNotNull() {
            addCriterion("card_holder is not null");
            return (Criteria) this;
        }

        public Criteria andCardHolderEqualTo(String value) {
            addCriterion("card_holder =", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderNotEqualTo(String value) {
            addCriterion("card_holder <>", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderGreaterThan(String value) {
            addCriterion("card_holder >", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderGreaterThanOrEqualTo(String value) {
            addCriterion("card_holder >=", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderLessThan(String value) {
            addCriterion("card_holder <", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderLessThanOrEqualTo(String value) {
            addCriterion("card_holder <=", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderLike(String value) {
            addCriterion("card_holder like", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderNotLike(String value) {
            addCriterion("card_holder not like", value, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderIn(List<String> values) {
            addCriterion("card_holder in", values, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderNotIn(List<String> values) {
            addCriterion("card_holder not in", values, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderBetween(String value1, String value2) {
            addCriterion("card_holder between", value1, value2, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardHolderNotBetween(String value1, String value2) {
            addCriterion("card_holder not between", value1, value2, "cardHolder");
            return (Criteria) this;
        }

        public Criteria andCardNameIsNull() {
            addCriterion("card_name is null");
            return (Criteria) this;
        }

        public Criteria andCardNameIsNotNull() {
            addCriterion("card_name is not null");
            return (Criteria) this;
        }

        public Criteria andCardNameEqualTo(String value) {
            addCriterion("card_name =", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotEqualTo(String value) {
            addCriterion("card_name <>", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameGreaterThan(String value) {
            addCriterion("card_name >", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameGreaterThanOrEqualTo(String value) {
            addCriterion("card_name >=", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLessThan(String value) {
            addCriterion("card_name <", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLessThanOrEqualTo(String value) {
            addCriterion("card_name <=", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLike(String value) {
            addCriterion("card_name like", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotLike(String value) {
            addCriterion("card_name not like", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameIn(List<String> values) {
            addCriterion("card_name in", values, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotIn(List<String> values) {
            addCriterion("card_name not in", values, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameBetween(String value1, String value2) {
            addCriterion("card_name between", value1, value2, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotBetween(String value1, String value2) {
            addCriterion("card_name not between", value1, value2, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardAvatarIsNull() {
            addCriterion("card_avatar is null");
            return (Criteria) this;
        }

        public Criteria andCardAvatarIsNotNull() {
            addCriterion("card_avatar is not null");
            return (Criteria) this;
        }

        public Criteria andCardAvatarEqualTo(String value) {
            addCriterion("card_avatar =", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarNotEqualTo(String value) {
            addCriterion("card_avatar <>", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarGreaterThan(String value) {
            addCriterion("card_avatar >", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("card_avatar >=", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarLessThan(String value) {
            addCriterion("card_avatar <", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarLessThanOrEqualTo(String value) {
            addCriterion("card_avatar <=", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarLike(String value) {
            addCriterion("card_avatar like", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarNotLike(String value) {
            addCriterion("card_avatar not like", value, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarIn(List<String> values) {
            addCriterion("card_avatar in", values, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarNotIn(List<String> values) {
            addCriterion("card_avatar not in", values, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarBetween(String value1, String value2) {
            addCriterion("card_avatar between", value1, value2, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAvatarNotBetween(String value1, String value2) {
            addCriterion("card_avatar not between", value1, value2, "cardAvatar");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankIsNull() {
            addCriterion("card_attr_bank is null");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankIsNotNull() {
            addCriterion("card_attr_bank is not null");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankEqualTo(String value) {
            addCriterion("card_attr_bank =", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankNotEqualTo(String value) {
            addCriterion("card_attr_bank <>", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankGreaterThan(String value) {
            addCriterion("card_attr_bank >", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankGreaterThanOrEqualTo(String value) {
            addCriterion("card_attr_bank >=", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankLessThan(String value) {
            addCriterion("card_attr_bank <", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankLessThanOrEqualTo(String value) {
            addCriterion("card_attr_bank <=", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankLike(String value) {
            addCriterion("card_attr_bank like", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankNotLike(String value) {
            addCriterion("card_attr_bank not like", value, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankIn(List<String> values) {
            addCriterion("card_attr_bank in", values, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankNotIn(List<String> values) {
            addCriterion("card_attr_bank not in", values, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankBetween(String value1, String value2) {
            addCriterion("card_attr_bank between", value1, value2, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardAttrBankNotBetween(String value1, String value2) {
            addCriterion("card_attr_bank not between", value1, value2, "cardAttrBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankIsNull() {
            addCriterion("card_pub_bank is null");
            return (Criteria) this;
        }

        public Criteria andCardPubBankIsNotNull() {
            addCriterion("card_pub_bank is not null");
            return (Criteria) this;
        }

        public Criteria andCardPubBankEqualTo(String value) {
            addCriterion("card_pub_bank =", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankNotEqualTo(String value) {
            addCriterion("card_pub_bank <>", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankGreaterThan(String value) {
            addCriterion("card_pub_bank >", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankGreaterThanOrEqualTo(String value) {
            addCriterion("card_pub_bank >=", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankLessThan(String value) {
            addCriterion("card_pub_bank <", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankLessThanOrEqualTo(String value) {
            addCriterion("card_pub_bank <=", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankLike(String value) {
            addCriterion("card_pub_bank like", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankNotLike(String value) {
            addCriterion("card_pub_bank not like", value, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankIn(List<String> values) {
            addCriterion("card_pub_bank in", values, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankNotIn(List<String> values) {
            addCriterion("card_pub_bank not in", values, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankBetween(String value1, String value2) {
            addCriterion("card_pub_bank between", value1, value2, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardPubBankNotBetween(String value1, String value2) {
            addCriterion("card_pub_bank not between", value1, value2, "cardPubBank");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("card_type is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("card_type is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(Integer value) {
            addCriterion("card_type =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(Integer value) {
            addCriterion("card_type <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(Integer value) {
            addCriterion("card_type >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_type >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(Integer value) {
            addCriterion("card_type <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(Integer value) {
            addCriterion("card_type <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<Integer> values) {
            addCriterion("card_type in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<Integer> values) {
            addCriterion("card_type not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(Integer value1, Integer value2) {
            addCriterion("card_type between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("card_type not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardPhoneIsNull() {
            addCriterion("card_phone is null");
            return (Criteria) this;
        }

        public Criteria andCardPhoneIsNotNull() {
            addCriterion("card_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCardPhoneEqualTo(String value) {
            addCriterion("card_phone =", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneNotEqualTo(String value) {
            addCriterion("card_phone <>", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneGreaterThan(String value) {
            addCriterion("card_phone >", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("card_phone >=", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneLessThan(String value) {
            addCriterion("card_phone <", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneLessThanOrEqualTo(String value) {
            addCriterion("card_phone <=", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneLike(String value) {
            addCriterion("card_phone like", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneNotLike(String value) {
            addCriterion("card_phone not like", value, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneIn(List<String> values) {
            addCriterion("card_phone in", values, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneNotIn(List<String> values) {
            addCriterion("card_phone not in", values, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneBetween(String value1, String value2) {
            addCriterion("card_phone between", value1, value2, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andCardPhoneNotBetween(String value1, String value2) {
            addCriterion("card_phone not between", value1, value2, "cardPhone");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("pay_channel is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("pay_channel is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(String value) {
            addCriterion("pay_channel =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(String value) {
            addCriterion("pay_channel <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(String value) {
            addCriterion("pay_channel >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(String value) {
            addCriterion("pay_channel >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(String value) {
            addCriterion("pay_channel <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(String value) {
            addCriterion("pay_channel <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLike(String value) {
            addCriterion("pay_channel like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotLike(String value) {
            addCriterion("pay_channel not like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<String> values) {
            addCriterion("pay_channel in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<String> values) {
            addCriterion("pay_channel not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(String value1, String value2) {
            addCriterion("pay_channel between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(String value1, String value2) {
            addCriterion("pay_channel not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayPwdIsNull() {
            addCriterion("pay_pwd is null");
            return (Criteria) this;
        }

        public Criteria andPayPwdIsNotNull() {
            addCriterion("pay_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPayPwdEqualTo(String value) {
            addCriterion("pay_pwd =", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdNotEqualTo(String value) {
            addCriterion("pay_pwd <>", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdGreaterThan(String value) {
            addCriterion("pay_pwd >", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pay_pwd >=", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdLessThan(String value) {
            addCriterion("pay_pwd <", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdLessThanOrEqualTo(String value) {
            addCriterion("pay_pwd <=", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdLike(String value) {
            addCriterion("pay_pwd like", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdNotLike(String value) {
            addCriterion("pay_pwd not like", value, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdIn(List<String> values) {
            addCriterion("pay_pwd in", values, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdNotIn(List<String> values) {
            addCriterion("pay_pwd not in", values, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdBetween(String value1, String value2) {
            addCriterion("pay_pwd between", value1, value2, "payPwd");
            return (Criteria) this;
        }

        public Criteria andPayPwdNotBetween(String value1, String value2) {
            addCriterion("pay_pwd not between", value1, value2, "payPwd");
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
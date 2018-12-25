package com.hworld.canoe.domain.req.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RaceCompetitionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RaceCompetitionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdIsNull() {
            addCriterion("race_group_id is null");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdIsNotNull() {
            addCriterion("race_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdEqualTo(Integer value) {
            addCriterion("race_group_id =", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdNotEqualTo(Integer value) {
            addCriterion("race_group_id <>", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdGreaterThan(Integer value) {
            addCriterion("race_group_id >", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("race_group_id >=", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdLessThan(Integer value) {
            addCriterion("race_group_id <", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("race_group_id <=", value, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdIn(List<Integer> values) {
            addCriterion("race_group_id in", values, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdNotIn(List<Integer> values) {
            addCriterion("race_group_id not in", values, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("race_group_id between", value1, value2, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andRaceGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("race_group_id not between", value1, value2, "raceGroupId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNull() {
            addCriterion("player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Integer value) {
            addCriterion("player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Integer value) {
            addCriterion("player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Integer value) {
            addCriterion("player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Integer value) {
            addCriterion("player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Integer value) {
            addCriterion("player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Integer> values) {
            addCriterion("player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Integer> values) {
            addCriterion("player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Integer value1, Integer value2) {
            addCriterion("player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIsNull() {
            addCriterion("finsh_time is null");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIsNotNull() {
            addCriterion("finsh_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinshTimeEqualTo(Date value) {
            addCriterionForJDBCTime("finsh_time =", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("finsh_time <>", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeGreaterThan(Date value) {
            addCriterionForJDBCTime("finsh_time >", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("finsh_time >=", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeLessThan(Date value) {
            addCriterionForJDBCTime("finsh_time <", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("finsh_time <=", value, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeIn(List<Date> values) {
            addCriterionForJDBCTime("finsh_time in", values, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("finsh_time not in", values, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("finsh_time between", value1, value2, "finshTime");
            return (Criteria) this;
        }

        public Criteria andFinshTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("finsh_time not between", value1, value2, "finshTime");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIsNull() {
            addCriterion("equipment_name is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIsNotNull() {
            addCriterion("equipment_name is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameEqualTo(String value) {
            addCriterion("equipment_name =", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotEqualTo(String value) {
            addCriterion("equipment_name <>", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThan(String value) {
            addCriterion("equipment_name >", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_name >=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThan(String value) {
            addCriterion("equipment_name <", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThanOrEqualTo(String value) {
            addCriterion("equipment_name <=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLike(String value) {
            addCriterion("equipment_name like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotLike(String value) {
            addCriterion("equipment_name not like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIn(List<String> values) {
            addCriterion("equipment_name in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotIn(List<String> values) {
            addCriterion("equipment_name not in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameBetween(String value1, String value2) {
            addCriterion("equipment_name between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotBetween(String value1, String value2) {
            addCriterion("equipment_name not between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNull() {
            addCriterion("equipment_category_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNotNull() {
            addCriterion("equipment_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdEqualTo(Integer value) {
            addCriterion("equipment_category_id =", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotEqualTo(Integer value) {
            addCriterion("equipment_category_id <>", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThan(Integer value) {
            addCriterion("equipment_category_id >", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipment_category_id >=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThan(Integer value) {
            addCriterion("equipment_category_id <", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("equipment_category_id <=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIn(List<Integer> values) {
            addCriterion("equipment_category_id in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotIn(List<Integer> values) {
            addCriterion("equipment_category_id not in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("equipment_category_id between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("equipment_category_id not between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoIsNull() {
            addCriterion("race_player_no is null");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoIsNotNull() {
            addCriterion("race_player_no is not null");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoEqualTo(String value) {
            addCriterion("race_player_no =", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoNotEqualTo(String value) {
            addCriterion("race_player_no <>", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoGreaterThan(String value) {
            addCriterion("race_player_no >", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoGreaterThanOrEqualTo(String value) {
            addCriterion("race_player_no >=", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoLessThan(String value) {
            addCriterion("race_player_no <", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoLessThanOrEqualTo(String value) {
            addCriterion("race_player_no <=", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoLike(String value) {
            addCriterion("race_player_no like", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoNotLike(String value) {
            addCriterion("race_player_no not like", value, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoIn(List<String> values) {
            addCriterion("race_player_no in", values, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoNotIn(List<String> values) {
            addCriterion("race_player_no not in", values, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoBetween(String value1, String value2) {
            addCriterion("race_player_no between", value1, value2, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andRacePlayerNoNotBetween(String value1, String value2) {
            addCriterion("race_player_no not between", value1, value2, "racePlayerNo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("rank is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("rank is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(Integer value) {
            addCriterion("rank =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(Integer value) {
            addCriterion("rank <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(Integer value) {
            addCriterion("rank >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(Integer value) {
            addCriterion("rank <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(Integer value) {
            addCriterion("rank <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<Integer> values) {
            addCriterion("rank in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<Integer> values) {
            addCriterion("rank not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(Integer value1, Integer value2) {
            addCriterion("rank between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(Integer value1, Integer value2) {
            addCriterion("rank not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagIsNull() {
            addCriterion("deleted_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagIsNotNull() {
            addCriterion("deleted_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagEqualTo(Long value) {
            addCriterion("deleted_flag =", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagNotEqualTo(Long value) {
            addCriterion("deleted_flag <>", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagGreaterThan(Long value) {
            addCriterion("deleted_flag >", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("deleted_flag >=", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagLessThan(Long value) {
            addCriterion("deleted_flag <", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagLessThanOrEqualTo(Long value) {
            addCriterion("deleted_flag <=", value, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagIn(List<Long> values) {
            addCriterion("deleted_flag in", values, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagNotIn(List<Long> values) {
            addCriterion("deleted_flag not in", values, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagBetween(Long value1, Long value2) {
            addCriterion("deleted_flag between", value1, value2, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andDeletedFlagNotBetween(Long value1, Long value2) {
            addCriterion("deleted_flag not between", value1, value2, "deletedFlag");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedOnIsNull() {
            addCriterion("created_on is null");
            return (Criteria) this;
        }

        public Criteria andCreatedOnIsNotNull() {
            addCriterion("created_on is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedOnEqualTo(Date value) {
            addCriterion("created_on =", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnNotEqualTo(Date value) {
            addCriterion("created_on <>", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnGreaterThan(Date value) {
            addCriterion("created_on >", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnGreaterThanOrEqualTo(Date value) {
            addCriterion("created_on >=", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnLessThan(Date value) {
            addCriterion("created_on <", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnLessThanOrEqualTo(Date value) {
            addCriterion("created_on <=", value, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnIn(List<Date> values) {
            addCriterion("created_on in", values, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnNotIn(List<Date> values) {
            addCriterion("created_on not in", values, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnBetween(Date value1, Date value2) {
            addCriterion("created_on between", value1, value2, "createdOn");
            return (Criteria) this;
        }

        public Criteria andCreatedOnNotBetween(Date value1, Date value2) {
            addCriterion("created_on not between", value1, value2, "createdOn");
            return (Criteria) this;
        }

        public Criteria andModifiedByIsNull() {
            addCriterion("modified_by is null");
            return (Criteria) this;
        }

        public Criteria andModifiedByIsNotNull() {
            addCriterion("modified_by is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedByEqualTo(String value) {
            addCriterion("modified_by =", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotEqualTo(String value) {
            addCriterion("modified_by <>", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByGreaterThan(String value) {
            addCriterion("modified_by >", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByGreaterThanOrEqualTo(String value) {
            addCriterion("modified_by >=", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByLessThan(String value) {
            addCriterion("modified_by <", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByLessThanOrEqualTo(String value) {
            addCriterion("modified_by <=", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByLike(String value) {
            addCriterion("modified_by like", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotLike(String value) {
            addCriterion("modified_by not like", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByIn(List<String> values) {
            addCriterion("modified_by in", values, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotIn(List<String> values) {
            addCriterion("modified_by not in", values, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByBetween(String value1, String value2) {
            addCriterion("modified_by between", value1, value2, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotBetween(String value1, String value2) {
            addCriterion("modified_by not between", value1, value2, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedOnIsNull() {
            addCriterion("modified_on is null");
            return (Criteria) this;
        }

        public Criteria andModifiedOnIsNotNull() {
            addCriterion("modified_on is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedOnEqualTo(Date value) {
            addCriterion("modified_on =", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnNotEqualTo(Date value) {
            addCriterion("modified_on <>", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnGreaterThan(Date value) {
            addCriterion("modified_on >", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_on >=", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnLessThan(Date value) {
            addCriterion("modified_on <", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnLessThanOrEqualTo(Date value) {
            addCriterion("modified_on <=", value, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnIn(List<Date> values) {
            addCriterion("modified_on in", values, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnNotIn(List<Date> values) {
            addCriterion("modified_on not in", values, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnBetween(Date value1, Date value2) {
            addCriterion("modified_on between", value1, value2, "modifiedOn");
            return (Criteria) this;
        }

        public Criteria andModifiedOnNotBetween(Date value1, Date value2) {
            addCriterion("modified_on not between", value1, value2, "modifiedOn");
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
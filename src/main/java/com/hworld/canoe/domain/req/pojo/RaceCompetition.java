package com.hworld.canoe.domain.req.pojo;

import java.util.Date;

public class RaceCompetition {
    private Integer id;

    private Integer raceGroupId;

    private Integer playerId;

    private Date finshTime;

    private String equipmentName;

    private Integer equipmentCategoryId;

    private String brand;

    private String racePlayerNo;

    private Integer status;

    private Integer rank;

    private Long deletedFlag;

    private String createdBy;

    private Date createdOn;

    private String modifiedBy;

    private Date modifiedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRaceGroupId() {
        return raceGroupId;
    }

    public void setRaceGroupId(Integer raceGroupId) {
        this.raceGroupId = raceGroupId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getRacePlayerNo() {
        return racePlayerNo;
    }

    public void setRacePlayerNo(String racePlayerNo) {
        this.racePlayerNo = racePlayerNo == null ? null : racePlayerNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Long deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
package com.hworld.canoe.framework.db.canoedb.entity;

import java.util.Date;
import javax.persistence.*;

public class Area {
    @Id
    private Long id;

    private String name;

    @Column(name = "level_gap")
    private Integer levelGap;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "deleted_flag")
    private Long deletedFlag;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_on")
    private Date modifiedOn;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return level_gap
     */
    public Integer getLevelGap() {
        return levelGap;
    }

    /**
     * @param levelGap
     */
    public void setLevelGap(Integer levelGap) {
        this.levelGap = levelGap;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return deleted_flag
     */
    public Long getDeletedFlag() {
        return deletedFlag;
    }

    /**
     * @param deletedFlag
     */
    public void setDeletedFlag(Long deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_on
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return modified_by
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return modified_on
     */
    public Date getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
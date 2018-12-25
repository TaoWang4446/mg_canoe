package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class XsportsEquipmentx implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private Integer equipmentCategoryId;
    private Boolean status;
    private Integer brandId;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
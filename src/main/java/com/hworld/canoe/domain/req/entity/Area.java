package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 省市区
 */
@Data
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String enName;
	private Integer levelGap;
	private Integer parentId;
	private Long deletedFlag;
	private String createdBy;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedOn;
}
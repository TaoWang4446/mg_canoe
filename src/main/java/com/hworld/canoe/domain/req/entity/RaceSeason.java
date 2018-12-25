package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RaceSeason implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
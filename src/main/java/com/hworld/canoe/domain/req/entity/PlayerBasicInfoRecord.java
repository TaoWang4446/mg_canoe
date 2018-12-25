package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PlayerBasicInfoRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Long playerId;
    private String columnName;
    private String oldValue;
    private String newValue;
    private String channelCode;
    private String remark;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private String name;
}
package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 俱乐部
 */
@Data
public class Club implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String clubName;
    private String name;  //所在城市
    private String introduction;
    private String clubImg;
    private String status;
    private Integer areaId;
    private String contactName;
    private String mobile;
    private String mail;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
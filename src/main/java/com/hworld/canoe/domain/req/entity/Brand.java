package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 品牌
 */
@Data
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String brandName;
    private String name; //所在城市
    private String brandImg;
    private String introduction;
    private String status;
    private Integer areaId;
    private String contactName;
    private String mobile;
    private String mail;
    private Integer isSponsor;  //是否是赞助商(0不是1是)
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
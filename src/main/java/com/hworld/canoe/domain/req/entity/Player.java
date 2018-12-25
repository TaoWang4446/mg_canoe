package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String firstName;
    private String lastName;
    private String mobile;
    private String mail;
    private Integer clubId;
    private String clubName;
    private Integer isSeed;
    private String idType;
    private String idNo;
    private Date birthday;
    private String gender;
    private String wechatNo;
    private String wechatOpenid;
    private Integer brandId;
    private Integer country;
    private Integer province;
    private String registerChannel;
    private Date registerTime;
    private String status;
    private BigDecimal currentCompetePoint;
    private BigDecimal currentFinishPoint;
    private BigDecimal totalCompetePoint;
    private BigDecimal totalFinishPoint;
    private String passwordHash;
    private String passwordSalt;
    private String remark;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
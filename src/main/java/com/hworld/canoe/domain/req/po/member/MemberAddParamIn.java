package com.hworld.canoe.domain.req.po.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 新增会员入参
 * @author xichonghang
 */
@Data
public class MemberAddParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String mobile;
	private Integer clubId;
	private String idType;
	private String idNo;
	private String gender;
	private Integer brandId;
	private Integer country;
	private Integer province;
	private String model;
	private Integer type;
	private Integer brand;
	private String remark;
}


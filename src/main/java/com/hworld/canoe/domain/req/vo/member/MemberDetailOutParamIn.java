package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 根据id得到会员详情(数据回显)出参
 * @author xichonghang
 */
@Data
public class MemberDetailOutParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String name;
	private String gender;
	private String mobile;
	private String country;
	private Integer countryId;
	private String countryName;
	private Integer provinceId;
	private String provinceName;
	private String idType;
	private String idNo;
	private Integer clubId;
	private String clubName;
	private Integer zzsId;
	private String zzsName;
	private Integer id;
	private String equipmentName;
	private Integer brandId;
	private String brandName;
	private String remark;
}


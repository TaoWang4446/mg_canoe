package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 旧值出参
 * @author xichonghang
 */
@Data
public class OldValueOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String playerName;
	private String relGender;
	private String gender;
	private String mobile;
	private Integer country;
	private String countryName;
	private String relIdType;
	private String idType;
	private String idNo;
	private Integer clubId;
	private String clubName;
	private Integer brandId;
	private String brandName;
}


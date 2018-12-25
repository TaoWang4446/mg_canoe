package com.hworld.canoe.domain.req.po.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改会员入参
 * @author xichonghang
 */
@Data
public class MemberUpdateParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String mobile;
	private Integer clubId; //所属俱乐部
	private String idType;
	private String idNo;
	private String gender;
	private Integer brandId;  //赞助商
	private Integer country;
	private Integer province;
	private String model; //型号
	private Integer type;
	private Integer brand;
	private String remark;
}


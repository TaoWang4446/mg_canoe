package com.hworld.canoe.domain.req.po.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 批量导入会员报表入参
 * @author xichonghang
 */
@Data
public class Member implements Serializable {

	private static final long serialVersionUID = 1;
	private String name;
	private String gender;
	private String mobile;
	private Integer country;
	private String idType;
    private String idNo;
    private Integer clubId;
    private Integer brandId;
    private String remark;
}


package com.hworld.canoe.domain.req.vo.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 品牌详情出参
 * @author xichonghang
 */
@Data
public class BrandDetailOutParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String brandName;
	private Integer areaId;
	private String name; //城市名称
	private String contactName;
	private String mobile;
	private String mail;
	private String status;
	private String brandImg;
}


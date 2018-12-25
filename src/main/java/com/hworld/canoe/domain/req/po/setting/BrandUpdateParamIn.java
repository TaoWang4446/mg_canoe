package com.hworld.canoe.domain.req.po.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改品牌入参
 * @author xichonghang
 */
@Data
public class BrandUpdateParamIn implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String brandName;
	private String logo;
	private Integer status;
	private Integer areaId;
	private String contactName;
	private String mobile;
	private String mail;
}


package com.hworld.canoe.domain.req.vo.wx.improveInfo;

import java.io.Serializable;

import lombok.Data;

/**
 * 完善信息出参
 * @author xichonghang
 */
@Data
public class ImproveInfoOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String memberName;
	private Integer countryId;
	private String idNo;
	private Integer areaId;
	private String areaName;
	private Integer typeId;
	private Integer brandId;
	private String model;
}


package com.hworld.canoe.domain.req.po.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改俱乐部入参
 * @author xichonghang
 */
@Data
public class ClubUpdateParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String introduction;
	private String logo;
	private Integer areaId;
	private String contactName;
	private String mobile;
	private String mail;
	private Integer status;
}


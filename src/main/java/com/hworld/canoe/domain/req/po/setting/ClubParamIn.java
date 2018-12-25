package com.hworld.canoe.domain.req.po.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 设置模块俱乐部管理入参
 * @author xichonghang
 */
@Data
public class ClubParamIn implements Serializable {

	private static final long serialVersionUID = 1L;
	private String clubName; // 俱乐部名称
	private String contactName; // 联系人
	private String mobile; // 联系电话
	private Integer areaId; // 所在城市
}

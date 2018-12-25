package com.hworld.canoe.domain.req.vo.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 俱乐部详情出参
 * @author xichonghang
 */
@Data
public class ClubDetailOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String clubName;
	private Integer areaId;
	private String name; //所在城市
	private String contactName;
	private String mobile;
	private String mail;
	private String status;
	private String clubImg;
	private String introduction;
}


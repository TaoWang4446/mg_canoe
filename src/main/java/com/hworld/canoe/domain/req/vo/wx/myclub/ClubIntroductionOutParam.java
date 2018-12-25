package com.hworld.canoe.domain.req.vo.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 俱乐部介绍出参
 * @author xichonghang
 */
@Data
public class ClubIntroductionOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer clubId;
	private String clubName;
	private String clubLogo;
	private String clubArea;
	private String introduction;
	private String contactName;
	private String mobile;
	private String mail;
}


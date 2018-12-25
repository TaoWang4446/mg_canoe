package com.hworld.canoe.domain.req.vo.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 俱乐部成员出参
 * @author xichonghang
 */
@Data
public class ClubMemberOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer clubId;
	private String clubName;
	private String clubLogo;
	private String clubArea;
	private Integer rank;
	private String memberName;
	private Double CompetePoint;
	private Double FinishPoint;
}


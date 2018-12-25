package com.hworld.canoe.domain.req.vo.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 我的俱乐部头部
 * @author xichonghang
 */
@Data
public class ClubHeadOutParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer clubId;
	private String clubName;
	private String clubLogo;
	private String clubArea;
}

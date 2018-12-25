package com.hworld.canoe.domain.req.vo.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 原所属俱乐部出参
 * @author xichonghang
 */
@Data
public class OriginalClubOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer playerId; //会员id
	private Integer fromClubId; //原所属俱乐部id
}


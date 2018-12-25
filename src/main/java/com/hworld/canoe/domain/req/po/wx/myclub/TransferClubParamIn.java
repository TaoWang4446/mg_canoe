package com.hworld.canoe.domain.req.po.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 申请转会入参
 * @author xichonghang
 */
@Data
public class TransferClubParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer playerId;
	private Integer fromClubId;
	private Integer targetClubId;
}


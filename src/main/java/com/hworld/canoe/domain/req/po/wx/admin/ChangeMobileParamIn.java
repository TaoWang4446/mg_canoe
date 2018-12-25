package com.hworld.canoe.domain.req.po.wx.admin;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改手机号入参
 * @author xichonghang
 */
@Data
public class ChangeMobileParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String newMobile;
}


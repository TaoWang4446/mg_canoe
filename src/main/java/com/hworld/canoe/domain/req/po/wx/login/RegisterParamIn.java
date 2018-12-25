package com.hworld.canoe.domain.req.po.wx.login;

import java.io.Serializable;

import lombok.Data;

/**
 * 微信注册入参
 * @author xichonghang
 */
@Data
public class RegisterParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name; //姓名
	private String pwd; //登录密码
	private Integer clubId; //所属俱乐部
	private String idNo; //身份证号
}


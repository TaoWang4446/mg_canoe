package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 省份出参
 * @author xichonghang
 */
@Data
public class ProvinceOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private String pname;
}


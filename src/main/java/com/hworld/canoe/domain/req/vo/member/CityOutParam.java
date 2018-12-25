package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 城市出参
 * @author xichonghang
 */
@Data
public class CityOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String cname;
}


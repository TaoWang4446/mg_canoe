package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 惯用器材品牌出参
 * @author xichonghang
 */
@Data
public class BrandOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String brandName;
	private Integer id;
}


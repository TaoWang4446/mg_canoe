package com.hworld.canoe.domain.req.vo.score;

import java.io.Serializable;

import lombok.Data;

/**
 * 大组名称出参
 * @author xichonghang
 */
@Data
public class GroupNameOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String groupName;
}


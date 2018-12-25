package com.hworld.canoe.domain.req.po.score;

import java.io.Serializable;

import lombok.Data;

/**
 * 批量导入成绩入参
 * @author xichonghang
 */
@Data
public class Score implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer groupId;
	private Integer playerId;
	private String finishTime;
	private String equipmentName;
	private Integer equipmentCategoryId;
	private String brand;
}


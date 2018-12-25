package com.hworld.canoe.domain.req.vo.wx.racehistory;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 参赛历史出参
 * @author xichonghang
 */
@Data
public class RaceHistoryOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer raceId;
	private String raceName;
	private String areaName;
	private String address;
	private String raceDate;
	private String groupName;
	private String finishTime;
	private BigDecimal competitionPoint;
	private BigDecimal finishPoint;
}


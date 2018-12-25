package com.hworld.canoe.domain.req.po.match;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改赛事入参
 * @author xichonghang
 */
@Data
public class RaceUpdateParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer raceId;
	private String raceName;
	private String raceDate;
	private Integer areaId;
	private String address;
	private String organiser;
	private Integer raceSeasonId;
}


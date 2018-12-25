package com.hworld.canoe.domain.req.vo.match;

import java.io.Serializable;

import lombok.Data;

/**
 * 赛事详情出参
 * @author xichonghang
 */
@Data
public class RaceDetailOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String raceDate;
	private Integer areaId;
	private String cityName;
	private String address;
	private Integer seasonId;
	private String seasonName;
	private String organiser;
}


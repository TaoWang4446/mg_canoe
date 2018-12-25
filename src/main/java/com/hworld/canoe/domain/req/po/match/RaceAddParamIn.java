package com.hworld.canoe.domain.req.po.match;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 新增赛事入参
 * @author xichonghang
 */
@Data
public class RaceAddParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String raceName;
	private String raceDate;  //页面值
	private Date tempDate; //往数据库插入值
	private Integer areaId;
	private String address;
	private String organiser;
	private Integer raceSeasonId;
	private Integer raceId;
	private String groupName;
	private Double distince;
	private Double wave;
	private Double dod;
	private String gender;
	private Integer raceNum;
}


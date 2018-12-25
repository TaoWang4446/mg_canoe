package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Race implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String cityName; //举办城市名称
    private String raceDate;
    private String raceEndDate;
    private Integer areaId;
    private String address;
    private String organiser;
    private Integer raceSeasonId;
    private String status;
    private Long deletedFlag;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;
}
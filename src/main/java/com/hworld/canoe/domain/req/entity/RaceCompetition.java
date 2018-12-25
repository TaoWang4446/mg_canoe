package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class RaceCompetition implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer raceGroupId;
    private Integer playerId;
    private String finshTime;
    private String equipmentName;
    private Integer equipmentCategoryId;
    private String brand;
    private String racePlayerNo;
    private Integer status;
    private Integer rank;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private String playerName;
    private String clubName;
    private String groupName;
    private BigDecimal totalCompetePoint;
    private BigDecimal totalFinishPoint;
    private String typeName;
    private String brandName;
}
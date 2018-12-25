package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PlayerPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer playerId;
    private Integer seasonId;
    private Integer raceId;
    private Integer raceGroupId;
    private Integer raceCompetitionId;
    private Date acquireDate;
    private BigDecimal finishPoint;
    private BigDecimal competitionPoint;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
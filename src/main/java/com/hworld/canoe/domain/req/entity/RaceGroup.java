package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class RaceGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer raceId;
    private Date raceDate;
    private String groupName;
    private BigDecimal distince;
    private BigDecimal wave;
    private BigDecimal dod;
    private Integer star;
    private String gender;
    private Integer raceNum;
    private Boolean givenCompetitionPoint;
    private Boolean givenFinshPoint;
    private Boolean publishCompetition;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
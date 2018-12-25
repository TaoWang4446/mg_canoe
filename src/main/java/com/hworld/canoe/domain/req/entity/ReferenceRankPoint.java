package com.hworld.canoe.domain.req.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ReferenceRankPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer rank;
    private BigDecimal basicPoint;
    private Long deletedFlag;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
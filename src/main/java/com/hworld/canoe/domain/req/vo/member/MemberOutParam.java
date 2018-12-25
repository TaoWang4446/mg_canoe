package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 会员模块首页出参
 * @author xichonghang
 */
@Data
public class MemberOutParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String mobile;
	private String gender;
	private Integer clubId;
	private BigDecimal totalCompetePoint; // 累积竞争积分
	private BigDecimal totalFinishPoint; // 累积完赛积分
	private String status;
	private Boolean isSeed;
}

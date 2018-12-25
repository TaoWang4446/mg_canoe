package com.hworld.canoe.domain.req.po.score;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改成绩入参
 * @author xichonghang
 */
@Data
public class ScoreUpdateParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer scoreId; //成绩id
	private Integer playerId; //会员id
	private String name; //会员姓名
	private String mobile; //手机号
	private Integer isSeed; //是否黑榜选手
	private Integer clubId; //所属俱乐部id
	private Integer raceGroupId; //大组id
	private String finshTime; //完赛时间
 	private Integer equipmentCategoryId; //器材类型id
	private String brand; //品牌
	private String equipmentName; //型号
}


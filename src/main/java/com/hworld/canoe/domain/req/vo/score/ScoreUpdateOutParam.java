package com.hworld.canoe.domain.req.vo.score;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改成绩出参
 * @author xichonghang
 */
@Data
public class ScoreUpdateOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer scoreId; //成绩id
	private Integer memberId; //会员id
	private String memberName; //会员姓名
	private String mobile; //手机号
	private String isSeed; //是否是黑榜选手
	private Integer clubId; //所属俱乐部id
	private String clubName; //所属俱乐部
	private String finishTime; //完赛时间
	private Integer typeId; //惯用器材类型id
	private String typeName; //惯用器材类型
	private Integer brandId; //惯用器材品牌id
	private String brandName; //惯用器材品牌
	private Integer modelId; //惯用器材型号id
	private String modelName; //惯用器材
	private Integer competitionId; //成绩id
	private Integer raceGroupId; //所属大组id
	private String groupName; //大组名称
}


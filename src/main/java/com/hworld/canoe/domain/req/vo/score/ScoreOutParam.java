package com.hworld.canoe.domain.req.vo.score;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 积分计算出参
 * @author xichonghang
 */
@Data
public class ScoreOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer pointId;  //积分id
	private Integer playerId; //会员id
	private String playerName; //会员名称
	private String wechatOpenId; //微信ID
	private String clubName; //所属俱乐部
	private String groupName; //大组名称
	private String finishTime; //完赛时间
	private Integer rank; //排名
	private Integer totalRank; //最终排名
	private BigDecimal competitionPoint; //竞争积分
	private BigDecimal finishPoint; //完赛积分
	private String typeName; //器材类型
	private String brandName; //器材品牌
	private String model; //器材型号
}


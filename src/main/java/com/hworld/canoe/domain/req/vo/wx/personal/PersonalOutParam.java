package com.hworld.canoe.domain.req.vo.wx.personal;

import java.io.Serializable;

import lombok.Data;

/**
 * 个人中心首页出参
 * @author xichonghang
 */
@Data
public class PersonalOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer playerId; //会员id
	private String playerName; //会员名称
	private String zzsName; //赞助商名称
	private Double competePoint; //竞争积分
	private Double finishPoint; //完赛积分
	private String isSeed; //是否黑榜选手
	private Integer rank; //当前排名
	private String logo; //俱乐部logo
	private String clubName; //俱乐部名称
	private Double clubPoint; //俱乐部积分
	private Integer clubRank; //俱乐部排名
	private String raceDate; //参赛时间
	private String raceName; //比赛名称
	private String areaName; //地点
	private String groupName; //大组名称
	private String finishTime; //完赛时间
	private Double givenCompetitionPoint; //组竞争积分
	private Double givenFinshPoint; //组完赛积分
}


package com.hworld.canoe.domain.req.vo.wx.myclub;

import java.io.Serializable;

import lombok.Data;

/**
 * 俱乐部赛事出参
 * @author xichonghang
 */
@Data
public class ClubRaceOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String raceDate;
	private String raceName;
	private String address;
	private String areaName; //办赛省市
	private Double competePoint; //竞争积分
	private Double finishPoint; //完赛积分
}


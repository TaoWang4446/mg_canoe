package com.hworld.canoe.dao.wx;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.vo.wx.myclub.ClubHeadOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubIntroductionOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubMemberOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubRaceOutParam;

/**
 * 我的俱乐部Mapper
 * @author xichonghang
 */
@Mapper
public interface MyClubMapper {
	
	/**
	 * 俱乐部head
	 */
	ClubHeadOutParam findClubHead(@Param("clubId") Integer clubId);
	
	/**
	 * 查询俱乐部介绍
	 */
	ClubIntroductionOutParam findClubIntroduction(@Param("clubId") Integer clubId);
	
	/**
	 * 查询俱乐部成员
	 */
	ClubMemberOutParam findClubMember(@Param("clubId") Integer clubId);
	
	/**
	 * 查询赛事
	 */
	ClubRaceOutParam findClubRace(@Param("clubId") Integer clubId);
	
	/**
	 * 查询所有俱乐部
	 */
	List<ClubOutParam> findAllClub();
	
	/**
	 * 申请转会
	 */
	void transferClub(@Param("map") Map<String, Object> map);
}


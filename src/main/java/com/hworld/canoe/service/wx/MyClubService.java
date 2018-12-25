package com.hworld.canoe.service.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.wx.MyClubMapper;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubHeadOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubIntroductionOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubMemberOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubOutParam;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubRaceOutParam;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 我的俱乐部Service
 * @author xichonghang
 */
@Service
public class MyClubService {
	
	@Autowired
	private MyClubMapper mapper;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 俱乐部head
	 */
	public ClubHeadOutParam findClubHead() {
		Integer clubId = (Integer) request.getSession().getAttribute("userInfo.club_id");
		return mapper.findClubHead(clubId);
	}
	
	/**
	 * 查询俱乐部介绍
	 */
	public ClubIntroductionOutParam findClubIntroduction() {
		Integer clubId = (Integer) request.getSession().getAttribute("userInfo.club_id");
		return mapper.findClubIntroduction(clubId);
	}
	
	/**
	 * 查询俱乐部成员
	 */
	public ClubMemberOutParam findClubMember() {
		Integer clubId = (Integer) request.getSession().getAttribute("userInfo.club_id");
		return mapper.findClubMember(clubId);
	}
	
	/**
	 * 查询赛事
	 */
	public ClubRaceOutParam findClubRace() {
		Integer clubId = (Integer) request.getSession().getAttribute("userInfo.club_id");
		return mapper.findClubRace(clubId);
	}
	
	/**
	 * 查询所有俱乐部
	 */
	public List<ClubOutParam> findAllClub(){
		return mapper.findAllClub();
	}
	
	/**
	 * 申请转会
	 */
	public ObjectRestResponse transferClub(Integer targetClubId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("playerId", request.getSession().getAttribute("userInfo.id"));
		map.put("fromClubId", request.getSession().getAttribute("userInfo.club_id"));
		map.put("targetClubId", targetClubId);
		
		mapper.transferClub(map);
		return new ObjectRestResponse();
	}
}


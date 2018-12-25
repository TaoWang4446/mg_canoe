package com.hworld.canoe.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.service.wx.MyClubService;

/**
 * 我的俱乐部Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/myclub")
public class MyClubController {
	
	@Autowired
	private MyClubService service;
	
	private static String MYCLUB_INDEX = "/wxpage/myClub.html";
	private static String MYCLUB_CHANGE = "/wxpage/changeClub.html";
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("headInfo", service.findClubHead());  //头部信息
		model.addAttribute("introduceInfo", service.findClubIntroduction()); //俱乐部介绍
		model.addAttribute("raceInfo", service.findClubRace()); //赛事信息
		model.addAttribute("memberInfo", service.findClubMember()); //俱乐部成员
		return MYCLUB_INDEX;
	}
	@RequestMapping("/change")
	public String change(Model model) {
		model.addAttribute("clubList", service.findAllClub());  //目标俱乐部列表
		return MYCLUB_CHANGE;
	}
	
	/**
	 * 申请转会
	 */
	@RequestMapping("/transferClub")
	public String transferClub(Integer targetClubId) {
		ObjectRestResponse response = service.transferClub(targetClubId);
		if (response.getStatus() == 200) {
			return MYCLUB_INDEX;
		}else {
			return "fail";
		}
	}
}


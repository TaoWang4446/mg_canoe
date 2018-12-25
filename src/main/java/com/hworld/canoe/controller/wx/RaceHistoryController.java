package com.hworld.canoe.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.service.wx.RaceHistoryService;

/**
 * 参赛历史Controller
 * @author xichonghang
 */
@Controller
public class RaceHistoryController {

	@Autowired
	private RaceHistoryService service;
	
	private static String RACE_HISTORY = "/wxpage/matchHistory.html";
	
	@RequestMapping("/raceHistoryIndex")
	public String raceHistoryIndex() {
		return RACE_HISTORY;
	}
	
	/**
	 * 查询当前登录者的参赛历史
	 */
	@RequestMapping("/findRaceHistory")
	@ResponseBody
	public ObjectRestResponse findRaceHistory() {
		return service.findRaceHistory();
	}
}


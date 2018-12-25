package com.hworld.canoe.service.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.wx.RaceHistoryMapper;
import com.hworld.canoe.domain.req.vo.wx.racehistory.RaceHistoryOutParam;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 参赛历史Service
 * @author xichonghang
 */
@Service
public class RaceHistoryService {
	
	@Autowired
	private RaceHistoryMapper mapper;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 查询当前登录者的参赛历史
	 */
	public ObjectRestResponse findRaceHistory(){
		Integer id = (Integer) request.getSession().getAttribute("userInfo.id");
		List<RaceHistoryOutParam> list = mapper.findRaceHistory(id);
		return new ObjectRestResponse(list);
	}
}


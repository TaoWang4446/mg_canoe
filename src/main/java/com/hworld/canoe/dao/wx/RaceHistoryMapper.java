package com.hworld.canoe.dao.wx;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.vo.wx.racehistory.RaceHistoryOutParam;

/**
 * 参赛历史Mapper
 * @author xichonghang
 */
@Mapper
public interface RaceHistoryMapper {
	
	/**
	 * 查询当前登录者的参赛历史
	 */
	List<RaceHistoryOutParam> findRaceHistory(@Param("id") Integer id);
}


package com.hworld.canoe.dao.wx;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.vo.wx.personal.PersonalOutParam;

/**
 * 微信端个人中心Mapper
 * @author xichonghang
 */
@Mapper
public interface PersonalMapper {
	
	/**
	 * 个人中心首页
	 */
	PersonalOutParam findPersonalIndex(@Param("id") Integer id);

}


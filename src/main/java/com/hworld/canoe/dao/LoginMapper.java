package com.hworld.canoe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 登录Mapper
 * @author xichonghang
 */
@Mapper
public interface LoginMapper {
	
	/**
	 * 登录
	 */
	Integer login(@Param("mobile") String mobile, @Param("password") String password);
}


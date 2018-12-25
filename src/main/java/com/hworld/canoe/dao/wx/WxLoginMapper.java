package com.hworld.canoe.dao.wx;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.po.wx.login.RegisterParamIn;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubOutParam;

/**
 * 登录Mapper
 * @author xichonghang
 */
@Mapper
public interface WxLoginMapper {
	
	/**
	 * 登录
	 */
	Object login(@Param("mobile") String mobile, @Param("password") String password);
	
	/**
	 * 查询所有俱乐部
	 */
	List<ClubOutParam> findAllClub();
	
	/**
	 * 注册
	 */
	void register(@Param("param") RegisterParamIn paramIn);
	
	/**
	 * 修改密码
	 */
	void updatePwd(@Param("id") Integer id, @Param("newPwd") String newPwd);

}


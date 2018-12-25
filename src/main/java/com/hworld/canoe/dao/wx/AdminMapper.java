package com.hworld.canoe.dao.wx;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.po.wx.admin.ChangeMobileParamIn;
import com.hworld.canoe.domain.req.po.wx.admin.ChangePwdParamIn;

/**
 * 账户Mapper
 * @author xichonghang
 */
@Mapper
public interface AdminMapper {
	
	/**
	 * 更换手机号
	 */
	void updateMobile(@Param("param") ChangeMobileParamIn paramIn);
	
	/**
	 * 根据会员id查询原始密码
	 */
	String findOldPwdById(@Param("memberId") Integer memberId);
	
	/**
	 * 修改密码
	 */
	void updatePassword(@Param("param") ChangePwdParamIn paramIn);

}


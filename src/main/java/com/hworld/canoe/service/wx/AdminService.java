package com.hworld.canoe.service.wx;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.wx.AdminMapper;
import com.hworld.canoe.domain.req.po.wx.admin.ChangeMobileParamIn;
import com.hworld.canoe.domain.req.po.wx.admin.ChangePwdParamIn;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 账户Service
 * @author xichonghang
 */
@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;
	
	/**
	 * 更换手机号
	 */
	public ObjectRestResponse updateMobile(ChangeMobileParamIn paramIn) {
		mapper.updateMobile(paramIn);
		return new ObjectRestResponse(200, "success");
	}
	
	/**
	 * 修改密码
	 */
	public ObjectRestResponse updatePassword(ChangePwdParamIn paramIn) {
		//根据会员id查询数据库原始密码
		String oldPwd = mapper.findOldPwdById(paramIn.getMemberId());
		Md5Hash md5Hash = new Md5Hash(paramIn.getOldPassword());
		if (! oldPwd.equals(md5Hash.toString())) {
			return new ObjectRestResponse(500, "原始密码不匹配");
		}else {
			mapper.updatePassword(paramIn);
			return new ObjectRestResponse();
		}
	}
}


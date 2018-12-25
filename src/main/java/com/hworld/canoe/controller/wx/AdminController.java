package com.hworld.canoe.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hworld.canoe.domain.req.po.wx.admin.ChangeMobileParamIn;
import com.hworld.canoe.domain.req.po.wx.admin.ChangePwdParamIn;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.service.wx.AdminService;

/**
 * 账户Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	/**
	 * 更换手机号
	 */
	@RequestMapping("/updateMobile")
	public String updateMobile(ChangeMobileParamIn paramIn) {
		ObjectRestResponse response = service.updateMobile(paramIn);
		if (response.getStatus() == 200) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(ChangePwdParamIn paramIn) {
		ObjectRestResponse response = service.updatePassword(paramIn);
		if (response.getStatus() == 200) {
			return "success";
		}else {
			return "fail";
		}
	}
	

}


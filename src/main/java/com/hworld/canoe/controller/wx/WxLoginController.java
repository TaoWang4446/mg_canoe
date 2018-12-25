package com.hworld.canoe.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.domain.req.po.wx.login.RegisterParamIn;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.service.wx.WxLoginService;

/**
 * Wx登录Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/wx")
public class WxLoginController {
	
	@Autowired
	private WxLoginService service;
	
	/**
	 * 集中定义跳转页面名称
	 */
	private static String LOGIN_INDEX = "/wxpage/wx_login.html";
	private static String FORGET_PWD = "/wxpage/forget.html";
	private static String PWD_RESET = "/wxpage/reset.html";
	
	/**
	 * 页面跳转
	 */
	@RequestMapping("/loginIndex")
	public String loginIndex(Model model) {
		model.addAttribute("clubList", service.findAllClub()); //俱乐部列表
		return LOGIN_INDEX;
	}
	@RequestMapping("/forgetPwdIndex")
	public String forgetPwdIndex() {
		return FORGET_PWD;
	}
	@RequestMapping("/resetPwdIndex")
	public String resetPwdIndex() {
		return PWD_RESET;
	}
	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ObjectRestResponse login(String mobile, String password) {
		return service.login(mobile, password);
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("/register")
	public ObjectRestResponse register(RegisterParamIn paramIn) {
		return service.register(paramIn);
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(String password) {
		service.updatePwd(password);
		return "redirect:/wx/loginIndex";
	}
}


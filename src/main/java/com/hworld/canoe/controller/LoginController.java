package com.hworld.canoe.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.utils.VerifyCodeUtils;
import com.hworld.canoe.service.LoginService;

/**
 * 登录Controller
 * @author xichonghang
 */
@Controller 
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	// 集中定义跳转页面名称
	private static String LOGIN_INDEX = "/page/Login.html";
	private static String INDEX = "/page/index.html";
	
	/**
	 * 跳转登录首页并显示验证码
	 */
	@RequestMapping("/")
	public String index() {
	    return LOGIN_INDEX;
	}
	/**
	 * 登录成功后跳转首页
	 */
	@RequestMapping("/pageIndex")
	public String pageIndex() {
	    return INDEX;
	}
	
	/**
	 * 获取图形码
	 */
	@RequestMapping("/getVerifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
	    String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
	    try {
	        HttpSession session = request.getSession(true);
	        session.setAttribute("verifyCode", verifyCode);  //向作用域中存放真实验证码
	        VerifyCodeUtils.outputImage(100, 40, response.getOutputStream(), verifyCode);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/logout")
	@ResponseBody
	public ObjectRestResponse logout() {
	    return new ObjectRestResponse("d");
	}
	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ObjectRestResponse login(String mobile, String password, String code) {
		return service.login(mobile, password, code);
	}
}


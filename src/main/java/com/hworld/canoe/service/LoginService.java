package com.hworld.canoe.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.LoginMapper;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 登录Service
 * @author xichonghang
 */
@Service
public class LoginService {
	
	@Autowired
	private LoginMapper mapper;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 登录
	 */
	public ObjectRestResponse login(String mobile, String password, String code) {
		HttpSession session = request.getSession();
		String relCode = (String) session.getAttribute("verifyCode");
		if (! relCode.equals(code)) {
			return new ObjectRestResponse(500, "验证码错误");
		}else {
			Md5Hash pwd = new Md5Hash(password);  //得到加密后的密码
			Integer num = mapper.login(mobile, pwd.toString());
			if (num > 0) {
				return new ObjectRestResponse(200, "success");
			}else {
				return new ObjectRestResponse(500, "fail");
			}
		}
	}
}


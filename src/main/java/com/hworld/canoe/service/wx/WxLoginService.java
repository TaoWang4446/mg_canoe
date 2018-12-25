package com.hworld.canoe.service.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.wx.WxLoginMapper;
import com.hworld.canoe.domain.req.po.wx.login.RegisterParamIn;
import com.hworld.canoe.domain.req.vo.wx.myclub.ClubOutParam;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 登录Service
 * @author xichonghang
 */
@Service
public class WxLoginService {
	
	@Autowired
	private WxLoginMapper mapper;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 登录
	 */
	public ObjectRestResponse login(String mobile, String password) {
		Md5Hash md5Hash = new Md5Hash(password);
		Object result = mapper.login(mobile, md5Hash.toString());
		if (result != null) {
			request.getSession().setAttribute("userInfo", result);
			return new ObjectRestResponse(200, "success");
		}else {
			return new ObjectRestResponse(500, "fail");
		}
	}
	
	/**
	 * 查询所有俱乐部
	 */
	public List<ClubOutParam> findAllClub(){
		return mapper.findAllClub();
	}
	
	/**
	 * 注册
	 */
	public ObjectRestResponse register(RegisterParamIn paramIn) {
		mapper.register(paramIn);
		return new ObjectRestResponse();
	}
	
	/**
	 * 修改密码
	 */
	public ObjectRestResponse updatePwd(String password) {
		Md5Hash md5Hash = new Md5Hash(password);
		Integer id = (Integer) request.getSession().getAttribute("userInfo.id");
		mapper.updatePwd(id, md5Hash.toString());
		return new ObjectRestResponse();
	}

}


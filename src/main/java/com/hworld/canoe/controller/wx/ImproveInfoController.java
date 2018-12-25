package com.hworld.canoe.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.wx.improveInfo.ImproveInfoOutParam;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.service.wx.ImproveInfoService;

/**
 * 完善信息Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/improve")
public class ImproveInfoController {
	
	@Autowired
	private ImproveInfoService service;
	
	/**
	 * 根据会员id查询会员信息
	 */
	@RequestMapping("/findMemberInfoById")
	public String findMemberInfoById(Model model) {
		ImproveInfoOutParam info = service.findMemberInfoById();
		model.addAttribute("info", info); //完善信息
		model.addAttribute("countryList", service.findCountry()); //国籍列表
		model.addAttribute("provinceList", service.findAllProvince()); //省份列表
		return "temp";
	}
	
	/**
	 * 根据省份id查询城市信息
	 */
	@RequestMapping("/city/{pid}")
	@ResponseBody
	public ObjectRestResponse findAllCityByPid(@PathVariable("pid") Integer pid){
		List<CityOutParam> list = service.findAllCityByPid(pid);
		return new ObjectRestResponse(list);
	}
	
	/**
	 * 根据类型id查询品牌信息
	 */
	@RequestMapping("/brand/{tid}")
	@ResponseBody
	public ObjectRestResponse findBrandInfoByTid(@PathVariable("tid") Integer tid){
		List<BrandOutParam> list = service.findBrandInfoByTid(tid);
		return new ObjectRestResponse(list);
	}
	
	/**
	 * 完善信息
	 */
	@RequestMapping("/improveInfo")
	public String improveInfo(ImproveInfoOutParam param) {
		ObjectRestResponse response = service.improveInfo(param);
		if (response.getStatus() == 200) {
			return "temp";
		}else {
			return "fail";
		}
	}
}


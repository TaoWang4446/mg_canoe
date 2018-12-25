package com.hworld.canoe.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.domain.req.po.match.RaceAddParamIn;
import com.hworld.canoe.domain.req.po.match.RaceUpdateParamIn;
import com.hworld.canoe.domain.req.vo.match.RaceDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.service.MatchService;

/**
 * 赛事管理Controller
 * @author xichonghang
 */
@Controller 
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
	private MatchService service; 
	
	/**
	 * 集中定义跳转页面名称
	 */
	private static String RACE_INDEX = "/page/match/match.html";
	private static String RACE_ADD = "/page/match/addMatch.html";
	private static String RACE_UPDATE = "/page/match/updateMatch.html";
	
	/**
	 * 进行页面跳转
	 */
	@RequestMapping("/page")
	public String page(Model model) {
		return RACE_INDEX;
	}
	@RequestMapping("/pageAdd")
	public String pageAdd(Model model) {
		model.addAttribute("raceSeasonList", service.findAllRaceSeason()); //赛季列表
		model.addAttribute("equipmentypeList", service.findAllEquipmentType());  //器材类型列表
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		return RACE_ADD;
	}
	@RequestMapping("/pageUpdate")
	public String pageUpdate(Model model) {
		return RACE_UPDATE;
	}
	
	/**
	 * 赛事列表(多条件)
	 */
	@RequestMapping("/list/condition")
	@ResponseBody
	public TableResponse<?> findAllRaceByCondition(TableRequest tableRequest){
		return service.findAllRaceByCondition(tableRequest);
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
	 * 新增赛事
	 */
	@RequestMapping("/addRace")
	public String addRace(RaceAddParamIn paramIn, BindingResult bindingResult) throws ParseException {
		service.addRace(paramIn);
		return "redirect:/match/page";
	}
	
	/**
	 * 通过赛事id查询赛事详细信息
	 */
	@RequestMapping("/findRaceDetailById/{id}")
	public String findRaceDetailById(@PathVariable("id") Integer id, Model model) {
		RaceDetailOutParam info = service.findRaceDetailById(id);
		model.addAttribute("raceInfo", info);  //赛事信息
		model.addAttribute("raceSeasonList", service.findAllRaceSeason()); //赛季列表
		model.addAttribute("equipmentypeList", service.findAllEquipmentType());  //器材类型列表
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		return RACE_UPDATE;
	}
	
	/**
	 * 修改赛事
	 */
	@RequestMapping("/updateRace")
	public String updateRace(RaceUpdateParamIn paramIn, BindingResult bindingResult) {
		service.updateRace(paramIn);
		return "redirect:/match/page";
	}
	
	/**
	 * 删除赛事
	 */
	@RequestMapping("/deleteRace/{id}")
	@ResponseBody
	public ObjectRestResponse deleteRace(@PathVariable("id") Integer id) {
		return service.deleteRace(id);
	}
	
	/**
	 * 成绩发布
	 */
	@RequestMapping("/publishScore/{id}")
	@ResponseBody
	public ObjectRestResponse publishScore(@PathVariable("id") Integer id) {
		ObjectRestResponse response = service.publishScore(id);
		if (response.getStatus() == 200) {
			return new ObjectRestResponse(200, "发布成功");
		}else {
			return new ObjectRestResponse(500, "发布失败");
		}
	}
}


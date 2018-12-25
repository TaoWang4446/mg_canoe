package com.hworld.canoe.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hworld.canoe.domain.req.entity.RaceCompetition;
import com.hworld.canoe.domain.req.po.score.ScoreUpdateParamIn;
import com.hworld.canoe.domain.req.vo.score.ScoreUpdateOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.BaseResponse;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.service.ScoreService;

/**
 * 成绩管理Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreService service;
	@Autowired
	private ServletContext cxt;
	
	/**
	 * 集中定义跳转页面名称   
	 */
	private static String SCORE_INDEX = "/page/score/score.html";
	private static String SCORE_UPDATE = "/page/score/scoreInput.html";
	private static String SCORE_COUNT = "/page/score/scoreCount.html";
	
	//跳转成绩管理
	@RequestMapping("/page")
	public String page(Model model){
		model.addAttribute("clubList", service.findAllClubByOption());  //俱乐部列表
		model.addAttribute("groupList", service.findAllGroup());  //大组列表
		return SCORE_INDEX;
	}
	//跳转修改成绩页面
	@RequestMapping("/updatePage")
	public String updateScore() {
		return SCORE_UPDATE;
	}
	//跳转积分计算页面
	@RequestMapping("/scoreCount")
	public String scoreCount(Model model) {
		model.addAttribute("clubList", service.findAllClubByOption());  //俱乐部列表
		model.addAttribute("groupList", service.findAllGroup());  //大组列表
		return SCORE_COUNT;
	}
	
	/**
	 * 成绩列表(多条件分页)
	 */
	@RequestMapping("/list/condition")
	@ResponseBody
	public TableResponse<RaceCompetition> findAllScore(TableRequest tableRequest){
		return service.findAllScore(tableRequest);
	}
	
	/**
	 * 模板下载
	 */
	@RequestMapping("/download")
	@ResponseBody
	public BaseResponse download(HttpServletResponse response) throws Exception {
		service.download(response);
		return new BaseResponse(200, "success");
	}
	
	/**
	 * 根据成绩id查询成绩
	 */
	@RequestMapping("/info/{id}")
	public String findById(Model model, @PathVariable("id") Integer id) {
		ScoreUpdateOutParam info = service.findById(id);
		model.addAttribute("info", info); //详情
		model.addAttribute("clubList", service.findAllClubByOption());  //俱乐部列表
		model.addAttribute("groupList", service.findAllGroup());  //组列表
		model.addAttribute("typeList", service.findAllEquipmentType());  //器材类型列表
		return SCORE_UPDATE;
	}
	
	/**
	 * 修改成绩
	 */
	@RequestMapping("/updateScore")
	public String updateScore(ScoreUpdateParamIn paramIn) {
		service.updateScore(paramIn);
		return "redirect:/score/page";
	}
	
	/**
	 * 删除成绩
	 */
	@RequestMapping("/deleteScore/{id}")
	@ResponseBody
	public ObjectRestResponse deleteScore(@PathVariable("id") Integer id) {
		service.deleteScore(id);
		return new ObjectRestResponse();
	}
	
	/**
	 * 清空成绩
	 */
	@RequestMapping("/clearAllScore")
	@ResponseBody
	public ObjectRestResponse clearAllScore() {
		service.clearAllScore();
		return new ObjectRestResponse();
	}
	
	/**
	 * 成绩导入
	 */
	@RequestMapping("/scoreImport")
	@ResponseBody
	public ObjectRestResponse scoreImport(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = cxt.getRealPath("/");
		String filePath = "upload/" + file.getOriginalFilename();
		path = path + "/upload";
		File newFile = new File(path, file.getOriginalFilename());
		file.getFileItem().write(newFile);
		
        service.addScore(file, request, response);
        return new ObjectRestResponse(200, "success");
	}
	
	/**
	 * 积分计算
	 */
	@RequestMapping("/count")
	@ResponseBody
	public TableResponse<?> findAllPoint(TableRequest tableRequest){
		return service.findAllPoint(tableRequest);
	}
	
	/**
	 * 删除积分
	 */
	@RequestMapping("/deletePoint/{pointId}")
	@ResponseBody
	public ObjectRestResponse deletePoint(@PathVariable("pointId") Integer pointId) {
		service.deletePoint(pointId);
		return new ObjectRestResponse();
	}
}


package com.hworld.canoe.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hworld.canoe.domain.req.entity.Brand;
import com.hworld.canoe.domain.req.entity.Club;
import com.hworld.canoe.domain.req.entity.EquipmentType;
import com.hworld.canoe.domain.req.po.setting.BrandAddParamIn;
import com.hworld.canoe.domain.req.po.setting.BrandUpdateParamIn;
import com.hworld.canoe.domain.req.po.setting.ClubAddParamIn;
import com.hworld.canoe.domain.req.po.setting.ClubUpdateParamIn;
import com.hworld.canoe.domain.req.po.setting.EquipmentAddParamIn;
import com.hworld.canoe.domain.req.po.setting.EquipmentUpdateParamIn;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.service.SettingService;

/**
 * 设置模块Controller
 * @author xichonghang
 */
@Controller
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private SettingService service;
	@Autowired
	private ServletContext cxt;
	
	/**
	 * 集中定义跳转页面名称   
	 */
	private static String SETTING_CLUB_INDEX = "/page/setting/club.html";
	private static String SETTING_CLUB_ADD = "/page/setting/addClub.html";
	private static String SETTING_CLUB_UPDATE = "/page/setting/updateClub.html";
	
	private static String SETTING_BRAND_INDEX = "/page/setting/brand.html";
	private static String SETTING_BRAND_ADD = "/page/setting/addBrand.html";
	private static String SETTING_BRAND_UPDATE = "/page/setting/updateBrand.html";
	
	private static String SETTING_EQUIPMENT_INDEX = "/page/setting/equipment.html";
	private static String SETTING_EQUIPMENT_ADD = "/page/setting/addEquipment.html";
	private static String SETTING_EQUIPMENT_UPDATE = "/page/setting/updateEquipment.html";
	
	/**
	 * 有关俱乐部
	 */
	@RequestMapping("/club/index")
	public String clubIndex(Model model) {
		return SETTING_CLUB_INDEX;
	}
	@RequestMapping("/club/add")
	public String clubAdd(Model model) {
		model.addAttribute("provinceList", service.findAllProvince());
		return SETTING_CLUB_ADD;
	}
	@RequestMapping("/club/update")
	public String clubUpdate() {
		return SETTING_CLUB_UPDATE;
	}
	
	/**
	 * 有关品牌 
	 */
	@RequestMapping("/brand/index")
	public String brandIndex() {
		return SETTING_BRAND_INDEX;
	}
	@RequestMapping("/brand/add")
	public String brandAdd(Model model) {
		model.addAttribute("provinceList", service.findAllProvince());
		return SETTING_BRAND_ADD;
	}
	@RequestMapping("/brand/update")
	public String brandUpdate() {
		return SETTING_BRAND_UPDATE;
	}
	
	/**
	 * 有关惯用器材
	 */
	@RequestMapping("/equipment/index")
	public String equipmentIndex() {
		return SETTING_EQUIPMENT_INDEX;
	}
	@RequestMapping("/equipment/add")
	public String equipmentAdd() {
		return SETTING_EQUIPMENT_ADD;
	}
	@RequestMapping("/equipment/update")
	public String equipmentUpdate() {
		return SETTING_EQUIPMENT_UPDATE;
	}
	
	/**
	 * 俱乐部列表(多条件)
	 */
	@RequestMapping("/club/condition")
	@ResponseBody
	public TableResponse<Club> findAllClubByCondition(TableRequest tableRequest) {
		return service.findAllClubByCondition(tableRequest);
	}
	
	/**
	 * 上传文件公用方法
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public ObjectRestResponse upload(@RequestParam("file") CommonsMultipartFile file) throws Exception {
		String path = cxt.getRealPath("/");
		String filePath = "upload/" + file.getOriginalFilename();
		path = path + "/upload";
		File newFile = new File(path, file.getOriginalFilename());
		file.getFileItem().write(newFile);
		return new ObjectRestResponse(filePath);
	}
	
	/**
	 * 新增俱乐部
	 */
	@RequestMapping("/addClub")
	public String addClud(ClubAddParamIn paramIn) throws Exception{
		service.addClud(paramIn);
		return "redirect:/setting/club/index";
	}
	
	/**
	 * 根据俱乐部id查询详情
	 */
	@RequestMapping("/club/detail/{id}")
	public String findClubById(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("clubInfo", service.findClubById(id)); //俱乐部信息
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		return SETTING_CLUB_UPDATE;
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
	 * 修改俱乐部
	 */
	@RequestMapping("/updateClub")
	public String updateClub(ClubUpdateParamIn paramIn) throws Exception {
		service.updateClub(paramIn);
		return "redirect:/setting/club/index";
	}
	
	/**
	 * 删除俱乐部
	 */
	@RequestMapping("/deleteClub/{id}")
	@ResponseBody
	public ObjectRestResponse deleteClub(@PathVariable("id") Integer id) {
		return service.deleteCLub(id);
	}
	
	/**
	 * 品牌列表(多条件)
	 */
	@RequestMapping("/brand/condition")
	@ResponseBody
	public TableResponse<Brand> findAllBrandByCondition(TableRequest tableRequest) {
		return service.findAllBrandByCondition(tableRequest);
	}
	
	/**
	 * 设为赞助商和取消赞助商
	 */
	@RequestMapping("/updateIsSponsor/{id}/{IsSponsor}")
	@ResponseBody
	public ObjectRestResponse updateIsSponsor(@PathVariable("id") Integer id, @PathVariable("IsSponsor") Integer IsSponsor) {
		return service.updateIsSponsor(id, IsSponsor);
	}
	
	/**
	 * 新增品牌
	 */
	@RequestMapping("/addBrand")
	public String addBrand(BrandAddParamIn paramIn) throws Exception{
		service.addBrand(paramIn);
		return "redirect:/setting/brand/index";
	}
	
	/**
	 * 根据品牌id查询详情
	 */
	@RequestMapping("/brand/detail/{id}")
	public String findBrandById(@PathVariable("id") Integer id, Model model){
		model.addAttribute("brandInfo", service.findBrandById(id)); //品牌信息
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		return SETTING_BRAND_UPDATE;
	}
	
	/**
	 * 修改品牌
	 */
	@RequestMapping("/updateBrand")
	public String updateBrand(BrandUpdateParamIn paramIn) {
		service.updateBrand(paramIn);
		return "redirect:/setting/brand/index";
	}
	
	/**
	 * 删除品牌
	 */
	@RequestMapping("/deleteBrand/{id}")
	@ResponseBody
	public ObjectRestResponse deleteBrand(@PathVariable("id") Integer id) {
		return service.deleteBrand(id);
	}
	
	/**
	 * 器材类型列表
	 */
	@RequestMapping("/equipment")
	@ResponseBody
	public TableResponse<EquipmentType> findAllEquipment(TableRequest tableRequest) {
		return service.findAllEquipment(tableRequest);
	}
	
	/**
	 * 新增器材类型
	 */
	@RequestMapping("/addEquipment")
	public String addEquipment(EquipmentAddParamIn paramIn) {
		service.addEquipment(paramIn);
		return "redirect:/setting/equipment/index";
	}
	
	/**
	 * 根据器材类型id查询详情
	 */
	@RequestMapping("/equipment/detail/{id}")
	public String findEquipmentById(@PathVariable("id") Integer id, Model model){
		model.addAttribute("equipmentInfo", service.findEquipmentById(id));
		return SETTING_EQUIPMENT_UPDATE;
	}
	
	/**
	 * 修改器材类型
	 */
	@RequestMapping("/updateEquipment")
	public String updateEquipment(EquipmentUpdateParamIn paramIn) {
		service.updateEquipment(paramIn);
		return "redirect:/setting/equipment/index";
	}
	
	/**
	 * 删除器材类型
	 */
	@RequestMapping("/deleteEquipment/{id}")
	@ResponseBody
	public ObjectRestResponse deleteEquipment(@PathVariable("id") Integer id) {
		return service.deleteEquipment(id);
	}
}

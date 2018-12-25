package com.hworld.canoe.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hworld.canoe.domain.req.po.member.MemberAddParamIn;
import com.hworld.canoe.domain.req.po.member.MemberUpdateParamIn;
import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParamIn;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.BaseResponse;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.service.MemberService;

/**
 * 会员模块Controller
 * @author xichonghang
 */
@Controller 
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private ServletContext cxt;   

	/**
	 * 集中定义跳转页面名称   
	 */
	private static String MEMBER_INDEX = "/page/member/member_list.html";
	private static String MEMBER_ADD = "/page/member/addMember.html";
	private static String MEMBER_UPDATE = "/page/member/changeMember.html";
	private static String MEMBER_DETAIL = "/page/member/memberDetail.html";
	private static String MEMBER_IMPORT = "/page/member/batchImportMember.html";
	private static String MEMBER_CHANGE = "/page/member/basicInfoChange.html";
 
	/**
	 * 进行页面跳转
	 */
	@RequestMapping("/page")
	public String page(Model model) {
		model.addAttribute("clubs", service.findAllClubByOption());  //俱乐部列表
		return MEMBER_INDEX;
	}
	@RequestMapping("/pageAdd")
	public String pageAdd(Model model) {
		model.addAttribute("clubList", service.findAllClubByOption()); //俱乐部列表
		model.addAttribute("equipmentypeList", service.findAllEquipmentType());  //器材类型列表
		model.addAttribute("zzsList", service.findAllBrand());  //赞助商列表
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		model.addAttribute("countryList", service.findCountryCondition());  //国籍下拉选择列表
		return MEMBER_ADD;
	}
	@RequestMapping("/pageUpdate")
	public String pageUpdate(Model model) {
		model.addAttribute("clubList", service.findAllClubByOption()); //俱乐部列表
		model.addAttribute("equipmentypeList", service.findAllEquipmentType());  //器材类型列表
		model.addAttribute("zzsList", service.findAllBrand());  //赞助商列表
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		model.addAttribute("countryList", service.findCountryCondition());  //国籍下拉选择列表
		return MEMBER_UPDATE;
	}
	@RequestMapping("/pageDetail")
	public String pageDetail() {
		return MEMBER_DETAIL;
	}
	@RequestMapping("/pageImport")
	public String pageImport() {
		return MEMBER_IMPORT;
	}
	@RequestMapping("/pageChange")
	public String pageChange() {
		return MEMBER_CHANGE;
	}
	
	/**
	 * 会员列表(多条件)
	 */
	@RequestMapping("/list/condition")
	@ResponseBody
	public TableResponse<?> findAllMemberByCondition(TableRequest tableRequest){
		return service.findAllMemberByCondition(tableRequest);
	}
	
	/**
	 * 修改状态(置为黑榜或取消黑榜  0不是1是)
	 */
	@RequestMapping("/status/{id}/{status}")
	@ResponseBody
	public ObjectRestResponse updateStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
		return service.updateStatus(id, status);
	}
	
	/**
	 * 注销(将初始或活跃状态改为注销状态)
	 */
	@RequestMapping("/logout/{id}/{status}")
	@ResponseBody
	public ObjectRestResponse logout(@PathVariable("id") Integer id, @PathVariable("status") String status) {
		return service.logout(id, status);
	}
	
	/**
	 * 根据类型id查询品牌信息
	 */
	@RequestMapping("/brandinfo/{tid}")
	@ResponseBody
	public ObjectRestResponse findBrandInfoByTid(@PathVariable("tid") Integer tid){
		List<BrandOutParam> list = service.findBrandInfoByTid(tid);
		return new ObjectRestResponse(list);
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
	 * 新增会员
	 */
	@RequestMapping("/add")
	public String addMember(MemberAddParamIn paramIn, BindingResult bindingResult) {
		service.addMember(paramIn);
		return "redirect:/member/page";
	}
	
	/**
	 * 查询会员详情信息
	 */
	@RequestMapping("/detail/{id}")
	public String findMemberDetail(@PathVariable("id") Integer id, Model model){
		MemberDetailOutParam detail = service.findMemberDetail(id);
		model.addAttribute("detailInfo", detail);
		return MEMBER_DETAIL;
	}
	
	/**
	 * 根据会员id查询信息
	 */
	@RequestMapping("/info/{id}")
	public String findMemberById(@PathVariable("id") Integer id, Model model){
		MemberDetailOutParamIn info = service.findMemberById(id);
		model.addAttribute("memberInfo", info);  //会员信息
		model.addAttribute("clubList", service.findAllClubByOption()); //俱乐部列表
		model.addAttribute("equipmentypeList", service.findAllEquipmentType());  //器材类型列表
		model.addAttribute("zzsList", service.findAllBrand());  //赞助商列表
		model.addAttribute("provinceList", service.findAllProvince());  //省份列表
		model.addAttribute("countryList", service.findCountryCondition());  //国籍下拉选择列表
		return MEMBER_UPDATE;
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
	 * 批量导入
	 */
	@RequestMapping("/batchImport")
	@ResponseBody
	public ObjectRestResponse batchImport(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = cxt.getRealPath("/");
		String filePath = "upload/" + file.getOriginalFilename();
		path = path + "/upload";
		File newFile = new File(path, file.getOriginalFilename());
		file.getFileItem().write(newFile);
		
        service.batchImport(file, request, response);
        return new ObjectRestResponse(200, "success");
	}
	
	/**
	 * 修改会员
	 */
	@RequestMapping(value = "/update")
	public String updateMember(MemberUpdateParamIn paramIn, BindingResult bindingResult) {
		service.updateMember(paramIn);
		return "redirect:/member/page";
	}
	
	/**
	 * 查询所有基础信息变更记录(多条件查询)
	 */
	@RequestMapping("/record")
	@ResponseBody
	public TableResponse<?> findAllBaseInfoChangeRecordPage(TableRequest tableRequest) {
		return service.findAllBaseInfoChangeRecordPage(tableRequest);
	}
}

package com.hworld.canoe.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hworld.canoe.dao.MemberMapper;
import com.hworld.canoe.domain.req.entity.Area;
import com.hworld.canoe.domain.req.entity.Player;
import com.hworld.canoe.domain.req.entity.PlayerBasicInfoRecord;
import com.hworld.canoe.domain.req.po.member.Member;
import com.hworld.canoe.domain.req.po.member.MemberAddParamIn;
import com.hworld.canoe.domain.req.po.member.MemberUpdateParamIn;
import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParamIn;
import com.hworld.canoe.domain.req.vo.member.OldValueOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.member.ZzsOutParam;
import com.hworld.canoe.framework.msg.req.OptionRequest;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.framework.utils.ExcelUtils;

/**
 * 会员模块Service
 * @author xichonghang
 */
@Service
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * 会员列表(多条件)
	 */
	public TableResponse<Player> findAllMemberByCondition(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<Player> list = mapper.findAllMemberByCondition(tableRequest.getParams());
		return TableResponse.<Player>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}
	
	/**
	 * 俱乐部列表
	 */
	public List<OptionRequest> findAllClubByOption() {
		return mapper.findAllClubByOption();
	}
	
	/**
	 * 修改状态(置为黑榜或取消黑榜  0不是1是)
	 */
	public ObjectRestResponse updateStatus(Integer id, Integer status) {
		mapper.updateStatus(id, status);
		return new ObjectRestResponse();
	}
	
	/**
	 * 注销(将初始或活跃状态改为注销状态)
	 */
	public ObjectRestResponse logout(Integer id, String status) {
		mapper.logout(id, status);
		return new ObjectRestResponse();
	}
	
	/**
	 * 根据会员id查询信息
	 */
	public MemberDetailOutParamIn findMemberById(Integer id){
		return mapper.findMemberById(id);
	}
	
	/**
	 * 新增会员
	 */
	public ObjectRestResponse addMember(MemberAddParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", paramIn.getName());
		if (paramIn.getName().length() == 2 || paramIn.getName().length() == 3) {
			map.put("firstName", paramIn.getName().substring(0, 1));
			map.put("lastName", paramIn.getName().substring(1));
		}else {
			map.put("firstName", "");
			map.put("lastName", "");
		}
		map.put("mobile", paramIn.getMobile());
		map.put("clubId", paramIn.getClubId());
		map.put("idType", paramIn.getIdType());
		map.put("idNo", paramIn.getIdNo());
		map.put("gender", paramIn.getGender());
		map.put("brandId", paramIn.getBrandId());
		map.put("country", paramIn.getCountry());
		map.put("province", paramIn.getProvince());
		map.put("model", paramIn.getModel());
		map.put("type", paramIn.getType());
		map.put("brand", paramIn.getBrand());
		map.put("remark", paramIn.getRemark());
		
		mapper.addMember(map);
		return new ObjectRestResponse();
	}
	
	/**
     * 国籍下拉框多选择
     */
	public List<Area> findCountryCondition(){
		return mapper.findCountryCondition();
	}
	
	/**
	 * 查询所有器材类型
	 */
	public List<EquipmentTypeOutParam> findAllEquipmentType(){
		return mapper.findAllEquipmentType();
	}
	
	/**
	 * 根据类型id查询品牌信息
	 */
	public List<BrandOutParam> findBrandInfoByTid(Integer tid){
		return mapper.findBrandInfoByTid(tid);
	}
	
	/**
	 * 查询所有赞助商
	 */
	public List<ZzsOutParam> findAllBrand(){
		return mapper.findAllBrand();
	}
	
	/**
	 * 查询所有省份
	 */
	public List<ProvinceOutParam> findAllProvince(){
		return mapper.findAllProvince();
	}
	
	/**
	 * 根据省份id查询城市信息
	 */
	public List<CityOutParam> findAllCityByPid(Integer pid){
		return mapper.findAllCityByPid(pid);
	}
	
	/**
	 * 查询会员详情信息
	 */
	public MemberDetailOutParam findMemberDetail(Integer id){
		MemberDetailOutParam detail = mapper.findMemberDetail(id);
		detail.setTempTime(new SimpleDateFormat("yyyy-MM-dd").format(detail.getRegisterTime()));
		if (!(detail.getRaceDate() == null)) {
			detail.setMatchDate((new SimpleDateFormat("yyyy-MM-dd").format(detail.getRaceDate())));
		}else {
			detail.setMatchDate("");
		}
		return detail;
	}
	
	/**
	 * 模板下载
	 */
	public void download(HttpServletResponse response) throws Exception{
		// 定义文件名称
		String fname = "member_input（模板）.xlsx";
		// 转换成中文
		String filename = URLEncoder.encode(fname, "UTF-8");
		// 弹出下载面板
		response.setContentType("application/x-msdownload");
		// 设置面板中的显示内容
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		// 产生输入流,获得下载的文件
		InputStream in = MemberService.class.getClassLoader().getResourceAsStream("i18n/" + fname);
		// 产生输出流,向客户端输出文件
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[8192];
		int len = 0;
		while ((len = in.read(b, 0, 8192)) != -1) {
			out.write(b, 0, len);
		}
		out.close();
		in.close();
	}
	
	/**
	 * 批量导入
	 */
	public ObjectRestResponse batchImport(CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

		InputStream in = null;
		try {
			in = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<List<Object>> listob = null;
		try {
			listob = new ExcelUtils().getBankListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Member vo = new Member();

			vo.setName(String.valueOf(lo.get(0)));
			String gen = String.valueOf(lo.get(1));
			if ("男".equals(gen)) {
				vo.setGender("M");
			} else if ("女".equals(gen)) {
				vo.setGender("F");
			}
			vo.setMobile(String.valueOf(lo.get(2)));
			vo.setCountry(mapper.findCountryId(String.valueOf(lo.get(3))));
			String type = String.valueOf(lo.get(4));
			if ("身份证".equals(type)) {
				vo.setIdType("ID_CARD");
			}else if ("护照".equals(type)) {
				vo.setIdType("PASSPORT");
			}
			vo.setIdNo(String.valueOf(lo.get(5)));
			vo.setClubId(mapper.findClubId(String.valueOf(lo.get(6))));
			vo.setBrandId(mapper.findBrandId(String.valueOf(lo.get(8))));
			vo.setRemark(String.valueOf(lo.get(9)));

			mapper.addMemberByExcel(vo);
		}
		return new ObjectRestResponse(200, "success");
	}
	
	
	/**
	 * 修改会员
	 */
	public ObjectRestResponse updateMember(MemberUpdateParamIn paramIn) {
		//根据会员id查询到想要的旧值
		OldValueOutParam info = mapper.findOldValueById(paramIn.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", paramIn.getId());
		map.put("name", paramIn.getName());
		if (paramIn.getName().length() == 2 || paramIn.getName().length() == 3) {
			map.put("firstName", paramIn.getName().substring(0, 1));
			map.put("lastName", paramIn.getName().substring(1));
		}else {
			map.put("firstName", "");
			map.put("lastName", "");
		}
		map.put("mobile", paramIn.getMobile());
		map.put("clubId", paramIn.getClubId());
		map.put("idType", paramIn.getIdType());
		map.put("idNo", paramIn.getIdNo());
		map.put("gender", paramIn.getGender());
		map.put("brandId", paramIn.getBrandId());
		map.put("country", paramIn.getCountry());
		map.put("province", paramIn.getProvince());
		map.put("remark", paramIn.getRemark());
		
		map.put("model", paramIn.getModel());
		map.put("equipmentTypeId", paramIn.getType());
		map.put("equipmentBrandId", paramIn.getBrand());
		
		map.put("oldValueName", info.getPlayerName());	
		map.put("oldValueGender", info.getGender());
		map.put("oldValueMobile", info.getMobile());
		map.put("oldValueCountry", info.getCountry());
		map.put("oldValueIdType", info.getIdType());
		map.put("oldValueIdNo", info.getIdNo());
		map.put("oldValueClubId", info.getClubId());
		map.put("oldValueBrandId", info.getBrandId());
		map.put("oldValueClubName", info.getClubName());
		map.put("oldValueBrandName", info.getBrandName());
		map.put("oldValueRelGender", info.getRelGender());
		map.put("oldValueRelIdType", info.getRelIdType());
		
		map.put("newValueCountry", mapper.findCountryNameById(paramIn.getCountry()));
		map.put("newValueClub", mapper.findClubNameById(paramIn.getClubId()));
		map.put("newValueBrand", mapper.findBrandNameById(paramIn.getBrandId()));
		if (paramIn.getGender().equals("M")) {
			map.put("newValueGender", "男");
		}else if (paramIn.getGender().equals("F")) {
			map.put("newValueGender", "女");
		}
		if (paramIn.getIdType().equals("ID_CARD")) {
			map.put("newValueIdType", "身份证");
		}else if (paramIn.getIdType().equals("PASSPORT")) {
			map.put("newValueIdType", "护照");
		}
		
		mapper.updateMember(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 查询所有基础信息变更记录
	 */
	public TableResponse<PlayerBasicInfoRecord> findAllBaseInfoChangeRecordPage(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<PlayerBasicInfoRecord> list = mapper.findAllBaseInfoChangeRecordPage(tableRequest.getParams());
		return TableResponse.<PlayerBasicInfoRecord>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}

	
}


package com.hworld.canoe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hworld.canoe.dao.SettingMapper;
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
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.setting.BrandDetailOutParam;
import com.hworld.canoe.domain.req.vo.setting.ClubDetailOutParam;
import com.hworld.canoe.domain.req.vo.setting.EquipmentDetailOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;

/**
 * 设置模块Service
 * @author xichonghang
 */
@Service
public class SettingService {
	
	@Autowired
	private SettingMapper mapper;
	
	/**
	 * 查询俱乐部(多条件)
	 */
	public TableResponse<Club> findAllClubByCondition(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<Club> list = mapper.findAllClubByCondition(tableRequest.getParams());
		return TableResponse.<Club>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
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
	 * 新增俱乐部(带有图片上传)
	 */
	public void addClud(ClubAddParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", paramIn.getName());
		map.put("introduction", paramIn.getIntroduction());
		map.put("logo", paramIn.getLogo());
		map.put("areaId", paramIn.getAreaId());
		map.put("contactName", paramIn.getContactName());
		map.put("mobile", paramIn.getMobile());
		map.put("mail", paramIn.getMail());
		map.put("status", paramIn.getStatus());
		
		mapper.addClud(map);
	}
	
	/**
	 * 根据俱乐部id查询详情
	 */
	public ClubDetailOutParam findClubById(Integer id){
		return mapper.findClubById(id);
	}
	
	/**
	 * 修改俱乐部
	 */
	public ObjectRestResponse updateClub(ClubUpdateParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubName", paramIn.getName());
		map.put("introduction", paramIn.getIntroduction());
		map.put("logo", paramIn.getLogo());
		map.put("status", paramIn.getStatus());
		map.put("areaId", paramIn.getAreaId());
		map.put("contactName", paramIn.getContactName());
		map.put("mobile", paramIn.getMobile());
		map.put("mail", paramIn.getMail());
		map.put("id", paramIn.getId());
		
		mapper.updateClud(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 删除俱乐部(若俱乐部下有成员则无法删除)
	 */
	public ObjectRestResponse deleteCLub(Integer id) {
		Integer count = mapper.findClubIsCanDelete(id);
		if (count > 0) {
			return new ObjectRestResponse(500, "不可删除");
		}else {
			mapper.deleteClub(id);
			return new ObjectRestResponse(200, "删除成功");
		}
	}
	
	/**
	 * 查询品牌(多条件)
	 */
	public TableResponse<Brand> findAllBrandByCondition(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<Brand> list = mapper.findAllBrandByCondition(tableRequest.getParams());
		return TableResponse.<Brand>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}
	
	/**
	 * 设为赞助商和取消赞助商
	 */
	public ObjectRestResponse updateIsSponsor(Integer id, Integer IsSponsor) {
		mapper.updateIsSponsor(id, IsSponsor);
		return new ObjectRestResponse();
	}
	
	/**
	 * 新增品牌(带有图片上传)
	 */
	public void addBrand(BrandAddParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandName", paramIn.getBrandName());
		map.put("logo", paramIn.getLogo());
		map.put("status", paramIn.getStatus());
		map.put("areaId", paramIn.getAreaId());
		map.put("contactName", paramIn.getContactName());
		map.put("mobile", paramIn.getMobile());
		map.put("mail", paramIn.getMail());
		
		mapper.addBrand(map);
	}
	
	/**
	 * 根据品牌id查询详情
	 */
	public BrandDetailOutParam findBrandById(Integer id){
		return mapper.findBrandById(id);
	}
	
	/**
	 * 修改品牌
	 */
	public ObjectRestResponse updateBrand(BrandUpdateParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandName", paramIn.getBrandName());
		map.put("logo", paramIn.getLogo());
		map.put("status", paramIn.getStatus());
		map.put("areaId", paramIn.getAreaId());
		map.put("contactName", paramIn.getContactName());
		map.put("mobile", paramIn.getMobile());
		map.put("mail", paramIn.getMail());
		map.put("id", paramIn.getId());
		
		mapper.updateBrand(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 删除品牌
	 */
	public ObjectRestResponse deleteBrand(Integer id) {
		mapper.deleteBrand(id);
		return new ObjectRestResponse();
	}
	
	/**
	 * 查询器材类型列表
	 */
	public TableResponse<EquipmentType> findAllEquipment(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<EquipmentType> list = mapper.findAllEquipment();
		return TableResponse.<EquipmentType>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}
	
	/**
	 * 新增惯用器材类型
	 */
	public ObjectRestResponse addEquipment(EquipmentAddParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", paramIn.getName());
		mapper.addEquipment(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 根据器材类型id查询详情
	 */
	public EquipmentDetailOutParam findEquipmentById(Integer id){
		return mapper.findEquipmentById(id);
	}
	
	/**
	 * 修改器材类型
	 */
	public ObjectRestResponse updateEquipment(EquipmentUpdateParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", paramIn.getId());
		map.put("name", paramIn.getName());
		mapper.updateEquipment(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 删除器材类型
	 */
	public ObjectRestResponse deleteEquipment(Integer id) {
		mapper.deleteEquipment(id);
		return new ObjectRestResponse();
	}
}

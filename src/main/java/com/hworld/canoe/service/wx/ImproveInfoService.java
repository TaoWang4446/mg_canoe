package com.hworld.canoe.service.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hworld.canoe.dao.wx.ImproveInfoMapper;
import com.hworld.canoe.domain.req.entity.Area;
import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.wx.improveInfo.ImproveInfoOutParam;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;

/**
 * 完善信息Service
 * @author xichonghang
 */
@Service
public class ImproveInfoService {
	
	@Autowired
	private ImproveInfoMapper mapper;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 查询所有国家
	 */
	public List<Area> findCountry(){
		return mapper.findCountry();
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
	 * 根据会员id查询信息
	 */
	public ImproveInfoOutParam findMemberInfoById() {
		Integer id = (Integer) request.getSession().getAttribute("userInfo.id");
		return mapper.findMemberInfoById(id);
	}
	
	/**
	 * 完善信息
	 */
	public ObjectRestResponse improveInfo(ImproveInfoOutParam param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", param.getMemberId());
		map.put("name", param.getMemberName());
		map.put("country", param.getCountryId());
		map.put("idNo", param.getIdNo());
		map.put("province", param.getAreaId());
		map.put("equipmentTypeId", param.getTypeId());
		map.put("equipmentBrandId", param.getBrandId());
		map.put("model", param.getModel());
		
		mapper.improveInfo(map);
		return new ObjectRestResponse();
	}
}


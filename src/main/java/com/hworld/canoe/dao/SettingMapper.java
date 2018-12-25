package com.hworld.canoe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.entity.Brand;
import com.hworld.canoe.domain.req.entity.Club;
import com.hworld.canoe.domain.req.entity.EquipmentType;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.setting.BrandDetailOutParam;
import com.hworld.canoe.domain.req.vo.setting.ClubDetailOutParam;
import com.hworld.canoe.domain.req.vo.setting.EquipmentDetailOutParam;

/**
 * 设置模块Mapper
 * @author xichonghang
 */
@Mapper
public interface SettingMapper {
	
	/**
	 * 查询俱乐部(多条件)
	 */
	List<Club> findAllClubByCondition(@Param("map") Map<String, Object> map);
	
	/**
	 * 查找所有省份
	 */
	List<ProvinceOutParam> findAllProvince();
	
	/**
     * 根据省份id查询城市信息
     */
    List<CityOutParam> findAllCityByPid(@Param("pid") Integer pid);
	
	/**
	 * 新增俱乐部
	 */
	void addClud(@Param("map") Map<String, Object> map);
	
	/**
	 * 根据俱乐部id查询详情
	 */
	ClubDetailOutParam findClubById(@Param("id") Integer id);
	
	/**
	 * 修改俱乐部
	 */
	void updateClud(@Param("map") Map<String, Object> map);
	
	/**
	 * 根据俱乐部id得知此俱乐部下是否有成员
	 */
	Integer findClubIsCanDelete(@Param("id") Integer id);
	
	/**
	 * 删除俱乐部(若俱乐部下有成员则无法删除)
	 */
	void deleteClub(@Param("id") Integer id);
	
	/**
	 * 查询品牌(多条件)
	 */
	List<Brand> findAllBrandByCondition(@Param("map") Map<String, Object> map);
	
	/**
	 * 设为赞助商和取消赞助商
	 */
	void updateIsSponsor(@Param("id") Integer id, @Param("IsSponsor") Integer IsSponsor);
	
	/**
	 * 新增品牌
	 */
	void addBrand(@Param("map") Map<String, Object> map);
	
	/**
	 * 根据品牌id查询详情
	 */
	BrandDetailOutParam findBrandById(@Param("id") Integer id);
	
	/**
	 * 修改品牌
	 */
	void updateBrand(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除品牌
	 */
	void deleteBrand(@Param("id") Integer id);
	
	/**
	 * 器材类型列表
	 */
	List<EquipmentType> findAllEquipment();
	
	/**
	 * 新增惯用器材类型
	 */
	void addEquipment(@Param("map") Map<String, Object> map);
	
	/**
	 * 根据器材类型id查询详情
	 */
	EquipmentDetailOutParam findEquipmentById(@Param("id") Integer id);
	
	/**
	 * 修改器材类型
	 */
	void updateEquipment(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除器材类型
	 */
	void deleteEquipment(@Param("id") Integer id);

	
}


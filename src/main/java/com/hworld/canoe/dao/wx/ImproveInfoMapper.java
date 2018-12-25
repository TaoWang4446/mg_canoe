package com.hworld.canoe.dao.wx;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.entity.Area;
import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.wx.improveInfo.ImproveInfoOutParam;

/**
 * 完善信息Mapper
 * @author xichonghang
 */
@Mapper
public interface ImproveInfoMapper {
	
	/**
	 * 查询所有国家
	 */
	List<Area> findCountry();
	
	/**
     * 查询所有省份
     */
    List<ProvinceOutParam> findAllProvince();

    /**
     * 根据省份id查询城市信息
     */
    List<CityOutParam> findAllCityByPid(@Param("pid") Integer pid);
    
    /**
     * 查询所有器材类型
     */
    List<EquipmentTypeOutParam> findAllEquipmentType();

    /**
     * 根据类型id查询品牌信息
     */
    List<BrandOutParam> findBrandInfoByTid(@Param("tid") Integer tid);
    
    /**
     * 根据会员id查询信息
     */
    ImproveInfoOutParam findMemberInfoById(@Param("id") Integer id);
    
    /**
     * 完善信息
     */
    void improveInfo(@Param("map") Map<String, Object> map);

}


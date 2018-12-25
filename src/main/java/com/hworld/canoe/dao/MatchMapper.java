package com.hworld.canoe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.entity.Race;
import com.hworld.canoe.domain.req.entity.RaceSeason;
import com.hworld.canoe.domain.req.po.match.RaceAddParamIn;
import com.hworld.canoe.domain.req.vo.match.RaceDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;

/**
 * 赛事模块Mapper
 * @author xichonghang
 */
@Mapper
public interface MatchMapper {
	
	/**
	 * 赛事列表(多条件)
	 */
	List<Race> findAllRaceByCondition(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询所有赛季
	 */
	List<RaceSeason> findAllRaceSeason();
	
	/**
     * 查询所有器材类型
     */
    List<EquipmentTypeOutParam> findAllEquipmentType();
    
    /**
     * 查询所有省份
     */
    List<ProvinceOutParam> findAllProvince();

    /**
     * 根据省份id查询城市信息
     */
    List<CityOutParam> findAllCityByPid(@Param("pid") Integer pid);
	
	/**
	 * 新增赛事(此处有mybatis主键返回)
	 */
	void addRace(RaceAddParamIn paramIn);
	
	/**
	 * 新增赛事下面的组
	 */
	void addRaceGroup(RaceAddParamIn paramIn);
	
	/**
	 * 通过赛事id查询赛事详细信息
	 */
	RaceDetailOutParam findRaceDetailById(@Param("id") Integer id);
	
	/**
	 * 修改赛事
	 */
	void updateRace(@Param("map") Map<String, Object> map);
	
	/**
	 * 根据赛事id得知此赛事是否已录入成绩
	 */
	Integer findRaceIsCanDelete(@Param("id") Integer id);
	
	/**
	 * 删除赛事
	 */
	void deleteRace(@Param("id") Integer id);
	
	/**
	 * 成绩发布前确认成绩是否被清空
	 */
	Integer sureScoreIsClear(@Param("id") Integer id);
	
	/**
	 * 成绩发布
	 */
	void publishScore(@Param("raceId") Integer raceId);
}


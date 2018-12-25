package com.hworld.canoe.dao;

import java.util.List;
import java.util.Map;

import com.hworld.canoe.domain.req.pojo.RaceCompetitionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hworld.canoe.domain.req.entity.RaceCompetition;
import com.hworld.canoe.domain.req.po.score.Score;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ZzsOutParam;
import com.hworld.canoe.domain.req.vo.score.GroupNameOutParam;
import com.hworld.canoe.domain.req.vo.score.ScoreOutParam;
import com.hworld.canoe.domain.req.vo.score.ScoreUpdateOutParam;

/**
 * 成绩管理模块Mapper
 * @author xichonghang
 */
@Mapper
public interface ScoreMapper {
	
	/**
	 * 成绩列表(多条件分页)
	 */
	List<RaceCompetition> findAllScore(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询所有俱乐部名称
	 */
	List<ZzsOutParam> findAllClubByOption();
	
	/**
	 * 查询所有大组名称
	 */
	List<GroupNameOutParam> findAllGroup();
	
	/**
     * 查询所有器材类型
     */
    List<EquipmentTypeOutParam> findAllEquipmentType();
	
	/**
	 * 根据成绩id查询成绩
	 */
	ScoreUpdateOutParam findById(@Param("id") Integer id);
	
	/**
	 * 修改成绩
	 */
	void updateScore(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除成绩
	 */
	void deleteScore(@Param("id") Integer id);
	
	/**
	 * 导入成绩
	 */
	void addScore(@Param("score") Score score);
	
	/**
	 * 清空成绩
	 */
	void clearAllScore();
	
	/**
	 * 根据组名称得到组id
	 */
	Integer findGroupIdByName(String groupName);
	
	/**
	 * 根据手机号得到会员id
	 */
	Integer findPlayerByPhone(String mobile);
	
	/**
	 * 根据器材类型名称得到器材类型id
	 */
	Integer findEquipmentTypeIdByName(String equipmentTypeName);
	
	/**
	 * 积分计算前判断成绩是否被清空
	 */
	Integer sureScoreIsClear();
	
	/**
	 * 积分计算
	 */
	List<ScoreOutParam> findAllPoint(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除积分
	 */
	void deletePoint(@Param("pointId") Integer pointId);

	int countByExample(RaceCompetitionExample example);

	int deleteByExample(RaceCompetitionExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(RaceCompetition record);

	int insertSelective(RaceCompetition record);

	List<RaceCompetition> selectByExample(RaceCompetitionExample example);

	RaceCompetition selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") RaceCompetition record, @Param("example") RaceCompetitionExample example);

	int updateByExample(@Param("record") RaceCompetition record, @Param("example") RaceCompetitionExample example);

	int updateByPrimaryKeySelective(RaceCompetition record);

	int updateByPrimaryKey(RaceCompetition record);
}


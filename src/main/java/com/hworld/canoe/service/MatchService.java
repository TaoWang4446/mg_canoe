package com.hworld.canoe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hworld.canoe.dao.MatchMapper;
import com.hworld.canoe.domain.req.entity.Race;
import com.hworld.canoe.domain.req.entity.RaceSeason;
import com.hworld.canoe.domain.req.po.match.RaceAddParamIn;
import com.hworld.canoe.domain.req.po.match.RaceUpdateParamIn;
import com.hworld.canoe.domain.req.vo.match.RaceDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;

/**
 * 赛事管理Service
 * @author xichonghang
 */
@Service
public class MatchService {
	
	@Autowired
	private MatchMapper mapper;
	
	/**
	 * 赛事列表(多条件)
	 */
	public TableResponse<Race> findAllRaceByCondition(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<Race> list = mapper.findAllRaceByCondition(tableRequest.getParams());
		return TableResponse.<Race>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}
	
	/**
	 * 查询所有赛季
	 */
	public List<RaceSeason> findAllRaceSeason(){
		return mapper.findAllRaceSeason();
	}
	
	/**
     * 查询所有器材类型
     */
    public List<EquipmentTypeOutParam> findAllEquipmentType(){
    	return mapper.findAllEquipmentType();
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
	 * 新增赛事
	 */
	public ObjectRestResponse addRace(RaceAddParamIn paramIn) throws ParseException {
		paramIn.setDod(paramIn.getDistince() * 0.2 / 5 + paramIn.getWave() * 0.8 * 2);
		paramIn.setTempDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramIn.getRaceDate()));
		mapper.addRace(paramIn);  //注意:此处的返回值是数据库受影响的行数,并不是实际id值
		mapper.addRaceGroup(paramIn);
		return new ObjectRestResponse();
	}
	
	/**
	 * 通过赛事id查询赛事详细信息
	 */
	public RaceDetailOutParam findRaceDetailById(Integer id) {
		return mapper.findRaceDetailById(id);
	}
	
	/**
	 * 修改赛事
	 */
	public ObjectRestResponse updateRace(RaceUpdateParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", paramIn.getRaceName());
		map.put("raceDate", paramIn.getRaceDate());
		map.put("areaId", paramIn.getAreaId());
		map.put("address", paramIn.getAddress());
		map.put("organiser", paramIn.getOrganiser());
		map.put("id", paramIn.getRaceId());
		
		mapper.updateRace(map);
		return new ObjectRestResponse();
	}
	
	/**
	 * 删除赛事
	 */
	public ObjectRestResponse deleteRace(Integer id) {
		Integer count = mapper.findRaceIsCanDelete(id);
		if (count > 0) {
			return new ObjectRestResponse(500, "不可删除");
		}else {
			mapper.deleteRace(id);
			return new ObjectRestResponse(200, "删除成功");
		}
	}
	
	/**
	 * 成绩发布
	 */
	public ObjectRestResponse publishScore(Integer raceId) {
		Integer count = mapper.sureScoreIsClear(raceId);
		if (count != null && count != 0) {
			mapper.publishScore(raceId);
			return new ObjectRestResponse();
		}else {
			return new ObjectRestResponse(500, "成绩发布失败");
		}
	}
}


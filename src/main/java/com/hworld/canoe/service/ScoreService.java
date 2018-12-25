package com.hworld.canoe.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.hworld.canoe.dao.ScoreMapper;
import com.hworld.canoe.domain.req.entity.RaceCompetition;
import com.hworld.canoe.domain.req.po.score.Score;
import com.hworld.canoe.domain.req.po.score.ScoreUpdateParamIn;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.ZzsOutParam;
import com.hworld.canoe.domain.req.vo.score.GroupNameOutParam;
import com.hworld.canoe.domain.req.vo.score.ScoreOutParam;
import com.hworld.canoe.domain.req.vo.score.ScoreUpdateOutParam;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.framework.utils.ExcelUtils;

/**
 * 成绩管理Service
 * @author xichonghang
 */
@Service
public class ScoreService {

	@Autowired
	private ScoreMapper mapper;


	/**
	 * 成绩列表(多条件分页)
	 */
	public TableResponse<RaceCompetition> findAllScore(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<RaceCompetition> list = mapper.findAllScore(tableRequest.getParams());
		return TableResponse.<RaceCompetition>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}

	/**
	 * 查询所有俱乐部名称
	 */
	public List<ZzsOutParam> findAllClubByOption() {
		return mapper.findAllClubByOption();
	}

	/**
	 * 查询所有大组名称
	 */
	public List<GroupNameOutParam> findAllGroup(){
		return mapper.findAllGroup();
	}

	/**
	 * 模板下载
	 */
	public void download(HttpServletResponse response) throws Exception{
		// 定义文件名称
		String fname = "score_input.xlsx";
		// 转换成中文
		String filename = URLEncoder.encode(fname, "UTF-8");
		// 弹出下载面板
		response.setContentType("application/x-msdownload");
		// 设置面板中的显示内容
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		// 产生输入流,获得下载的文件
		InputStream in = ScoreService.class.getClassLoader().getResourceAsStream("i18n/" + fname);
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
	 * 查询所有器材类型
	 */
	public List<EquipmentTypeOutParam> findAllEquipmentType(){
		return mapper.findAllEquipmentType();
	}

	/**
	 * 根据成绩id查询成绩
	 */
	public ScoreUpdateOutParam findById(Integer id) {
		return mapper.findById(id);
	}

	/**
	 * 修改成绩
	 */
	public ObjectRestResponse updateScore(ScoreUpdateParamIn paramIn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", paramIn.getScoreId());
		map.put("playerId", paramIn.getPlayerId());
		map.put("name", paramIn.getName());
		map.put("mobile", paramIn.getMobile());
		map.put("isSeed", paramIn.getIsSeed());
		map.put("clubId", paramIn.getClubId());
		map.put("raceGroupId", paramIn.getRaceGroupId());
		map.put("finshTime", paramIn.getFinshTime());
		map.put("equipmentCategoryId", paramIn.getEquipmentCategoryId());
		map.put("brand", paramIn.getBrand());
		map.put("equipmentName", paramIn.getEquipmentName());
		mapper.updateScore(map);
		return new ObjectRestResponse();
	}

	/**
	 * 删除成绩
	 */
	public ObjectRestResponse deleteScore(Integer id) {
		mapper.deleteScore(id);
		return new ObjectRestResponse();
	}

	/**
	 * 清空成绩
	 */
	public ObjectRestResponse clearAllScore() {
		mapper.clearAllScore();
		return new ObjectRestResponse();
	}

	/**
	 * 导入成绩
	 */
	public ObjectRestResponse addScore(CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

		InputStream in = null;
		in = file.getInputStream();

		List<List<Object>> listob = null;
		listob = new ExcelUtils().getBankListByExcel(in, file.getOriginalFilename());

		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			for (short j = 0; j < lo.size(); j++) {
				Object value = lo.get(j);
				String data = String.valueOf(value);
				System.out.println(data + "\t");
			}
			/*RaceCompetition raceCompetition = new RaceCompetition();
			raceCompetition.setRaceGroupId(mapper.findGroupIdByName(String.valueOf(lo.get(0))));
			raceCompetition.setPlayerId(mapper.findPlayerByPhone(String.valueOf(lo.get(1))));
			raceCompetition.setFinshTime(String.valueOf(lo.get(6)));
			Integer equipmenttypeId = mapper.findEquipmentTypeIdByName(String.valueOf(lo.get(7)));
			raceCompetition.setEquipmentCategoryId(equipmenttypeId);
			System.out.println(">>>>>>>>>>>>>"+String.valueOf(lo.get(8)));
			raceCompetition.setBrand(String.valueOf(lo.get(8)==null? "":lo.get(8)));
			raceCompetition.setEquipmentName(String.valueOf(lo.get(9)==null? "":lo.get(9)));*/
			/*Score score = new Score();

			score.setGroupId(mapper.findGroupIdByName(String.valueOf(lo.get(0))));
			score.setPlayerId(mapper.findPlayerByPhone(String.valueOf(lo.get(2))));
			score.setFinishTime(String.valueOf(lo.get(6)));
			score.setEquipmentCategoryId(mapper.findEquipmentTypeIdByName(String.valueOf(lo.get(7))));  //器材类型
			score.setBrand(String.valueOf(lo.get(8)));  //器材品牌
			score.setEquipmentName(String.valueOf(lo.get(9)));  //器材型号*/

			//mapper.addScore(score);
			//mapper.insertSelective(raceCompetition);
		}
		return new ObjectRestResponse(200, "success");
	}

	/**
	 * 积分计算
	 */
	public TableResponse<ScoreOutParam> findAllPoint(TableRequest tableRequest){
		Page<Object> page = PageHelper.startPage(tableRequest.getOffset(), tableRequest.getLimit());
		List<ScoreOutParam> list = mapper.findAllPoint(tableRequest.getParams());
		return TableResponse.<ScoreOutParam>builder().recordsTotal(page.getTotal()).recordsFiltered(page.getTotal()).data(list).build();
	}

	/**
	 * 删除积分
	 */
	public ObjectRestResponse deletePoint(Integer pointId) {
		mapper.deletePoint(pointId);
		return new ObjectRestResponse();
	}
}


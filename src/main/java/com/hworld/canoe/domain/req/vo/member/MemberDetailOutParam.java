package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 会员详情出参
 * @author xichonghang
 */
@Data
public class MemberDetailOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String memberName; //会员姓名
	private String gender; //性别
	private String wechatOpenid; //微信ID
	private String mobile; //手机号
	private String idNo; //证件号
	private String idType; //证件类型
	private Integer age; //年龄
	private String country; //国籍
	private String province; //居住城市
	private String clubName; //所属俱乐部
	private String zzh; //赞助商
	private String isSeed; //是否黑榜选手
	private BigDecimal totalCompetePoint; //累积竞争积分
	private BigDecimal totalFinishPoint; //累积完赛积分
	private String equipmentxName1; //惯用器材型号
	private String typeName; //惯用器材类型
	private String brandName; //惯用器材品牌
	private Integer id; //编号
	private String statue; //状态
	private String registerChannel; //注册渠道
	private Date registerTime; //注册时间
	private String modifiedBy; //操作人
	private String raceName; //比赛名称
	private Date raceDate; //比赛日期
	private String groupName; //参与组别
	private String finshTime;  //完赛时间
	private BigDecimal competitionPoint; //竞争积分
	private BigDecimal finishPoint; //完赛积分
	private String chooseTypeName; //选择类型
	private String equipmentName2;  //型号
	private String brand; //品牌
	private Integer rank; //排名
	private String tempTime;  //在页面取注册时间时取此字段
	private String matchDate; //在页面取比赛日期时取此字段
}


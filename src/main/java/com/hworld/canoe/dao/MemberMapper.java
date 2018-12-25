package com.hworld.canoe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hworld.canoe.domain.req.entity.Area;
import com.hworld.canoe.domain.req.entity.Player;
import com.hworld.canoe.domain.req.entity.PlayerBasicInfoRecord;
import com.hworld.canoe.domain.req.po.member.Member;
import com.hworld.canoe.domain.req.vo.member.BrandOutParam;
import com.hworld.canoe.domain.req.vo.member.CityOutParam;
import com.hworld.canoe.domain.req.vo.member.EquipmentTypeOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParam;
import com.hworld.canoe.domain.req.vo.member.MemberDetailOutParamIn;
import com.hworld.canoe.domain.req.vo.member.OldValueOutParam;
import com.hworld.canoe.domain.req.vo.member.ProvinceOutParam;
import com.hworld.canoe.domain.req.vo.member.ZzsOutParam;
import com.hworld.canoe.framework.msg.req.OptionRequest;

/**
 * 会员模块Mapper
 *
 * @author xichonghang
 */
@Mapper
public interface MemberMapper {

    /**
     * 会员列表(多条件)
     */
    List<Player> findAllMemberByCondition(@Param("map") Map<String, Object> map);

    /**
     * 查询俱乐部信息
     */
    @Select("select DISTINCT a.id as id,a.club_name as val\n" +
            "\t\tfrom club a\n" +
            "\t\twhere a.`status` = 1 and deleted_flag = 0")
    List<OptionRequest> findAllClubByOption();

    /**
     * 修改状态(置为黑榜或取消黑榜 0不是1是)
     */
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
    
    /**
     * 注销(将初始或活跃状态改为注销状态)
     */
    void logout(@Param("id") Integer id, @Param("status") String status);

    /**
     * 新增会员
     */
    void addMember(@Param("map") Map<String, Object> map);
    
    /**
     * 国籍下拉框多选择
     */
    List<Area> findCountryCondition();

    /**
     * 查询所有器材类型
     */
    List<EquipmentTypeOutParam> findAllEquipmentType();

    /**
     * 根据类型id查询品牌信息
     */
    List<BrandOutParam> findBrandInfoByTid(@Param("tid") Integer tid);

    /**
     * 查询所有赞助商
     */
    List<ZzsOutParam> findAllBrand();

    /**
     * 查询所有省份
     */
    List<ProvinceOutParam> findAllProvince();

    /**
     * 根据省份id查询城市信息
     */
    List<CityOutParam> findAllCityByPid(@Param("pid") Integer pid);

    /**
     * 查询会员详情信息
     */
    MemberDetailOutParam findMemberDetail(@Param("id") Integer id);

    /**
     * 根据id查询会员信息
     */
    MemberDetailOutParamIn findMemberById(@Param("id") Integer id);

    /**
     * 修改会员
     */
    void updateMember(@Param("map") Map<String, Object> map);
    
    /**
     * 根据id查询到旧值
     */
    OldValueOutParam findOldValueById(@Param("id") Integer id);

    /**
     * 查询所有基础信息变更记录
     */
    List<PlayerBasicInfoRecord> findAllBaseInfoChangeRecordPage(@Param("map") Map<String, Object> map);

    /**
     * excel批量导入会员
     */
    void addMemberByExcel(@Param("member") Member member);

    /**
     * 根据国家名称得到国家id
     */
    Integer findCountryId(String countryName);

    /**
     * 根据俱乐部名称得到俱乐部id
     */
    Integer findClubId(String clubName);

    /**
     * 根据赞助商名称得到赞助商id
     */
    Integer findBrandId(String brandName);
    
    /**
     * 根据国家id得到国家名称
     */
    String findCountryNameById(Integer id);
    
    /**
     * 根据俱乐部id得到俱乐部名称
     */
    String findClubNameById(Integer id);
    
    /**
     * 根据赞助商id得到赞助商名称
     */
    String findBrandNameById(Integer id);
}

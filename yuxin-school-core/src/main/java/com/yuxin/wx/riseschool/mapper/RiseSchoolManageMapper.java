package com.yuxin.wx.riseschool.mapper;

import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.UsersFront;

import java.util.List;
import java.util.Map;

public interface RiseSchoolManageMapper {
    void insertRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 根据不同条件查询学校信息
     * @param riseSchoolManageVo
     * @return
     */
    List<RiseSchoolManageVo> queryRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 处理逻辑的插入
     * @param map
     */
    void insertRiseSchoolInfoAndUsers(Map map);

    /**
     * 更新学校信息
     * @param riseSchoolManageVo
     */
    void updateRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 更新学校信息
     * @param riseSchoolManageVo
     */
    void updateRiseSchoolInfoTwo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 查询学校信息总条数
     * @param riseSchoolManageVo
     * @return
     */
    Integer queryRiseSchoolCount(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 查询字典值
     * @param map
     * @return
     */
    List<SysDictVo> queryRiseSchoolDict(Map map);
    List<SysDictVo> querySchoolName(Map map);
    List<UsersFront> searchUsers(Map map);

    List<RiseSchoolManageVo> queryDimRiseSchoolInfo(SearchRiseSchoolVo serchRiseSchoolVo);

    Integer queryDimRiseSchoolCount(SearchRiseSchoolVo serchRiseSchoolVo);

    RiseSchoolManageVo queryCurrentRiseSchoolInfo(Map<String, Object> params);
    RiseSchoolManageVo findSchoolById(Integer schoolId);
    //查询当前最大的学校编号
    String findSchoolNo();
    //判断学校名称是否重复
    Integer checkSchoolName(String schoolName);

    List<SysDictVo> queryProvince();
    List<SysDictVo> queryCity();
    List<SysDictVo> queryDistrict();
    String queryCodeName(String code);
}

package com.yuxin.wx.api.riseschool;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolInfoVo;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;

import java.util.List;
import java.util.Map;

/**
 * 学校信息
 */
public interface RiseSchoolManageService {
    /**
     * 插入学校信息
     * @param riseSchoolManageVo
     */
    void insertRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 根据不同条件查询学校信息
     * @param riseSchoolManageVo
     * @return
     */
    PageFinder<RiseSchoolManageVo> queryRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo);

    /**
     * 处理逻辑的插入
     * @param map
     */
    void insertRiseSchoolInfoAndUsers(Map map) throws Exception;

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
     * 查询字典值
     * @param map
     * @return
     */
    List<SysDictVo> queryRiseSchoolDict(Map map);
    List<SysDictVo> querySchoolName(Map map);

    /**
     * 根据不同条件查询学校信息
     * @param serchRiseSchoolVo
     * @return
     */
    PageFinder<RiseSchoolManageVo> queryDimRiseSchoolInfo(SearchRiseSchoolVo serchRiseSchoolVo);

    /**
     *查询当前小升初学校
     * @param params
     * @return
     */
    RiseSchoolManageVo queryCurrentRiseSchoolInfo(Map<String, Object> params);
    RiseSchoolManageVo findSchoolById(Integer schoolId);
    //查询当前最大的学校编号
    String findSchoolNo();
    //判断学校名称是否重复
    Integer checkSchoolName(String schoolName);
}

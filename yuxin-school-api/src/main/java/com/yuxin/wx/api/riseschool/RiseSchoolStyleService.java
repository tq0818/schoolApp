package com.yuxin.wx.api.riseschool;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolStyleVo;

import java.util.List;
import java.util.Map;

public interface RiseSchoolStyleService {
    /**
     * 添加学校风采信息
     * @param riseSchoolStyleVo
     */
    void insertRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo);

    /**
     * 更新学校风采信息
     * @param riseSchoolStyleVo
     */
    void updateRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo);

    /**
     * 查询学校风采信息
     * @param riseSchoolStyleVo
     * @return
     */
    PageFinder<RiseSchoolStyleVo> queryRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo);

    /**
     * 删除学校风采信息
     * @param id
     */
    void deleteRiseSchoolStyleById(Integer id);

    /**
     * 查询学校封面图
     * @param map
     * @return
     */
    RiseSchoolStyleVo queryRiseSchoolStyleById(Map map);
}

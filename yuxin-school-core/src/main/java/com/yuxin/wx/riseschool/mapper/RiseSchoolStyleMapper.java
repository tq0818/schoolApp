package com.yuxin.wx.riseschool.mapper;

import com.yuxin.wx.model.riseschool.RiseSchoolStyleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RiseSchoolStyleMapper {
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
    List<RiseSchoolStyleVo> queryRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo);

    /**
     * 查询学校风采总数
     * @param riseSchoolStyleVo
     * @return
     */
    Integer queryRiseSchoolStyleCount(RiseSchoolStyleVo riseSchoolStyleVo);

    /**
     * 删除学校风采信息
     * @param id
     */
    void deleteRiseSchoolStyleById(@Param("id") Integer id);

    /**
     * 查询学校封面
     * @param map
     * @return
     */
    RiseSchoolStyleVo queryRiseSchoolStyleById(Map map);
}

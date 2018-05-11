package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InsFeaturesVo;

import java.util.List;

public interface InsFeaturesMapper {

    /**
     * 插入图片
     * @param insFeaturesVo
     */
    void insert(InsFeaturesVo insFeaturesVo);

    /**
     * 查询所有图片
     * @return
     */
    List<InsFeaturesVo> findInsFeaturesVos(InsFeaturesVo insFeaturesVo);

    Integer findInsFeaturesVosCount(InsFeaturesVo insFeaturesVo);
}

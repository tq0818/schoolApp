package com.yuxin.wx.api.institution;

import com.yuxin.wx.model.institution.InsFeaturesVo;

import java.util.List;

public interface InsFeaturesService {
    /**
     * 插入图片
     * @param insFeaturesVo
     */
    void insert(InsFeaturesVo insFeaturesVo);

    /**
     * 查询所有图片
     * @return
     */
    List<InsFeaturesVo> findInsFeaturesVos();

    Integer findInsFeaturesVosCount();
}

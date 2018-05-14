package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.IndexRecommendVo;

public interface InstitutionRecommendService {
    /**
     * 获取首页列表推荐信息
     * @param typeId
     * @param status
     * @param insName
     * @param pageStart
     * @param pageSize
     * @return
     */
    PageFinder<IndexRecommendVo> getRecommend(Integer typeId,Integer status , String insName ,  Integer pageStart , Integer pageSize );

}

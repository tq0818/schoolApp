package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionRecommendService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.IndexRecommendVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstitutionRecommendServiceImpl extends BaseServiceImpl implements InstitutionRecommendService {

    @Override
    public PageFinder<IndexRecommendVo> getRecommend(Integer typeId, Integer status, String insName, Integer pageStart, Integer pageSize) {
        return null;
    }



}

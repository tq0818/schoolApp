package com.yuxin.wx.model.institution;

import com.yuxin.wx.common.BaseEntity;

public class InstitutionRelationVo extends BaseEntity{
    private Integer insId;
    private Integer oneLevelId;
    private Integer towLevelId;


    public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    public Integer getOneLevelId() {
        return oneLevelId;
    }

    public void setOneLevelId(Integer oneLevelId) {
        this.oneLevelId = oneLevelId;
    }

    public Integer getTowLevelId() {
        return towLevelId;
    }

    public void setTowLevelId(Integer towLevelId) {
        this.towLevelId = towLevelId;
    }
}

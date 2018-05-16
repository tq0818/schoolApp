package com.yuxin.wx.model.institution;

import com.yuxin.wx.common.BaseEntity;

public class InstitutionRelationVo extends BaseEntity{
    private Integer insId;
    private Integer oneLevelId;
    private Integer twoLevelId;


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

    public Integer getTwoLevelId() {
        return twoLevelId;
    }

    public void setTwoLevelId(Integer twoLevelId) {
        this.twoLevelId = twoLevelId;
    }
}

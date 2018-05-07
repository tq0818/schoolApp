package com.yuxin.wx.model.institution;

import java.math.BigDecimal;
import java.util.Date;

public class InstitutionLabelVo {

    private Integer relationId;//机构主键或者课程主键或者教师主键
    private String labelName;//标签名称
    private String imgUrl;//图片地址
    private String labelType;//标签类型 0 是系统标签 1是自定义 2是特色服务
    private Integer sourceFlag;//0 是机构 1 是课程 2 是教师
    private Date createTime;//创建时间
    private Date updateTime;//修改时间


    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public Integer getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(Integer sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

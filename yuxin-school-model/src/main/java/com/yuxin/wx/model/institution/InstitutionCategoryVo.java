package com.yuxin.wx.model.institution;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

public class InstitutionCategoryVo extends BaseEntity{

    private String codeName;//分类名称
    private Integer codeLevel;//分类级别(1是一级，2是二级)
    private String codeType;//分类的类型(0是机构分类，以后新增则需添加注释)
    private String imgUrl;//分类图片地址
    private Integer isEnable;//是否启用
    private Integer parentId;//分类上一级id，没有则为null
    private Integer firstRecommend;//移动端第一项项推荐(1是 0否)
    private Integer secondRecommend;//移动端第二项项推荐(1是 0否)
    private Integer thirdRecommend;//移动端第三项项推荐(1是 0否)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private String secondCate;//二级分类组装
    private Integer sort;   //排序
    private Integer sort3;  //首页列表排序

    /**
     * 更新主键id
     */
    private String ids;


    private String firstcodeName;
    private String secondcodeName;
    private String oneLevelId;
    private String twoLevelId;
    private Integer insId;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getFirstcodeName() {
        return firstcodeName;
    }

    public void setFirstcodeName(String firstcodeName) {
        this.firstcodeName = firstcodeName;
    }

    public String getSecondcodeName() {
        return secondcodeName;
    }

    public void setSecondcodeName(String secondcodeName) {
        this.secondcodeName = secondcodeName;
    }

    public String getOneLevelId() {
        return oneLevelId;
    }

    public void setOneLevelId(String oneLevelId) {
        this.oneLevelId = oneLevelId;
    }

    public String getTwoLevelId() {
        return twoLevelId;
    }

    public void setTwoLevelId(String twoLevelId) {
        this.twoLevelId = twoLevelId;
    }

    public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    public String getSecondCate() {
        return secondCate;
    }

    public void setSecondCate(String secondCate) {
        this.secondCate = secondCate;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(Integer codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFirstRecommend() {
        return firstRecommend;
    }

    public void setFirstRecommend(Integer firstRecommend) {
        this.firstRecommend = firstRecommend;
    }

    public Integer getSecondRecommend() {
        return secondRecommend;
    }

    public void setSecondRecommend(Integer secondRecommend) {
        this.secondRecommend = secondRecommend;
    }

    public Integer getThirdRecommend() {
        return thirdRecommend;
    }

    public void setThirdRecommend(Integer thirdRecommend) {
        this.thirdRecommend = thirdRecommend;
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


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort3() {
        return sort3;
    }

    public void setSort3(Integer sort3) {
        this.sort3 = sort3;
    }
}

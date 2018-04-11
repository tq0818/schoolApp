package com.yuxin.wx.vo.commodity;

import java.io.Serializable;
import java.util.Date;

public class ClassTypeInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//商品id
	private Integer commodityId;//商品id
	private String commodityName;
	private String coverUrl;
	private Integer classTypeId;
    private String overview; /* 商品概述 */
    private Double originalPrice; /* 商品原始价格 */
    private Double realPrice; /* 商品的真实价格（优惠后价格） */
    private Integer actualNum; /* 实际售卖数 */
    private Integer liveFlag; /* 是否属于直播标签，1:是；0：否 */
    private Integer videoFlag; /* 是否属于视频标签，1:是；0：否 */
    private String teacherName;
    private String schoolShortName;
    private Integer buyNumMax;
    private String iconLable;
    private String courseTime;
    private Date shelvesTime;
    private Integer isNew;//是否是最新课程 0 否 1 是
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public Integer getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}
	public Integer getVideoFlag() {
		return videoFlag;
	}
	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSchoolShortName() {
		return schoolShortName;
	}
	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}
	public Integer getBuyNumMax() {
		return buyNumMax;
	}
	public void setBuyNumMax(Integer buyNumMax) {
		this.buyNumMax = buyNumMax;
	}
	public String getIconLable() {
		return iconLable;
	}
	public void setIconLable(String iconLable) {
		this.iconLable = iconLable;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public Date getShelvesTime() {
		return shelvesTime;
	}
	public void setShelvesTime(Date shelvesTime) {
		this.shelvesTime = shelvesTime;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}

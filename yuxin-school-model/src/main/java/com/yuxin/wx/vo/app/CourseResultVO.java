package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页第二板块课程查询
 * @author admin
 *
 */
public class CourseResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer commodityId;//商品ID
	private String commodityName;//商品名称
	private String coverUrl;//商品图片
	private Float realPrice;//真是价格
	private Float originalPrice;//原始价格
	private Integer classTypeId;//课程ID
	private String description;//课程简介
	private String detailDesc;//课程描述
	private String iconLable;//标签
	private Date publishTime;//发布时间
	private Integer videoWatchCount;//观看次数
	private String teacherName;//教师姓名
	private String resume;//教师简介
	private String schoolName;//学校名称
	private String schoolShortName;//学校简称
	private String gradeName;//阶段
	private Integer actualNum;//实际人数
	private Integer categoryId;//课程分类Id
	private String overview;//商品概述
	private String subjectName;//学科
	private String courseTime;//课程开始时间结束时间，格式是 2011-01-01 11:02:02
	private Integer buyNumMax;//最大购买数
	private String teacherLevel;//教师级别
    private Integer isNew;//是否是最新课程 0 否 1 是
    private Date shelvesTime;//上架时间
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
	public Float getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Float realPrice) {
		this.realPrice = realPrice;
	}
	public Float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getIconLable() {
		return iconLable;
	}
	public void setIconLable(String iconLable) {
		this.iconLable = iconLable;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getVideoWatchCount() {
		return videoWatchCount;
	}
	public void setVideoWatchCount(Integer videoWatchCount) {
		this.videoWatchCount = videoWatchCount;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolShortName() {
		return schoolShortName;
	}
	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public Integer getBuyNumMax() {
		return buyNumMax;
	}
	public void setBuyNumMax(Integer buyNumMax) {
		this.buyNumMax = buyNumMax;
	}
	public String getTeacherLevel() {
		return teacherLevel;
	}
	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public Date getShelvesTime() {
		return shelvesTime;
	}
	public void setShelvesTime(Date shelvesTime) {
		this.shelvesTime = shelvesTime;
	}
	
}

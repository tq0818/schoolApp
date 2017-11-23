package com.yuxin.wx.vo.commodity;

import com.yuxin.wx.common.BaseEntity;

public class CommodityDto extends BaseEntity {
	private String classTypeId;
	/**
	 * 课程图片
	 */
	private String coverUrl;
	/**
	 * 课程名称
	 */
	private String name;
	/**
	 * 类别名称
	 */
	private String categoryName;
	/**
	 * 学段名称
	 */
	private String gradeName;
	/**
	 * 学科名称
	 */
	private String subjectName;
	/**
	 * 知识点专题
	 */
	private String knowProName;
	/**
	 * 知识点名称
	 */
	private String knowName;
	/**
	 * 阶段名称
	 */
	private String stageName;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 上架时间
	 */
	private String shelvesTime;
	/**
	 * 直播时间
	 */
	private String lessonTime;
	/**
	 * 学习人数
	 */
	private String actualNum;
	/**
	 * 实际价格
	 */
	private String realPrice;
	/**
	 * 售卖价格
	 */
	private String salePrice;
	
	private String shelves_id;
	
	public String getShelves_id() {
		return shelves_id;
	}
	public void setShelves_id(String shelves_id) {
		this.shelves_id = shelves_id;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getKnowProName() {
		return knowProName;
	}
	public void setKnowProName(String knowProName) {
		this.knowProName = knowProName;
	}
	public String getKnowName() {
		return knowName;
	}
	public void setKnowName(String knowName) {
		this.knowName = knowName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getShelvesTime() {
		return shelvesTime;
	}
	public void setShelvesTime(String shelvesTime) {
		this.shelvesTime = shelvesTime;
	}
	public String getLessonTime() {
		return lessonTime;
	}
	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
	public String getActualNum() {
		return actualNum;
	}
	public void setActualNum(String actualNum) {
		this.actualNum = actualNum;
	}
	public String getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(String classTypeId) {
		this.classTypeId = classTypeId;
	}
	
	
}

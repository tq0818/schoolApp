package com.yuxin.wx.model.app;



import java.sql.Timestamp;
import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/***
 * 已上架课程列表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ShelvesCourse extends BaseEntity{
	private String categoryName;//课程分类名称
	private String imgUrl;//课程图片
	private String name;//课程名称
	private String gradeName;//学段
	private String subjectName;//学科
	private String knowledgeName;//知识点专题
	private String knowledgeProName;//知识点
	private String stageName;//阶段
	private String typeCode;//类型
	private Date shelvesTime;//上架时间
	private Date reserveTime;//直播时间
	private String reserveHour;//直播分钟
	private Integer buyNum;//学习人数
	private float salePrice;//出售价格
	private float appprice;//实际价格
	private Integer isShelves;//是否上架，1上架，0未上架
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}
	public String getKnowledgeProName() {
		return knowledgeProName;
	}
	public void setKnowledgeProName(String knowledgeProName) {
		this.knowledgeProName = knowledgeProName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Date getShelvesTime() {
		return shelvesTime;
	}
	public void setShelvesTime(Date shelvesTime) {
		this.shelvesTime = shelvesTime;
	}
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	public float getAppprice() {
		return appprice;
	}
	public void setAppprice(float appprice) {
		this.appprice = appprice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getReserveHour() {
		return reserveHour;
	}
	public void setReserveHour(String reserveHour) {
		this.reserveHour = reserveHour;
	}
	public Integer getIsShelves() {
		return isShelves;
	}
	public void setIsShelves(Integer isShelves) {
		this.isShelves = isShelves;
	}
	
	
}

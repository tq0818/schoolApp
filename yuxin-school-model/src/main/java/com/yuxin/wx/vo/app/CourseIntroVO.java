package com.yuxin.wx.vo.app;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.system.SysConfigTeacher;
/**
 * 课程介绍VO
 * @author admin
 *
 */
public class CourseIntroVO extends BaseEntity {
	String detaildesc;//课程描述
	Integer actulnum;//该课程学习人数
	Integer stuCount;//所有人数
	String teacherName;
	String schoolName;
	String subject;
	String picUrl;
	String flag;//是否登录
	Double realPrice;
	Integer classTypeId;
	String isCollect;
	String rspFlag;
	String imageUrl;
	String coverUrl;
	Integer isComment;//是否已经评论，0为未评论，否则为已经评论
	List<SysConfigTeacher> teachers;
	String descUrl;//课程介绍连接
	public String getDetaildesc() {
		return detaildesc;
	}
	public void setDetaildesc(String detaildesc) {
		this.detaildesc = detaildesc;
	}
	public Integer getActulnum() {
		return actulnum;
	}
	public void setActulnum(Integer actulnum) {
		this.actulnum = actulnum;
	}
	public Integer getStuCount() {
		return stuCount;
	}
	public void setStuCount(Integer stuCount) {
		this.stuCount = stuCount;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
	public String getRspFlag() {
		return rspFlag;
	}
	public void setRspFlag(String rspFlag) {
		this.rspFlag = rspFlag;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public List<SysConfigTeacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<SysConfigTeacher> teachers) {
		this.teachers = teachers;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	
}

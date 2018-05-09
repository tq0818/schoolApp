package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.List;

/**
 * 线下课程
 * @author hello
 *
 */
public class CourseInfoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer courseInfoId;	//	课程id
	private String courseInfoName;		//课程名称
	private String courseInfoImgUrl;	//	封面图片地址
	private String courseInfoIntro;		//课程介绍
	private List<String> courseInfoSign;		//课程标签
	private Float courseInfoPrice;		//课程价格
	private Integer courseInfoRemain;		//剩余名额
	private Integer isSupportAppoint;		//是否支持预约(0否1是)
	public Integer getCourseInfoId() {
		return courseInfoId;
	}
	public void setCourseInfoId(Integer courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
	public String getCourseInfoName() {
		return courseInfoName;
	}
	public void setCourseInfoName(String courseInfoName) {
		this.courseInfoName = courseInfoName;
	}
	public String getCourseInfoImgUrl() {
		return courseInfoImgUrl;
	}
	public void setCourseInfoImgUrl(String courseInfoImgUrl) {
		this.courseInfoImgUrl = courseInfoImgUrl;
	}
	public String getCourseInfoIntro() {
		return courseInfoIntro;
	}
	public void setCourseInfoIntro(String courseInfoIntro) {
		this.courseInfoIntro = courseInfoIntro;
	}
	public List<String> getCourseInfoSign() {
		return courseInfoSign;
	}
	public void setCourseInfoSign(List<String> courseInfoSign) {
		this.courseInfoSign = courseInfoSign;
	}
	public Float getCourseInfoPrice() {
		return courseInfoPrice;
	}
	public void setCourseInfoPrice(Float courseInfoPrice) {
		this.courseInfoPrice = courseInfoPrice;
	}
	public Integer getCourseInfoRemain() {
		return courseInfoRemain;
	}
	public void setCourseInfoRemain(Integer courseInfoRemain) {
		this.courseInfoRemain = courseInfoRemain;
	}
	public Integer getIsSupportAppoint() {
		return isSupportAppoint;
	}
	public void setIsSupportAppoint(Integer isSupportAppoint) {
		this.isSupportAppoint = isSupportAppoint;
	}
	
}

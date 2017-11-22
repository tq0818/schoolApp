package com.yuxin.wx.vo.app;

import java.sql.Date;

/**
 * 用户所购买的直播的课程
 * @author admin
 *
 */
public class UserBuyedLiveCourseVO {
	private String iconLable;//标签
	private String courseName;//课程名
	private String isStatus;//课程是否结束
	private Date date ;//日期
	private Integer classTypeId;//课程ID
	private Integer userId;//用户ID
	private Integer comId;//商品ID
	public String getIconLable() {
		return iconLable;
	}
	public void setIconLable(String iconLable) {
		this.iconLable = iconLable;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}

}

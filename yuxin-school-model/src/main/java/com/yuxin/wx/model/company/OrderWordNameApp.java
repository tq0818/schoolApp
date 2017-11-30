package com.yuxin.wx.model.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;
/**
 * 订阅文章_订阅统计信息
 * @author cxl
 *
 */
@SuppressWarnings("serial")
public class OrderWordNameApp extends BaseEntity {
	 
	private String telNum;
	private String userName;
	private Integer isSignUp;
	private Integer isAgree;
	private Date createTime;
	private Integer stuMsgId;
	private Integer studentId;
	
	public OrderWordNameApp(){
		
	}

	public OrderWordNameApp(String telNum, String userName, Integer isSignUp,
			Integer isAgree, Date createTime, Integer stuMsgId,
			Integer studentId) {
		setId(id);
		this.telNum = telNum;
		this.userName = userName;
		this.isSignUp = isSignUp;
		this.isAgree = isAgree;
		this.createTime = createTime;
		this.stuMsgId = stuMsgId;
		this.studentId = studentId;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsSignUp() {
		return isSignUp;
	}

	public void setIsSignUp(Integer isSignUp) {
		this.isSignUp = isSignUp;
	}

	public Integer getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStuMsgId() {
		return stuMsgId;
	}

	public void setStuMsgId(Integer stuMsgId) {
		this.stuMsgId = stuMsgId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
}

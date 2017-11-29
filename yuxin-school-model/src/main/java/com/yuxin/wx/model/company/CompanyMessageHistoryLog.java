package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

@SuppressWarnings("serial")
public class CompanyMessageHistoryLog extends BaseEntity {
	
	private String	userId;		/* 接收者用户id */ 
	private String	mobile;		/* 接收者手机号 */ 
	private Date	sendTime;			/* 发送时间 */ 
	private String	content;			/* 发送内容 */ 
	private	Integer	companyId;			/* 公司id*/
	private	Integer	schoolId;			/* 学校id*/
	private Integer	messageId;			/* 学院通知id*/
	private Integer status;	

	// Constructor
	public CompanyMessageHistoryLog() {
	}


	public CompanyMessageHistoryLog(String userId, String mobile,
			Date sendTime, String content, Integer companyId, Integer schoolId,
			Integer messageId,Integer status) {
		setId(id);
		this.userId = userId;
		this.mobile = mobile;
		this.sendTime = sendTime;
		this.content = content;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.messageId = messageId;
		this.status=status;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Date getSendTime() {
		return sendTime;
	}


	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public Integer getMessageId() {
		return messageId;
	}


	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	 
}

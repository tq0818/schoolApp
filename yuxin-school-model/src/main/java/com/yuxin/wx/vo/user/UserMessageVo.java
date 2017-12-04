package com.yuxin.wx.vo.user;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.vo.company.CompanyStudentMessageVo;

public class UserMessageVo extends BaseEntity {

	private Integer	userId;		 /* 用户表id */ 
	private Integer	messageId;		 /* 学员通知表id */ 
	private Integer	readFlag;		 /* 阅读标记（1：已读；0：未读） */
	private String title;   /*标题*/
	private String msgType; /*消息类型*/
	private String content; /*消息内容*/

	private String creatTime;/*发送时间*/
	private String imgUrl;/*封面图片*/
	private String h5Url;
	private String contentText;/*通知纯文本*/
	private String erroInfo;
	private String signupVote;/*报名或者投票 0报名 1投票*/
	private String signupVoteUrl;/*报名或者投票连接*/
	private String createDate;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSignupVoteUrl() {
		return signupVoteUrl;
	}

	public void setSignupVoteUrl(String signupVoteUrl) {
		this.signupVoteUrl = signupVoteUrl;
	}

	public String getSignupVote() {
		return signupVote;
	}

	public void setSignupVote(String signupVote) {
		this.signupVote = signupVote;
	}

	public String getErroInfo() {
		return erroInfo;
	}

	public void setErroInfo(String erroInfo) {
		this.erroInfo = erroInfo;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private CompanyStudentMessageVo message;	/* 信息标题*/

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

	public CompanyStudentMessageVo getMessage() {
		return message;
	}

	public void setMessage(CompanyStudentMessageVo message) {
		this.message = message;
	}
	
}

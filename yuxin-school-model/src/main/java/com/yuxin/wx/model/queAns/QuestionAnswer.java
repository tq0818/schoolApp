package com.yuxin.wx.model.queAns;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionAnswer
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QuestionAnswer extends BaseEntity {
	
	
	private String	answerDesc;		 /* 回答内容 */ 
	private Integer	answerId;		 /* 一级回复ID */ 
	private Integer	questionId;		 /* 回答对应的问题Id */ 
	private Integer	userId;		 /* 学员Id（如果是学生对应： user_front表的ID。 如果是老师、管理员对应：users表的ID） */ 
	private String	answerType;		 /* 回答类型（1. 学生、2.老师、3.管理员） */ 
	private Integer	answerLevel;		 /* 回复等级（1：问题的回答  2. 评论） */ 
	private Integer	commentCount;		 /*  评论的数量 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	delFlag;		 /* 是否删除 */
	private Integer parentId;		/* 二级回复Id*/
	private Integer replyUserId;	/*被回复人id*/
	private String replyUserName;	/*被回复人name*/
	private Integer readFlag;		/*阅读标记：0 未读，1 已读*/
	private String replyUserType;	/*被回复人类型*/
	
	private String name;			/*回复人*/
	private String imgUrl;			/*头像地址*/
	private String times;			/*时间*/
	private int likeanswer;     /*点赞数 */
	private Integer isThumbs;//是否被点赞
	private Integer th_user_id;//点赞用户
	private Integer thumbsFlag;//0未点赞，1为点赞
	private Integer isAdopt;//该用户的回答是否被采纳
	private Integer adoptFlag; //记录采纳数量
	private Integer isAccept;//该回答是否被采纳
	private Integer questionscore;//问题奖励积分
	// Constructor
	public QuestionAnswer() {
	}
	
	/**
	 * full Constructor
	 */
	

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为QuestionAnswer可以实现连缀设置属性
	
	public String getAnswerDesc() {
		return answerDesc;
	}

	public QuestionAnswer(String answerDesc, Integer answerId, Integer questionId, Integer userId, String answerType,
	        Integer answerLevel, Integer commentCount, Date createTime, Integer delFlag, Integer parentId, Integer replyUserId,
	        String replyUserName, Integer readFlag, String replyUserType, String name, String imgurl, String times,
	        int likeanswer, Integer isThumbs, Integer th_user_id, Integer thumbsFlag, Integer isAdopt, Integer adoptFlag,
	        Integer isAccept, Integer questionscore) {
		super();
		this.answerDesc = answerDesc;
		this.answerId = answerId;
		this.questionId = questionId;
		this.userId = userId;
		this.answerType = answerType;
		this.answerLevel = answerLevel;
		this.commentCount = commentCount;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.parentId = parentId;
		this.replyUserId = replyUserId;
		this.replyUserName = replyUserName;
		this.readFlag = readFlag;
		this.replyUserType = replyUserType;
		this.name = name;
		this.imgurl = imgurl;
		this.times = times;
		this.likeanswer = likeanswer;
		this.isThumbs = isThumbs;
		this.th_user_id = th_user_id;
		this.thumbsFlag = thumbsFlag;
		this.isAdopt = isAdopt;
		this.adoptFlag = adoptFlag;
		this.isAccept = isAccept;
		this.questionscore = questionscore;
	}

	public QuestionAnswer setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
		return this;
	}
	
	
	public Integer getAnswerId() {
		return answerId;
	}

	public QuestionAnswer setAnswerId(Integer answerId) {
		this.answerId = answerId;
		return this;
	}
	
	
	public Integer getQuestionId() {
		return questionId;
	}

	public QuestionAnswer setQuestionId(Integer questionId) {
		this.questionId = questionId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public QuestionAnswer setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getAnswerType() {
		return answerType;
	}

	public QuestionAnswer setAnswerType(String answerType) {
		this.answerType = answerType;
		return this;
	}
	
	
	public Integer getAnswerLevel() {
		return answerLevel;
	}

	public QuestionAnswer setAnswerLevel(Integer answerLevel) {
		this.answerLevel = answerLevel;
		return this;
	}
	
	
	public Integer getCommentCount() {
		return commentCount;
	}

	public QuestionAnswer setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QuestionAnswer setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public QuestionAnswer setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	




	@Override
	public String toString() {
		return "QuestionAnswer [answerDesc=" + answerDesc + ", answerId=" + answerId + ", questionId=" + questionId
		        + ", userId=" + userId + ", answerType=" + answerType + ", answerLevel=" + answerLevel + ", commentCount="
		        + commentCount + ", createTime=" + createTime + ", delFlag=" + delFlag + ", parentId=" + parentId
		        + ", replyUserId=" + replyUserId + ", replyUserName=" + replyUserName + ", readFlag=" + readFlag
		        + ", replyUserType=" + replyUserType + ", name=" + name + ", imgUrl=" + imgUrl + ", times=" + times
		        + ", likeanswer=" + likeanswer + ", isThumbs=" + isThumbs + ", th_user_id=" + th_user_id + ", thumbsFlag="
		        + thumbsFlag + ", isAdopt=" + isAdopt + ", adoptFlag=" + adoptFlag + ", isAccept=" + isAccept
		        + ", questionscore=" + questionscore + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getReplyUserType() {
		return replyUserType;
	}

	public void setReplyUserType(String replyUserType) {
		this.replyUserType = replyUserType;
	}

	public int getLikeanswer() {
		return likeanswer;
	}

	public void setLikeanswer(int likeanswer) {
		this.likeanswer = likeanswer;
	}

	public Integer getIsThumbs() {
		return isThumbs;
	}

	public void setIsThumbs(Integer isThumbs) {
		this.isThumbs = isThumbs;
	}

	public Integer getTh_user_id() {
		return th_user_id;
	}

	public void setTh_user_id(Integer th_user_id) {
		this.th_user_id = th_user_id;
	}

	public Integer getThumbsFlag() {
		return thumbsFlag;
	}

	public void setThumbsFlag(Integer thumbsFlag) {
		this.thumbsFlag = thumbsFlag;
	}

	public Integer getIsAdopt() {
		return isAdopt;
	}

	public void setIsAdopt(Integer isAdopt) {
		this.isAdopt = isAdopt;
	}

	
	public Integer getAdoptFlag() {
		return adoptFlag;
	}

	
	public void setAdoptFlag(Integer adoptFlag) {
		this.adoptFlag = adoptFlag;
	}

	
	public Integer getIsAccept() {
		return isAccept;
	}

	
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}

	
	public Integer getQuestionscore() {
		return questionscore;
	}

	
	public void setQuestionScore(Integer questionscore) {
		this.questionscore = questionscore;
	}
	
}

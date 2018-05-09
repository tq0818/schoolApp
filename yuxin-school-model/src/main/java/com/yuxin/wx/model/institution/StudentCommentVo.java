package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.Date;

/**
 * 学员评论
 * @author hello
 *
 */
public class StudentCommentVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer commentId;//		评论id
	private Integer commentUserId;//		评论者的userId
	private String commentImgUrl;//		评论者头像地址
	private Float commentScore;//		评论等级
	private String commentContent;//		评论内容
	private Date commtentTime;//		评论时间
	private Integer commentThumpCount;//		评论点赞数
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentImgUrl() {
		return commentImgUrl;
	}
	public void setCommentImgUrl(String commentImgUrl) {
		this.commentImgUrl = commentImgUrl;
	}
	public Float getCommentScore() {
		return commentScore;
	}
	public void setCommentScore(Float commentScore) {
		this.commentScore = commentScore;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommtentTime() {
		return commtentTime;
	}
	public void setCommtentTime(Date commtentTime) {
		this.commtentTime = commtentTime;
	}
	public Integer getCommentThumpCount() {
		return commentThumpCount;
	}
	public void setCommentThumpCount(Integer commentThumpCount) {
		this.commentThumpCount = commentThumpCount;
	}
	
}

package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.Date;

public class CommentThumpApp implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	 private Integer userId;
	 private Integer commentId;
	 private Integer isThump;
	 private Date createTime;
	 private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getIsThump() {
		return isThump;
	}
	public void setIsThump(Integer isThump) {
		this.isThump = isThump;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	 
}

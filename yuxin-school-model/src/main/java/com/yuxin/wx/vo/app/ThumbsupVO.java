package com.yuxin.wx.vo.app;

import java.io.Serializable;

public class ThumbsupVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id ;
	private Integer userId;
	private Integer answerId;
	private Integer isThumbs;
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
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public Integer getIsThumbs() {
		return isThumbs;
	}
	public void setIsThumbs(Integer isThumbs) {
		this.isThumbs = isThumbs;
	}
	
}

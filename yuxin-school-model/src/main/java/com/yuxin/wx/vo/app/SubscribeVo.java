package com.yuxin.wx.vo.app;

import java.util.Date;

/**
 * 订阅列表
 * @author admin
 *
 */
public class SubscribeVo {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 介绍
	 */
	private String introduce;
	/**
	 * 推送时间
	 */
	private Date time;
	/**
	 * 图片地址
	 */
	private String picUrl;
	/**
	 * 跳转html页面地址
	 */
	private String htmlUrl;
	/**
	 * 预留字段
	 */
	private String id;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

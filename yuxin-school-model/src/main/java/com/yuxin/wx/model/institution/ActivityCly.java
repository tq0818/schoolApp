package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 * @author hello
 *
 */
public class ActivityCly implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer activityId;	
	private String activityTitle;	
	private String activityIntro;	
	private String activityImgUrl;	
	private String activityUrl;	
	private String activityTime;
	private Date startTime;
	private Date endTime;
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityIntro() {
		return activityIntro;
	}
	public void setActivityIntro(String activityIntro) {
		this.activityIntro = activityIntro;
	}
	public String getActivityImgUrl() {
		return activityImgUrl;
	}
	public void setActivityImgUrl(String activityImgUrl) {
		this.activityImgUrl = activityImgUrl;
	}
	public String getActivityUrl() {
		return activityUrl;
	}
	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}	
	
}

package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 存放ccid和ccuserId
 * @author admin
 *
 */
public class CcResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ccId;
	private String ccUserId;
	private String ccApiKey;
	private Integer videoClassTypeId;//录播课程Id
	private Integer lectureId;
	//上次观看时长
	private Long preTime;
	public String getCcId() {
		return ccId;
	}
	public void setCcId(String ccId) {
		this.ccId = ccId;
	}
	public String getCcUserId() {
		return ccUserId;
	}
	public void setCcUserId(String ccUserId) {
		this.ccUserId = ccUserId;
	}
	public String getCcApiKey() {
		return ccApiKey;
	}
	public void setCcApiKey(String ccApiKey) {
		this.ccApiKey = ccApiKey;
	}
	public Integer getVideoClassTypeId() {
		return videoClassTypeId;
	}
	public void setVideoClassTypeId(Integer videoClassTypeId) {
		this.videoClassTypeId = videoClassTypeId;
	}
	public Integer getLectureId() {
		return lectureId;
	}
	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}
	public Long getPreTime() {
		return preTime;
	}
	public void setPreTime(Long preTime) {
		this.preTime = preTime;
	}
	
}

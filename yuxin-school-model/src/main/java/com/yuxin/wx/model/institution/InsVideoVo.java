package com.yuxin.wx.model.institution;
/**
 * 视频相关信息
 * @author hello
 *
 */
public class InsVideoVo {
	private String ccId	;
	private String ccUserId	; 
	private String ccApiKey	;
	private Integer watchCount;
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
	public Integer getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	} 
	
}

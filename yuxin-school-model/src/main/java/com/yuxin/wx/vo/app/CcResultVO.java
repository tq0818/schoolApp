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

}

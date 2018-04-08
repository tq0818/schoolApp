package com.yuxin.wx.vo.app;

import java.io.Serializable;
import java.util.Date;

public class MobileSignVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//序号
	private String mobileSign;//手机序列，唯一标识
	private Date createTime;
	private Date updateTime;
	private Integer isLogin;//是否登录过，0 否，1登录
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileSign() {
		return mobileSign;
	}
	public void setMobileSign(String mobileSign) {
		this.mobileSign = mobileSign;
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
	public Integer getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
	
}

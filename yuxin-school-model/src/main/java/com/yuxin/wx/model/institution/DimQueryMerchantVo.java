package com.yuxin.wx.model.institution;

import java.util.Date;

import com.yuxin.wx.common.BaseModel;

public class DimQueryMerchantVo extends BaseModel {
	private Integer dealStatus;//处理状态
	private Date leftTime;//左边录入的时间
	private Date rightTime;//右边录入的时间
	private String mobile;//手机号码
	private String insName;//机构名称
	private Integer dimFlag;//0表示带入的是左边的条件,1表示带入的是右边的条件
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	public Date getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Date leftTime) {
		this.leftTime = leftTime;
	}
	public Date getRightTime() {
		return rightTime;
	}
	public void setRightTime(Date rightTime) {
		this.rightTime = rightTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public Integer getDimFlag() {
		return dimFlag;
	}
	public void setDimFlag(Integer dimFlag) {
		this.dimFlag = dimFlag;
	}
	
}

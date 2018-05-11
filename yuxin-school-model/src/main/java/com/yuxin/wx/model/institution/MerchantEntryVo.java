package com.yuxin.wx.model.institution;

import java.util.Date;

import com.yuxin.wx.common.BaseModel;
/**
 * 商家入驻申请
 * @author hello
 *
 */
public class MerchantEntryVo extends BaseModel{
	private Integer id;
	private String insName;
	private String mobile;
	private Integer dealStatus;
	private String note;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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

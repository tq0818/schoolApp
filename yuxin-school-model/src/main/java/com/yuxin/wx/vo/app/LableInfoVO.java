package com.yuxin.wx.vo.app;

import java.io.Serializable;

public class LableInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String labName;
	private Integer labType;
	private Integer labStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public Integer getLabType() {
		return labType;
	}
	public void setLabType(Integer labType) {
		this.labType = labType;
	}
	public Integer getLabStatus() {
		return labStatus;
	}
	public void setLabStatus(Integer labStatus) {
		this.labStatus = labStatus;
	}
	
	
	
}

package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 四大分类、学段、学科、知识点结果集
 * @author admin
 *
 */
public class SysDictAppVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sysDictAppId;//四大分类、学段、学科、知识点的ID
	private String sysDictAppName;//四大分类、学段、学科、知识点的意思
	private String sysDictAppCode;
	private String sysDictAppType;
	public Integer getSysDictAppId() {
		return sysDictAppId;
	}
	public void setSysDictAppId(Integer sysDictAppId) {
		this.sysDictAppId = sysDictAppId;
	}
	public String getSysDictAppName() {
		return sysDictAppName;
	}
	public void setSysDictAppName(String sysDictAppName) {
		this.sysDictAppName = sysDictAppName;
	}
	public String getSysDictAppCode() {
		return sysDictAppCode;
	}
	public void setSysDictAppCode(String sysDictAppCode) {
		this.sysDictAppCode = sysDictAppCode;
	}
	public String getSysDictAppType() {
		return sysDictAppType;
	}
	public void setSysDictAppType(String sysDictAppType) {
		this.sysDictAppType = sysDictAppType;
	}
	
}

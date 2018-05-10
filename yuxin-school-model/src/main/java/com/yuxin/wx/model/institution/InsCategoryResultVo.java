package com.yuxin.wx.model.institution;

/**
 * 分类的结果集
 * @author hello
 *
 */
public class InsCategoryResultVo {
	private Integer parentId;//一级分类的id
	private String parentName;//一级分类的名称
	private Integer subId;//二级分类id
	private String subName;//二级分类的名称
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
}

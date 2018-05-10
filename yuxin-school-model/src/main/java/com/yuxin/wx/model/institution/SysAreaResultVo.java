package com.yuxin.wx.model.institution;

/**
 * 区域(市区)
 * @author hello
 *
 */
public class SysAreaResultVo {
	private String itemCode;//子集的code
	private String parentCode;//父类的code
	private String parentName;//父类的名称
	private String itemName;//子集的名称
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}

package com.yuxin.wx.model.institution;

import java.util.List;

/**
 * 区域
 * @author hello
 *
 */
public class SysAreaVo {
	private String itemCode	;//	区域编码
	private String itemName;//		市区的名称
	private String itemType	;//	编码分类(省PROVINCE、市CITY、区AREA)
	private Integer isItemSub;//		0不存在下级，1是存在(存在再往下解析)
	private List<SysAreaVo> sysAreaVoSubList;//		省市的下级结果集
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getIsItemSub() {
		return isItemSub;
	}
	public void setIsItemSub(Integer isItemSub) {
		this.isItemSub = isItemSub;
	}
	public List<SysAreaVo> getSysAreaVoSubList() {
		return sysAreaVoSubList;
	}
	public void setSysAreaVoSubList(List<SysAreaVo> sysAreaVoSubList) {
		this.sysAreaVoSubList = sysAreaVoSubList;
	}
	
}

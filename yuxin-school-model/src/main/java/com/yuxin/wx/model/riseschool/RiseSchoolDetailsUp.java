package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;

/**
 * 	学校详情及升学情况
 */
public class RiseSchoolDetailsUp extends BaseEntity{
    /**
     * 字典编码
     */
    private String itemCode;
    /**
     * 字典编码名称
     */
    private String itemName;
    /**
     * 内容
     */
    private String itemDiscrible;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 对应的学校id
     */
    private String riseSchoolId;
    
    
    
    
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDiscrible() {
		return itemDiscrible;
	}
	public void setItemDiscrible(String itemDiscrible) {
		this.itemDiscrible = itemDiscrible;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRiseSchoolId() {
		return riseSchoolId;
	}
	public void setRiseSchoolId(String riseSchoolId) {
		this.riseSchoolId = riseSchoolId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		return "RiseSchoolDetailsUp [itemCode=" + itemCode + ", itemDiscrible=" + itemDiscrible + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", riseSchoolId=" + riseSchoolId + "]";
	}
    
}

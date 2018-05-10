package com.yuxin.wx.model.institution;
/**
 * 机构模块列表传参
 * @author hello
 *
 */
public class InstitutionModuleVo {
	private String itemType	;//编码分类(市CITY、区DISTRICT)	
	private String itemCode	;//省市区编码	
	private Integer InstitutionClyId	;//机构分类id	
	private Integer hotFlag	;//0人气最高 1评论最多	
	private Integer selectFlag;//	0认证机构 1预约礼包	
	private Integer page;//	分页(从0开始)	
	private Integer firstSize;//从第几条开始取数据
	private Integer endSize;//取多少条数据
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Integer getInstitutionClyId() {
		return InstitutionClyId;
	}
	public void setInstitutionClyId(Integer institutionClyId) {
		InstitutionClyId = institutionClyId;
	}
	public Integer getHotFlag() {
		return hotFlag;
	}
	public void setHotFlag(Integer hotFlag) {
		this.hotFlag = hotFlag;
	}
	public Integer getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(Integer selectFlag) {
		this.selectFlag = selectFlag;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getFirstSize() {
		return firstSize;
	}
	public void setFirstSize(Integer firstSize) {
		this.firstSize = firstSize;
	}
	public Integer getEndSize() {
		return endSize;
	}
	public void setEndSize(Integer endSize) {
		this.endSize = endSize;
	}

}

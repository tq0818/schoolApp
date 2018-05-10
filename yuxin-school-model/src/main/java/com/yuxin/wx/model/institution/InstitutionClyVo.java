package com.yuxin.wx.model.institution;

import java.util.List;
/**
 * 机构分类下拉数据
 * @author hello
 *
 */
public class InstitutionClyVo {
	private Integer institutionClyId;//		机构分类id下拉
	private String institutionClyName;//		机构分类名称
	private Integer institutionLevel;//		(1是一级分类，2是二级分类)
	private Integer isInstitutionSub;//		是否存在下级(0不存在 1 存在)
	private List<InstitutionClyVo> institutionSubList;//		下级结果集
	public Integer getInstitutionClyId() {
		return institutionClyId;
	}
	public void setInstitutionClyId(Integer institutionClyId) {
		this.institutionClyId = institutionClyId;
	}
	public String getInstitutionClyName() {
		return institutionClyName;
	}
	public void setInstitutionClyName(String institutionClyName) {
		this.institutionClyName = institutionClyName;
	}
	public Integer getInstitutionLevel() {
		return institutionLevel;
	}
	public void setInstitutionLevel(Integer institutionLevel) {
		this.institutionLevel = institutionLevel;
	}
	public Integer getIsInstitutionSub() {
		return isInstitutionSub;
	}
	public void setIsInstitutionSub(Integer isInstitutionSub) {
		this.isInstitutionSub = isInstitutionSub;
	}
	public List<InstitutionClyVo> getInstitutionSubList() {
		return institutionSubList;
	}
	public void setInstitutionSubList(List<InstitutionClyVo> institutionSubList) {
		this.institutionSubList = institutionSubList;
	}
	
}

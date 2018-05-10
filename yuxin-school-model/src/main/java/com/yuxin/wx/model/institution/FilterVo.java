package com.yuxin.wx.model.institution;
/**
 * 筛选
 * @author hello
 *
 */
public class FilterVo {
	private Integer filterId;//		筛选的id
	private String filterName;//		筛选的名称
	public Integer getFilterId() {
		return filterId;
	}
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
}

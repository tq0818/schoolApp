package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;

/**
 * 学校动态
 */
@SuppressWarnings("serial")
public class RiseSchoolDynamicVo extends BaseEntity{
    private String tittle;//学校动态标题
    private String content;//学校动态内容
    private String createTime;//学校动态创建时间
    private String updateTime;//学校动态修改时间
    private Integer riseSchoolId;//学校动态对应学校id
	
    
    public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getRiseSchoolId() {
		return riseSchoolId;
	}
	public void setRiseSchoolId(Integer riseSchoolId) {
		this.riseSchoolId = riseSchoolId;
	}
}

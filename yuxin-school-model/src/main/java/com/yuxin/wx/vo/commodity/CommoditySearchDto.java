package com.yuxin.wx.vo.commodity;

import com.yuxin.wx.common.BaseEntity;

public class CommoditySearchDto extends BaseEntity {
	private String gradeid;
    private String subjectid;
    private String knowledgeProid;
    private String knowledgeid;
    private String typeCode;
    private String stageid;
    private String categoryid;
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getKnowledgeProid() {
		return knowledgeProid;
	}
	public void setKnowledgeProid(String knowledgeProid) {
		this.knowledgeProid = knowledgeProid;
	}
	public String getKnowledgeid() {
		return knowledgeid;
	}
	public void setKnowledgeid(String knowledgeid) {
		this.knowledgeid = knowledgeid;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getStageid() {
		return stageid;
	}
	public void setStageid(String stageid) {
		this.stageid = stageid;
	}
    
}

package com.yuxin.wx.vo.app;

import java.io.Serializable;

public class SelectCourseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String timeId;
	private String categoryId;
	private String gradeId;
	private String subjectId;
	private String knowledgeProId;
	private String knowledgeId;
	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getKnowledgeProId() {
		return knowledgeProId;
	}
	public void setKnowledgeProId(String knowledgeProId) {
		this.knowledgeProId = knowledgeProId;
	}
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	
}

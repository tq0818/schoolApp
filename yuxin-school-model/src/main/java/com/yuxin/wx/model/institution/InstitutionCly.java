package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.List;

/**
 * 机构分类
 * @author hello
 *
 */
public class InstitutionCly implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer institutionId;	
	private String institutionName;	
	private Float institutionScore;	
	private String institutionArea;	
	private List<String> institutionSign;	
	private String institutionImgUrl;
	private String reServationPackage;
	private String courseInfoName;
	private Integer isCertified;
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public Float getInstitutionScore() {
		return institutionScore;
	}
	public void setInstitutionScore(Float institutionScore) {
		this.institutionScore = institutionScore;
	}
	public String getInstitutionArea() {
		return institutionArea;
	}
	public void setInstitutionArea(String institutionArea) {
		this.institutionArea = institutionArea;
	}
	
	public List<String> getInstitutionSign() {
		return institutionSign;
	}
	public void setInstitutionSign(List<String> institutionSign) {
		this.institutionSign = institutionSign;
	}
	public String getInstitutionImgUrl() {
		return institutionImgUrl;
	}
	public void setInstitutionImgUrl(String institutionImgUrl) {
		this.institutionImgUrl = institutionImgUrl;
	}
	public String getReServationPackage() {
		return reServationPackage;
	}
	public void setReServationPackage(String reServationPackage) {
		this.reServationPackage = reServationPackage;
	}
	public String getCourseInfoName() {
		return courseInfoName;
	}
	public void setCourseInfoName(String courseInfoName) {
		this.courseInfoName = courseInfoName;
	}
	public Integer getIsCertified() {
		return isCertified;
	}
	public void setIsCertified(Integer isCertified) {
		this.isCertified = isCertified;
	}
	
}

package com.yuxin.wx.model.institution;

import java.io.Serializable;

/**
 * 推荐结果集
 * @author hello
 *
 */
public class RecommendedInsCly implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recommendedInsClyName;	
	private Integer recommendedInsClyId;
	private Integer codeLevel;//分类级别
	public String getRecommendedInsClyName() {
		return recommendedInsClyName;
	}
	public void setRecommendedInsClyName(String recommendedInsClyName) {
		this.recommendedInsClyName = recommendedInsClyName;
	}
	public Integer getRecommendedInsClyId() {
		return recommendedInsClyId;
	}
	public void setRecommendedInsClyId(Integer recommendedInsClyId) {
		this.recommendedInsClyId = recommendedInsClyId;
	}
	public Integer getCodeLevel() {
		return codeLevel;
	}
	public void setCodeLevel(Integer codeLevel) {
		this.codeLevel = codeLevel;
	}
	
}

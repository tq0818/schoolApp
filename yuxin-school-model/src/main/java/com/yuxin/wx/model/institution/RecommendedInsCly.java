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
	
}

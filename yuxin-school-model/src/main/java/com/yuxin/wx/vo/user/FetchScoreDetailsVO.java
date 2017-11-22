package com.yuxin.wx.vo.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FetchScoreDetailsVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer itemScore;
	private String	origin;
	private Date createTime;
	private String url;
	private BigDecimal fixedScore;
	private Integer fixedPerson;
	private Date fixedTime;
	private Integer totalScoreId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemScore() {
		return itemScore;
	}
	public void setItemScore(Integer itemScore) {
		this.itemScore = itemScore;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public BigDecimal getFixedScore() {
		return fixedScore;
	}
	public void setFixedScore(BigDecimal fixedScore) {
		this.fixedScore = fixedScore;
	}
	public Integer getFixedPerson() {
		return fixedPerson;
	}
	public void setFixedPerson(Integer fixedPerson) {
		this.fixedPerson = fixedPerson;
	}
	public Date getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
	}
	public Integer getTotalScoreId() {
		return totalScoreId;
	}
	public void setTotalScoreId(Integer totalScoreId) {
		this.totalScoreId = totalScoreId;
	}
	
}

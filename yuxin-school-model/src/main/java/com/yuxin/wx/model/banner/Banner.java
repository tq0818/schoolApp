package com.yuxin.wx.model.banner;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionAnswer
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class Banner extends BaseEntity {
	
	
	private Integer id;
	private String bannerName; /* 标题 */
	private String bannerDescribe; /* 描述 */
	private String bannerContent; /* 内容 */
	private String bannerImgUrl; /* banner图路径 */
	private Date updateTime; /* 更新时间 */
	private Integer isState; /* 状态( 0： 禁用 1：启用) */
	private Integer orderByNum;//排序号
	
	
	
	// Constructor
	public Banner() {
	}

	public Banner(Integer id, String bannerName, String bannerDescribe, String bannerContent, String bannerImgUrl,
	        Date updateTime, Integer isState, Integer orderByNum) {
		super();
		this.id = id;
		this.bannerName = bannerName;
		this.bannerDescribe = bannerDescribe;
		this.bannerContent = bannerContent;
		this.bannerImgUrl = bannerImgUrl;
		this.updateTime = updateTime;
		this.isState = isState;
		this.orderByNum = orderByNum;
	}

	
	@Override
	public String toString() {
		return "Banner [id=" + id + ", bannerName=" + bannerName + ", bannerDescribe=" + bannerDescribe + ", bannerContent="
		        + bannerContent + ", bannerImgUrl=" + bannerImgUrl + ", updateTime=" + updateTime + ", isState=" + isState
		        + ", orderByNum=" + orderByNum + "]";
	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getBannerName() {
		return bannerName;
	}

	
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	
	public String getBannerDescribe() {
		return bannerDescribe;
	}

	
	public void setBannerDescribe(String bannerDescribe) {
		this.bannerDescribe = bannerDescribe;
	}

	
	public String getBannerContent() {
		return bannerContent;
	}

	
	public void setBannerContent(String bannerContent) {
		this.bannerContent = bannerContent;
	}

	
	public String getBannerImgUrl() {
		return bannerImgUrl;
	}

	
	public void setBannerImgUrl(String bannerImgUrl) {
		this.bannerImgUrl = bannerImgUrl;
	}

	
	public Date getUpdateTime() {
		return updateTime;
	}

	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	public Integer getIsState() {
		return isState;
	}

	
	public void setIsState(Integer isState) {
		this.isState = isState;
	}

	
	public Integer getOrderByNum() {
		return orderByNum;
	}

	
	public void setOrderByNum(Integer orderByNum) {
		this.orderByNum = orderByNum;
	}

	
	
}

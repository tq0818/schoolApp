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
	private String bannerContentUrl; /* 内容 */
	private String bannerImgUrl; /* banner图路径 */
	private String realyBannerImgUrl; /* banner图路径 */
	private Date updateTime; /* 更新时间 */
	private Integer isState; /* 状态( 0： 禁用 1：启用) */
	private Integer orderByNum;//排序号
	private Integer bannerType;//排序号
	private String linkHref;//活动链接
	private String searchClass;//课程参数
	private Integer detailType;//0表示活动，1表示课程，2表示内容
	
	
	
	// Constructor
	public Banner() {
	}
	
	public Banner(Integer id, String bannerName, String bannerDescribe, String bannerContent, String bannerContentUrl,
	        String bannerImgUrl, String realyBannerImgUrl, Date updateTime, Integer isState, Integer orderByNum,Integer bannerType) {
		super();
		this.id = id;
		this.bannerName = bannerName;
		this.bannerDescribe = bannerDescribe;
		this.bannerContent = bannerContent;
		this.bannerContentUrl = bannerContentUrl;
		this.bannerImgUrl = bannerImgUrl;
		this.realyBannerImgUrl = realyBannerImgUrl;
		this.updateTime = updateTime;
		this.isState = isState;
		this.orderByNum = orderByNum;
		this.bannerType = bannerType;
	}


	@Override
	public String toString() {
		return "Banner [id=" + id + ", bannerName=" + bannerName + ", bannerDescribe=" + bannerDescribe + ", bannerContent="
		        + bannerContent + ", bannerContentUrl=" + bannerContentUrl + ", bannerImgUrl=" + bannerImgUrl
		        + ", realyBannerImgUrl=" + realyBannerImgUrl + ", updateTime=" + updateTime + ", isState=" + isState
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

	
	public String getBannerContentUrl() {
		return bannerContentUrl;
	}

	
	public void setBannerContentUrl(String bannerContentUrl) {
		this.bannerContentUrl = bannerContentUrl;
	}

	
	public String getRealyBannerImgUrl() {
		return realyBannerImgUrl;
	}

	
	public void setRealyBannerImgUrl(String realyBannerImgUrl) {
		this.realyBannerImgUrl = realyBannerImgUrl;
	}

	public Integer getBannerType() {
		return bannerType;
	}

	public void setBannerType(Integer bannerType) {
		this.bannerType = bannerType;
	}

	public String getLinkHref() {
		return linkHref;
	}

	public void setLinkHref(String linkHref) {
		this.linkHref = linkHref;
	}

	public String getSearchClass() {
		return searchClass;
	}

	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	
	
}

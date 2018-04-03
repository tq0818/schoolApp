package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysCyclePic
 * 
 * @author chopin
 * @date 2015-4-11
 */
@SuppressWarnings("serial")
public class SysCyclePic extends BaseEntity {
	
	
	private String	picTitle;		 /* 图片的标题 */ 
	private String	picDesc;		 /* 图片详细描述 */ 
	private String	picUrl;		 /* 图片存放路径 */ 
	private String	clickUrl;		 /* 点击图片打开的地址 */ 
	private Integer	validFlag;		 /* 图片有效标记（1：有效；0：无效） */ 
	private String	picType;		 /* 轮播图类型（首页轮播图：homepage；） */ 
	private Integer	companyId;		
	private Integer	schoolId;		
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;
	private Integer picSequence;

	private String typeName;
	
	private Integer bannerId; //APP后台的ID     
	private String bannerName;   
	private String bannerDescribe;
	private String bannerContent; 
	private String bannerImgUrl; 
	private Integer isState;          
	private Integer orderByNum; 
	private String bannerContentUrl;
	//2018-4-3,fengxl添加，前台判断banner内容。
	private Integer detailType; //0表示活动，1表示课程，2表示内容
	private String linkHref;//活动链接
	private String searchClass;//课程参数
	
	private String classtypeId;//课程id
	private String name;//课程名称
	private String liveFlag;//课程类型
	private String comId;//商品id
	private String teacherName;//老师姓名
	
	// Constructor
	public SysCyclePic() {
	}
	
	/**
	 * full Constructor
	 */
	public SysCyclePic(Integer id, String picTitle, String picDesc, String picUrl, String clickUrl, Integer validFlag, String picType, Integer companyId, Integer schoolId, Integer creator, Date createTime, Integer updator, Date updateTime,Integer picSequence) {
		setId(id);
		this.picTitle = picTitle;
		this.picDesc = picDesc;
		this.picUrl = picUrl;
		this.clickUrl = clickUrl;
		this.validFlag = validFlag;
		this.picType = picType;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.picSequence = picSequence;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysCyclePic可以实现连缀设置属性
	
	public String getPicTitle() {
		return picTitle;
	}

	public SysCyclePic setPicTitle(String picTitle) {
		this.picTitle = picTitle;
		return this;
	}
	
	
	public String getPicDesc() {
		return picDesc;
	}

	public SysCyclePic setPicDesc(String picDesc) {
		this.picDesc = picDesc;
		return this;
	}
	
	
	public String getPicUrl() {
		return picUrl;
	}

	public SysCyclePic setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}
	
	
	public String getClickUrl() {
		return clickUrl;
	}

	public SysCyclePic setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
		return this;
	}
	
	
	public Integer getValidFlag() {
		return validFlag;
	}

	public SysCyclePic setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
		return this;
	}
	
	
	public String getPicType() {
		return picType;
	}

	public SysCyclePic setPicType(String picType) {
		this.picType = picType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysCyclePic setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysCyclePic setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysCyclePic setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysCyclePic setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysCyclePic setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysCyclePic setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getPicSequence() {
		return picSequence;
	}

	public void setPicSequence(Integer picSequence) {
		this.picSequence = picSequence;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
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

	@Override
	public String toString() {
		return "SysCyclePic [" + "id=" + getId() + ", picTitle=" + picTitle + ", picDesc=" + picDesc + ", picUrl=" + picUrl + ", clickUrl=" + clickUrl + ", validFlag=" + validFlag + ", picType=" + picType + ", companyId=" + companyId + ", schoolId=" + schoolId + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
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

	public String getClasstypeId() {
		return classtypeId;
	}

	public void setClasstypeId(String classtypeId) {
		this.classtypeId = classtypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLiveFlag() {
		return liveFlag;
	}

	public void setLiveFlag(String liveFlag) {
		this.liveFlag = liveFlag;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}

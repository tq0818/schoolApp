package com.yuxin.wx.vo.banner;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * 
 * @author jishangyang 2017年12月1日 下午1:45:54
 * @ClassName bannerVo
 * @Description banner 类
 * @version V1.0
 */
@SuppressWarnings("serial")
public class bannerVo extends  BaseEntity  {
	private Integer id;
	private String banner_name; /* 提问标题 */
	private String banner_describe; /* 问题描述 */
	private Integer banner_content; /* 学科ID */
	private Integer banner_img_url; /* 学科小类ID */
	
	private Date createTime; /* 创建时间 */
	private Date updateTime; /* 更新时间 */
	private Integer isState; /* 状态( 0： 禁用 1：启用) */
	
	
	
	
	// Constructor
	public bannerVo() {
	}




	public bannerVo(Integer id, String banner_name, String banner_describe, Integer banner_content, Integer banner_img_url,
	        Date createTime, Date updateTime, Integer isState) {
		super();
		this.id = id;
		this.banner_name = banner_name;
		this.banner_describe = banner_describe;
		this.banner_content = banner_content;
		this.banner_img_url = banner_img_url;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isState = isState;
	}




	@Override
	public String toString() {
		return "bannerVo [id=" + id + ", banner_name=" + banner_name + ", banner_describe=" + banner_describe
		        + ", banner_content=" + banner_content + ", banner_img_url=" + banner_img_url + ", createTime=" + createTime
		        + ", updateTime=" + updateTime + ", isState=" + isState + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getBanner_name() {
		return banner_name;
	}

	public void setBanner_name(String banner_name) {
		this.banner_name = banner_name;
	}

	public String getBanner_describe() {
		return banner_describe;
	}

	public void setBanner_describe(String banner_describe) {
		this.banner_describe = banner_describe;
	}

	public Integer getBanner_content() {
		return banner_content;
	}

	public void setBanner_content(Integer banner_content) {
		this.banner_content = banner_content;
	}

	public Integer getBanner_img_url() {
		return banner_img_url;
	}

	
	public void setBanner_img_url(Integer banner_img_url) {
		this.banner_img_url = banner_img_url;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	
	
	

	

	
}

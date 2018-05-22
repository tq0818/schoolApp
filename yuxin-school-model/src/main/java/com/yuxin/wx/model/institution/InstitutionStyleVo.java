package com.yuxin.wx.model.institution;

import java.io.Serializable;

/**
 * 机构风采
 * @author hello
 *
 */
public class InstitutionStyleVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer institutionStyleId;//		风采id
	private String imgUrl;//		图片地址
	private String institutionName;//		风采名称
	private Integer watchCount;//		观看次数
	private Integer isVideo	;//	是否是视频(0否1是)
	private String content;//描述
	public Integer getInstitutionStyleId() {
		return institutionStyleId;
	}
	public void setInstitutionStyleId(Integer institutionStyleId) {
		this.institutionStyleId = institutionStyleId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	public Integer getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	}
	public Integer getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Integer isVideo) {
		this.isVideo = isVideo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

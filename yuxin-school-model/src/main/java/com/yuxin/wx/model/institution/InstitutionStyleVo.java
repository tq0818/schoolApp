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
	private Integer scanCount;//		观看次数
	private Integer isVideo	;//	是否是视频(0否1是)
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
	public Integer getScanCount() {
		return scanCount;
	}
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}
	public Integer getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Integer isVideo) {
		this.isVideo = isVideo;
	}
	
}

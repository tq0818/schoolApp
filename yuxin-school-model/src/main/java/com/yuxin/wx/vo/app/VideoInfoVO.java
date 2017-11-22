package com.yuxin.wx.vo.app;

import java.io.Serializable;
/**
 * 录播信息
 * @author admin
 *
 */
public class VideoInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//课程id
	private Integer companyId;//公司id
	private Integer comId;//商品ID
	private String className;//课程名
	private String coverUrl;//图片地址
	private String videoTime;//视频时长
	private float rate;//进度
	private Integer buyNum;//购买人数
	private String liveFlag;
	private String name;//教师名字
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public String getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(String liveFlag) {
		this.liveFlag = liveFlag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

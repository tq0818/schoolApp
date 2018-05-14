package com.yuxin.wx.model.institution;

import java.util.Date;

/**
 * 视频实体
 * @author hello
 *
 */
public class InsVideo {
	private Integer id;
	private Integer sourceId;
	private String videoName;
	private String videoCcId;
	private String videoTime;
	private Double videoSize;
	private String videoStatus;
	private Integer creator;
	private Date createTime;
	private Date updateTime;
	private String storageType;
	private Integer type;
	private Integer watchCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoCcId() {
		return videoCcId;
	}
	public void setVideoCcId(String videoCcId) {
		this.videoCcId = videoCcId;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public Double getVideoSize() {
		return videoSize;
	}
	public void setVideoSize(Double videoSize) {
		this.videoSize = videoSize;
	}
	public String getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
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
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	}
	
}

package com.yuxin.wx.model.institution;

import java.util.Date;

import com.yuxin.wx.common.BaseModel;

/**
 * 风采实体
 * @author hello
 *
 */
public class InstitutionStyle extends BaseModel{
	private Integer id;//主键
	private Integer relationId;//关系id
	private String name;//名称
	private String imgUrl;//图片地址
	private Integer isVideo;//是否是视频
	private Integer sourceFlag;//0 机构 1课程
	private String content;//描述
	private Integer type;//0 是封面图 1 是视频 2 是风采图
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private Integer isTop;//是否置顶 0 否 1 是
	private String updateFlag;//更新标志，根据这个标志判断是否需要更新更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Integer isVideo) {
		this.isVideo = isVideo;
	}
	public Integer getSourceFlag() {
		return sourceFlag;
	}
	public void setSourceFlag(Integer sourceFlag) {
		this.sourceFlag = sourceFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
}

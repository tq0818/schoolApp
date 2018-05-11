package com.yuxin.wx.model.institution;

import java.io.Serializable;
import java.util.Date;
/**
 * 机构、课程收藏
 * @author hello
 *
 */
public class InsCollect implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private Integer userId;
	private Integer relationId;
	private Integer collectType;
	private Integer isCollect;
	private Date createTime;
	private Date updateTime;
	private Integer page;
	private Integer firstSize;
	private Integer endSize;
	private Integer isPage;//是否分页
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getCollectType() {
		return collectType;
	}
	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
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
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getFirstSize() {
		return firstSize;
	}
	public void setFirstSize(Integer firstSize) {
		this.firstSize = firstSize;
	}
	public Integer getEndSize() {
		return endSize;
	}
	public void setEndSize(Integer endSize) {
		this.endSize = endSize;
	}
	public Integer getIsPage() {
		return isPage;
	}
	public void setIsPage(Integer isPage) {
		this.isPage = isPage;
	}
	
}

package com.yuxin.wx.model.institution;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class InstitutionClassTypeVo extends BaseEntity{
	/*
	 * id                   int(11)                        not null AUTO_INCREMENT,
   name                 varchar(60)                    null comment '课程名称',
   cover_url            varchar(200)                   null comment '课程封面图',
   price                float                          null comment '价格',
   is_reser             int(1)                         null comment '是否限定预约人数(0否1是)',
   reser_count          int(11)                        null comment '预约人数限定',
   detaildesc           text                           null comment '课程描述',
   is_shelves           int(1)                         null comment '是否上架(0否 1是)',
   del_flag             int(1)                         null comment '删除标志(0未删除 1是删除)',
   create_time          datetime                       null comment '创建时间',
   update_time          datetime                       null comment '修改时间',
	 * */
	
	private Integer id;	//not null AUTO_INCREMENT,
	private String name;	//'课程名称',
	private String coverUrl;	//'课程封面图',
	private Float price;	//'价格',
	private Integer  isReser;	//'是否限定预约人数(0否1是)',
	private Integer reserCount;	//'预约人数限定',
	private String detaildesc;	//'课程描述',
	private Integer isShelves;	//'是否上架(0否 1是)',
	private Integer delFlag;	//'删除标志(0未删除 1是删除)',
	private Date createTime;	//'创建时间',
	private Date updateTime;	//'修改时间',
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getIsReser() {
		return isReser;
	}
	public void setIsReser(Integer isReser) {
		this.isReser = isReser;
	}
	public Integer getReserCount() {
		return reserCount;
	}
	public void setReserCount(Integer reserCount) {
		this.reserCount = reserCount;
	}
	public String getDetaildesc() {
		return detaildesc;
	}
	public void setDetaildesc(String detaildesc) {
		this.detaildesc = detaildesc;
	}
	public Integer getIsShelves() {
		return isShelves;
	}
	public void setIsShelves(Integer isShelves) {
		this.isShelves = isShelves;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	
	
	
}

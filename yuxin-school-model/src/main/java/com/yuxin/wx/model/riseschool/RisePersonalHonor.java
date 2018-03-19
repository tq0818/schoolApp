package com.yuxin.wx.model.riseschool;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/3/2.
 */
public class RisePersonalHonor extends BaseEntity {

    private Integer studentId;

    private String honorContent;
    
    private Date createTime;

    private Date updateTime;
    
    private Integer userId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getHonorContent() {
        return honorContent;
    }

    public void setHonorContent(String honorContent) {
        this.honorContent = honorContent;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
}

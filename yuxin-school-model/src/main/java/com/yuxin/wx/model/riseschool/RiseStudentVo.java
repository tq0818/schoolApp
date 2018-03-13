package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.common.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lym_gxm on 18/3/2.
 */
public class RiseStudentVo extends BaseEntity {

    private Integer userId;

    private String studentName;

    private String sex;

    private String birthday;

    private String idNo;

    private String censusAddress;

    private String censusUrl;

    private String headUrl;

    private String selfUrl;

    private String curator;

    private String mobile;

    private String studentNo;
    
    private String curatorRelation;

    private Date createTime;

    private Date updateTime;
    
    private String censusDetAddress;
    
    private String curatorRelationName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCensusAddress() {
        return censusAddress;
    }

    public void setCensusAddress(String censusAddress) {
        this.censusAddress = censusAddress;
    }

    public String getCensusUrl() {
        return censusUrl;
    }

    public void setCensusUrl(String censusUrl) {
        this.censusUrl = censusUrl;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

	public String getCuratorRelation() {
		return curatorRelation;
	}

	public void setCuratorRelation(String curatorRelation) {
		this.curatorRelation = curatorRelation;
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

	public String getCensusDetAddress() {
		return censusDetAddress;
	}

	public void setCensusDetAddress(String censusDetAddress) {
		this.censusDetAddress = censusDetAddress;
	}

	public String getCuratorRelationName() {
		return curatorRelationName;
	}

	public void setCuratorRelationName(String curatorRelationName) {
		this.curatorRelationName = curatorRelationName;
	}
    
}

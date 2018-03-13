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

    private String censusAddress;//户籍地址
    private String censusDetAddress;//详细地址

    private String censusUrl;

    private String headUrl;

    private String selfUrl;

    private String curator;

    private String mobile;//电话
    private String curatorMobile;//家长电话

    private String studentNo;
    

//    private String curatorRelation;

    private Date createTime;

    private Date updateTime;
    
//    private String censusDetAddress;
    
    private String curatorRelationName;

    private String schoolName;//申请学校
    
    private String schoolTag;//毕业学校
    
//    private String createTime;//创建时间
    private Date putTime;//提交时间
    
    private String endTime;//结束时间
    private String startTime;//开始时间
    
    private String isCheck;//审核状态
    
    private String timeOrder;//是否按照提交时间排序，1不是，2是
    private String curatorRelation;//监护人状态(0父子，1父女，2母子，3母女，4其他)
    private String schoolId;//学校id

    public Integer getUserId() {
        return userId;
    }

	public void setUserId(Integer userId) {
        this.userId = userId;
    }
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
//	public String getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(String createTime) {
//		this.createTime = createTime;
//	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getEndTime() {
		return endTime;
	}
 
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTimeOrder() {
		return timeOrder;
	}

	public void setTimeOrder(String timeOrder) {
		this.timeOrder = timeOrder;
	}

	public String getSchoolTag() {
		return schoolTag;
	}

	public void setSchoolTag(String schoolTag) {
		this.schoolTag = schoolTag;
	}

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

//	public String getCensusDetAddress() {
//		return censusDetAddress;
//	}
//
//	public void setCensusDetAddress(String censusDetAddress) {
//		this.censusDetAddress = censusDetAddress;
//	}

	public String getCuratorMobile() {
		return curatorMobile;
	}

	public void setCuratorMobile(String curatorMobile) {
		this.curatorMobile = curatorMobile;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

//	public String getCuratorRelation() {
//		return curatorRelation;
//	}
//
//	public void setCuratorRelation(String curatorRelation) {
//		this.curatorRelation = curatorRelation;
//	}
    
}

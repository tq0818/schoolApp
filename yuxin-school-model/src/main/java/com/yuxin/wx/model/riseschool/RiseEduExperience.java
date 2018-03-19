package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.common.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lym_gxm on 18/3/2.
 */
public class RiseEduExperience extends BaseEntity {

    private Integer studentId;

    private String provinceName;

    private String cityName;

    private String districtName;

    private String schoolName;

    private Integer isGraduate;
    
    private String province;

    private String city;

    private String district;

    private Date createTime;

    private Date updateTime;
    
    private String connectNameInfo;
    
    private int schoolNo;
    
    private String schoolNoName;
    
    private Integer userId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(Integer isGraduate) {
        this.isGraduate = isGraduate;
    }

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getConnectNameInfo() {
		return connectNameInfo;
	}

	public void setConnectNameInfo(String connectNameInfo) {
		this.connectNameInfo = connectNameInfo;
	}

	public int getSchoolNo() {
		return schoolNo;
	}

	public void setSchoolNo(int schoolNo) {
		this.schoolNo = schoolNo;
	}

	public String getSchoolNoName() {
		return schoolNoName;
	}

	public void setSchoolNoName(String schoolNoName) {
		this.schoolNoName = schoolNoName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}

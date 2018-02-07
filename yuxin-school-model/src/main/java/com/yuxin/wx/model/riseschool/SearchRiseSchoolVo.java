package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校管理模糊搜索
 */
public class SearchRiseSchoolVo extends BaseModel implements Serializable {
    private String schoolName;   //学校名称
    private String provinceCode;       //省份
    private String cityCode;           //市级
    private String district;           //区级
    private Integer isShalve;          //上架状态
    private String startTime;           //开始时间
    private String endTime;           //结束时间

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getIsShalve() {
        return isShalve;
    }

    public void setIsShalve(Integer isShalve) {
        this.isShalve = isShalve;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

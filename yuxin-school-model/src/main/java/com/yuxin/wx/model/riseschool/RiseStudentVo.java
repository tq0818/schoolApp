package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.common.BaseModel;

import java.io.Serializable;

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

    private String province;

    private String city;

    private String censusUrl;

    private String headUrl;

    private String selfUrl;

    private String curator;

    private String mobile;

    private String studentNo;

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
}

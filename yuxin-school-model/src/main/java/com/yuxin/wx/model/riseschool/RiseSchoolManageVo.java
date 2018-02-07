package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseModel;

import java.util.Date;

/**
 * 学校管理信息
 */
public class RiseSchoolManageVo extends BaseModel{
    private Integer id;            //主键
    private String schoolName;   //学校名称
    private String enrollmentType;  //招生类型
    private String provinceCode;       //省份
    private String cityCode;           //市级
    private String district;           //区级
    private Integer isTop;             //置顶状态
    private Integer isShalve;          //上架状态
    private Date createTime;           //创建时间
    private Date updateTime;           //更新时间
    private String detailAddress;      //学校详细地址
    private String schoolWeb;          //学校网址
    private String schoolFax;          //学校传真
    private String busRoad;//公交路线      //公交路线
    private Integer userId;//用户id      //用户
    private Integer collectNum; //收藏数
    private Integer baseNum; //收藏基数
    private String userName;//用户名
    private String enrollmentName;//用户名
    private String provinceName;//用户名
    private String cityName;//用户名
    private String districtName;//用户名
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    public void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
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

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsShalve() {
        return isShalve;
    }

    public void setIsShalve(Integer isShalve) {
        this.isShalve = isShalve;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getSchoolWeb() {
        return schoolWeb;
    }

    public void setSchoolWeb(String schoolWeb) {
        this.schoolWeb = schoolWeb;
    }

    public String getSchoolFax() {
        return schoolFax;
    }

    public void setSchoolFax(String schoolFax) {
        this.schoolFax = schoolFax;
    }

    public String getBusRoad() {
        return busRoad;
    }

    public void setBusRoad(String busRoad) {
        this.busRoad = busRoad;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(Integer baseNum) {
        this.baseNum = baseNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEnrollmentName() {
        return enrollmentName;
    }

    public void setEnrollmentName(String enrollmentName) {
        this.enrollmentName = enrollmentName;
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
}

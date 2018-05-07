package com.yuxin.wx.model.institution;

import com.yuxin.wx.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InstitutionInfoVo extends BaseEntity {
    private String name; //机构名称
    private String province;//省
    private String city;//市
    private String aera;//区
    private String address;//地址
    private String mobile;//电话
    private Integer isChain;//是否是连锁机构(1是0否)
    private Integer chainId;//连锁id
    private String chainName;//连锁机构名称
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private String reservService;//预约服务
    private Integer isShelves;//是否上架(1 是 0否)
    private Integer getCertified;//是否认证(1是 0否)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private String codeName;//分类名称
    private List<String> firstcodeName;//一级分类名称
    private List<String> secondcodeName;//二级分类名称


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAera() {
        return aera;
    }

    public void setAera(String aera) {
        this.aera = aera;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsChain() {
        return isChain;
    }

    public void setIsChain(Integer isChain) {
        this.isChain = isChain;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getReservService() {
        return reservService;
    }

    public void setReservService(String reservService) {
        this.reservService = reservService;
    }

    public Integer getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(Integer isShelves) {
        this.isShelves = isShelves;
    }

    public Integer getGetCertified() {
        return getCertified;
    }

    public void setGetCertified(Integer getCertified) {
        this.getCertified = getCertified;
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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public List<String> getFirstcodeName() {
        return firstcodeName;
    }

    public void setFirstcodeName(List<String> firstcodeName) {
        this.firstcodeName = firstcodeName;
    }

    public List<String> getSecondcodeName() {
        return secondcodeName;
    }

    public void setSecondcodeName(List<String> secondcodeName) {
        this.secondcodeName = secondcodeName;
    }
}

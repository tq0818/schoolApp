package com.yuxin.wx.model.institution;

import com.yuxin.wx.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class InstitutionInfoVo extends BaseEntity {
    private String name; //机构名称
    private String province;//省
    private String city;//市
    private String area;//区
    private String address;//地址
    private String mobile;//电话
    private Integer isChain;//是否是连锁机构(1是0否)
    private String isChains;//是否是连锁机构(1是0否)
    private Integer chainId;//连锁id
    private String chainName;//连锁机构名称
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private String reservService;//预约服务
    private Integer isShelves;//是否上架(1 是 0否)
    private Integer isCertified;//是否认证(1是 0否)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private String codeName;//分类名称
    private String firstcodeName;//一级分类名称
    private String secondcodeName;//二级分类名称
    private String oneLevelId;//一级分类id
    private String twoLevelId;//二级分类id

    private String sysLabelIds;//系统标签id(在机构基本信息页修改的标签)
    private String customLabelIds;//自定义标签id(在机构基本信息页修改的标签)
    private String specialServiceIds;//特殊服务id(在机构基本信息页修改的标签)

    private String sysLabel;//系统标签
    private String customLabel;//自定义标签
    private String specialService;//特殊服务

    private String sysLabelNew;//系统标签(在机构基本信息页新增的标签)
    private String customLabelNew;//自定义标签(在机构基本信息页新增的标签)
    private String specialServiceNew;//特殊服务(在机构基本信息页新增的标签)

    private String imgUrl;//修改的特殊服务图片路径
    private String imgUrlNew;//新增的特殊服务图片路径
    private Integer page;
    private Integer firstPage;
    private Integer pageSize;
    private String startTime;
    private String endTime;
    private Integer sort;
    private String userName;//管理员账号
    private String pwd;
    private Integer userId;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsChains() {
        return isChains;
    }

    public void setIsChains(String isChains) {
        this.isChains = isChains;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Integer isCertified) {
        this.isCertified = isCertified;
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

    public String getFirstcodeName() {
        return firstcodeName;
    }

    public void setFirstcodeName(String firstcodeName) {
        this.firstcodeName = firstcodeName;
    }

    public String getSecondcodeName() {
        return secondcodeName;
    }

    public void setSecondcodeName(String secondcodeName) {
        this.secondcodeName = secondcodeName;
    }

    public String getOneLevelId() {
        return oneLevelId;
    }

    public void setOneLevelId(String oneLevelId) {
        this.oneLevelId = oneLevelId;
    }

    public String getTwoLevelId() {
        return twoLevelId;
    }

    public void setTwoLevelId(String twoLevelId) {
        this.twoLevelId = twoLevelId;
    }

    public String getSysLabel() {
        return sysLabel;
    }

    public void setSysLabel(String sysLabel) {
        this.sysLabel = sysLabel;
    }

    public String getCustomLabel() {
        return customLabel;
    }

    public void setCustomLabel(String customLabel) {
        this.customLabel = customLabel;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSysLabelIds() {
        return sysLabelIds;
    }

    public void setSysLabelIds(String sysLabelIds) {
        this.sysLabelIds = sysLabelIds;
    }

    public String getCustomLabelIds() {
        return customLabelIds;
    }

    public void setCustomLabelIds(String customLabelIds) {
        this.customLabelIds = customLabelIds;
    }

    public String getSpecialServiceIds() {
        return specialServiceIds;
    }

    public void setSpecialServiceIds(String specialServiceIds) {
        this.specialServiceIds = specialServiceIds;
    }

    public String getSpecialService() {
        return specialService;
    }

    public void setSpecialService(String specialService) {
        this.specialService = specialService;
    }

    public String getSysLabelNew() {
        return sysLabelNew;
    }

    public void setSysLabelNew(String sysLabelNew) {
        this.sysLabelNew = sysLabelNew;
    }

    public String getCustomLabelNew() {
        return customLabelNew;
    }

    public void setCustomLabelNew(String customLabelNew) {
        this.customLabelNew = customLabelNew;
    }

    public String getSpecialServiceNew() {
        return specialServiceNew;
    }

    public void setSpecialServiceNew(String specialServiceNew) {
        this.specialServiceNew = specialServiceNew;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrlNew() {
        return imgUrlNew;
    }

    public void setImgUrlNew(String imgUrlNew) {
        this.imgUrlNew = imgUrlNew;
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

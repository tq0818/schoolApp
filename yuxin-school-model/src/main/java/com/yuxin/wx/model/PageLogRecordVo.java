package com.yuxin.wx.model;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/5/23.
 */
public class PageLogRecordVo extends BaseEntity {


    /**
     * 页面编码
     */
    private String pageNo;

    /**
     * 用户标识
     */
    private String userFlag;

    /**
     * 进入时间
     */
    private String inTime;

    /**
     * 推出页面时间
     */
    private String outTime;

    /**
     * 客户端类型   1:ios 2:安卓
     */
    private String clientType;

    /**
     * 记录业务类型 1:机构
     */
    private String recordType;

    /**
     * 挂载id
     */
    private String pkId;

    /**
     *  1:机构 2:课程 （机构内对应id类型）
     */
    private String type;

    /**
     * 预约类型：点击预约按钮：1.预约机构  2.预约机构礼包 3.课程预约 4.点击打电话   提交预约按钮：11.提交机构预约 21.提交机构礼包预约 31.提交课程预约
     */
    private String reservationType;


    /**
     * 初始化实体数据
     * @param pageNo
     * @param userFlag
     * @param inTime
     * @param outTime
     * @param clientType
     * @param recordType
     * @param pkId
     */
    public PageLogRecordVo(String pageNo, String userFlag, String inTime, String outTime, String clientType, String recordType, String pkId) {
        this.pageNo = pageNo;
        this.userFlag = userFlag;
        this.inTime = inTime;
        this.outTime = outTime;
        this.clientType = clientType;
        this.recordType = recordType;
        this.pkId = pkId;
    }


    public PageLogRecordVo() {
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }


}

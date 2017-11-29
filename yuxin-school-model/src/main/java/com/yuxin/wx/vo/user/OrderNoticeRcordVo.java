package com.yuxin.wx.vo.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 17/11/30.
 */
public class OrderNoticeRcordVo extends BaseEntity {
    //电话号码
    private String telNum;
    //用户姓名
    private String userName;
    //是否报名
    private String signUp;
    //是否同意
    private String agree;
    //消息ID
    private String stuMsgId;
    //学生Id
    private String userId;

    public OrderNoticeRcordVo(String telNum, String userName, String signUp, String agree, String stuMsgId, String userId) {
        this.telNum = telNum;
        this.userName = userName;
        this.signUp = signUp;
        this.agree = agree;
        this.stuMsgId = stuMsgId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignUp() {
        return signUp;
    }

    public void setSignUp(String signUp) {
        this.signUp = signUp;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getStuMsgId() {
        return stuMsgId;
    }

    public void setStuMsgId(String stuMsgId) {
        this.stuMsgId = stuMsgId;
    }
}

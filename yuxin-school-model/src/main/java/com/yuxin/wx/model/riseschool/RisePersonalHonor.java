package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/3/2.
 */
public class RisePersonalHonor extends BaseEntity {

    private Integer studentId;

    private String honorContent;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getHonorContent() {
        return honorContent;
    }

    public void setHonorContent(String honorContent) {
        this.honorContent = honorContent;
    }
}

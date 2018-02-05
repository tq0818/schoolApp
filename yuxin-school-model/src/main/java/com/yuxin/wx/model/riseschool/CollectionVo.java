package com.yuxin.wx.model.riseschool;

/**
 * Created by lym_gxm on 18/2/5.
 */
@SuppressWarnings("serial")
public class CollectionVo {
    private Integer id;
    private Integer userId;
    private Integer schoolId;
    private Integer isCollect;

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}

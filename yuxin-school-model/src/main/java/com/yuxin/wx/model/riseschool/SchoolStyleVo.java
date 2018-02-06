package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/2/5.
 */
@SuppressWarnings("serial")
public class SchoolStyleVo extends BaseEntity {

    private String imgUrl;

    private String imgDiscrible;

    private String schoolId;

    private String isCover;

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDiscrible() {
        return imgDiscrible;
    }

    public void setImgDiscrible(String imgDiscrible) {
        this.imgDiscrible = imgDiscrible;
    }
}

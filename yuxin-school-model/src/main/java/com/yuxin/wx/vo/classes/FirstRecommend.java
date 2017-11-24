package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

import java.util.List;

/**
 * Created by lym_gxm on 17/11/21.
 */
public class FirstRecommend extends BaseEntity {

    //app上架Id
    private String appShelvesId;
    //学段编号
    private String gradeNo;
    //推荐学段排序
    private String sort;

    public String getAppShelvesId() {
        return appShelvesId;
    }

    public void setAppShelvesId(String appShelvesId) {
        this.appShelvesId = appShelvesId;
    }

    public String getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(String gradeNo) {
        this.gradeNo = gradeNo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}

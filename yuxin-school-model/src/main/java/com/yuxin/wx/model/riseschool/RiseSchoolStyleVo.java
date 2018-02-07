package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class RiseSchoolStyleVo extends BaseModel implements Serializable {
    private Integer id;            //主键
    private String imgUrl;         //图片路径
    private String imgDiscrible;   //图片描述
    private Integer isTop;         //是否置顶
    private Date createTime;       //创建时间
    private Date updateTime;       //更新时间
    private Integer riseSchoolId;  //学校id
    private Integer isCover;       //是否封面

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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

    public Integer getRiseSchoolId() {
        return riseSchoolId;
    }

    public void setRiseSchoolId(Integer riseSchoolId) {
        this.riseSchoolId = riseSchoolId;
    }

    public Integer getIsCover() {
        return isCover;
    }

    public void setIsCover(Integer isCover) {
        this.isCover = isCover;
    }
}

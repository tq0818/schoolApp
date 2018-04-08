package com.yuxin.wx.model.app;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/4/8.
 */
public class AppTagVo extends BaseEntity {

    private String type;

    private String name;

    private String isOpen;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
}

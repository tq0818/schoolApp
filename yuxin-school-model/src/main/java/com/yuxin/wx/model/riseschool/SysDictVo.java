package com.yuxin.wx.model.riseschool;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 18/2/5.
 */
@SuppressWarnings("serial")
public class SysDictVo{

    /**
     * 字典值
     */
    private String itemName;

    /**
     * 字典编码
     */
    private String itemCode;

    /**
     * 数据类型
     */
    private String itemType;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}

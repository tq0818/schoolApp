package com.yuxin.wx.model.institution;

/**
 * 用于批量更新排名中的case when 语句中
 */
public class CaseWhenVO {
    private Integer id;
    private Integer sort;

    public CaseWhenVO(Integer id, Integer sort) {
        this.id = id;
        this.sort = sort;
    }

    public CaseWhenVO(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

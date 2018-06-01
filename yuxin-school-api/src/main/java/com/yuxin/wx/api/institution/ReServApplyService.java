package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.ReServApply;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ReServApplyService {

    void update(ReServApply reServApply);

    /**
     * 获取预约机构列表
     * @return
     */
    List<ReServApply> findReServApplyIns();

    /**
     * 获取预约课程列表
     * @return
     */
    List<ReServApply> findReServApplyClass();

    /**
     *查找预约列表
     * @param reServApply
     * @return
     */
    PageFinder<ReServApply> findReServApplyList(ReServApply reServApply) throws ParseException;

    List<Map<Object,Object>> findReServApplyMap(ReServApply reServApply);
    List<Map<Object,Object>> findReServApplyMapByClass(ReServApply reServApply);
}

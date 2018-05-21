package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.ReServApply;

import java.util.List;
import java.util.Map;

public interface ReServApplyMapper {

    void update(ReServApply reServApply);

    /**
     * 获取预约机构列表
     *
     * @return
     */
    List<ReServApply> findReServApplyIns();

    /**
     * 获取预约课程列表
     *
     * @return
     */
    List<ReServApply> findReServApplyClass();

    /**
     * 查找预约列表
     *
     * @param reServApply
     * @return
     */
    List<ReServApply> findReServApplyList(ReServApply reServApply);

    Integer findReServApplyListCount(ReServApply reServApply);

    List<ReServApply> findReServApplyClassList(ReServApply reServApply);

    Integer findReServApplyClassListCount(ReServApply reServApply);


    List<Map> findReServApplyMap(ReServApply reServApply);

    List<Map> findReServApplyClassMap(ReServApply reServApply);
}
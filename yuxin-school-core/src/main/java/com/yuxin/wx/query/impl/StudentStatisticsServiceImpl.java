package com.yuxin.wx.query.impl;

import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.query.mapper.StudentStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */
@Service
public class StudentStatisticsServiceImpl implements IStudentStatisticsService{

    @Autowired
    private StudentStatisticsMapper studentstatisticsMapper;
    @Override
    public Long getAllStudentNum(SysConfigTeacher teacher) {
        return studentstatisticsMapper.getAllStudentNum(teacher);
    }

    @Override
    public Long getAllStudentNumOfComplete(SysConfigTeacher teacher) {
        return studentstatisticsMapper.getAllStudentNumOfComplete(teacher);
    }

    @Override
    public List<Map> getAreaStudentStatistics() {
        return studentstatisticsMapper.getAreaStudentStatistics();
    }

    @Override
    public List<Map> getOrgStudentStatisticsByAreaAndStep(Map<String, Object> map) {
        return studentstatisticsMapper.getOrgStudentStatisticsByAreaAndStep(map);
    }

    @Override
    public List<Map> getOrgStudentStatistics(Map<String, Object> map) {
        return studentstatisticsMapper.getOrgStudentStatistics(map);
    }
}

package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.RiseSchoolStyleService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolStyleVo;
import com.yuxin.wx.riseschool.mapper.RiseSchoolStyleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RiseSchoolStyleServiceImpl implements RiseSchoolStyleService {
    @Autowired
    private RiseSchoolStyleMapper riseSchoolStyleMapper;
    @Override
    public void insertRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo) {
        riseSchoolStyleMapper.insertRiseSchoolStyle(riseSchoolStyleVo);
    }

    @Override
    public void updateRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo) {
        riseSchoolStyleMapper.updateRiseSchoolStyle(riseSchoolStyleVo);
    }

    @Override
    public PageFinder<RiseSchoolStyleVo> queryRiseSchoolStyle(RiseSchoolStyleVo riseSchoolStyleVo) {
        List<RiseSchoolStyleVo> list = riseSchoolStyleMapper.queryRiseSchoolStyle(riseSchoolStyleVo);
        Integer count = riseSchoolStyleMapper.queryRiseSchoolStyleCount(riseSchoolStyleVo);
        PageFinder<RiseSchoolStyleVo> pageFinder = new PageFinder<RiseSchoolStyleVo>(riseSchoolStyleVo.getPage(),riseSchoolStyleVo.getPageSize(),count,list);
        return pageFinder;
    }

    @Override
    public void deleteRiseSchoolStyleById(Integer id) {
        riseSchoolStyleMapper.deleteRiseSchoolStyleById(id);
    }

    @Override
    public RiseSchoolStyleVo queryRiseSchoolStyleById(Map map) {
        return riseSchoolStyleMapper.queryRiseSchoolStyleById(map);
    }
}

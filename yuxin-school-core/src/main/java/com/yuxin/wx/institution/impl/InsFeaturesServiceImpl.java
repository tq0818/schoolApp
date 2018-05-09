package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InsFeaturesService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InsFeaturesMapper;
import com.yuxin.wx.model.institution.InsFeaturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsFeaturesServiceImpl extends BaseServiceImpl implements InsFeaturesService {
    @Autowired
    private InsFeaturesMapper insFeaturesMapper;
    @Override
    public void insert(InsFeaturesVo insFeaturesVo) {
        insFeaturesMapper.insert(insFeaturesVo);
    }

    @Override
    public List<InsFeaturesVo> findInsFeaturesVos() {
        return insFeaturesMapper.findInsFeaturesVos();
    }

    @Override
    public Integer findInsFeaturesVosCount() {
        return insFeaturesMapper.findInsFeaturesVosCount();
    }
}

package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InstitutionInfoMapper;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InstitutionInfoServiceImpl extends BaseServiceImpl implements InstitutionInfoService {
    @Autowired
    private InstitutionInfoMapper institutionInfoMapper;

    @Override
    public void insert(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.insert(institutionInfoVo);
    }

    @Override
    public void update(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.update(institutionInfoVo);
    }

    @Override
    public InstitutionInfoVo findInstitutionInfoById(Integer id) {
        return institutionInfoMapper.findInstitutionInfoById(id);
    }

    @Override
    public InstitutionInfoVo findInstitutionInfo(InstitutionInfoVo institutionInfoVo) {
        return institutionInfoMapper.findInstitutionInfo(institutionInfoVo);
    }


    @Override
    public List<InstitutionInfoVo> findInstitutionInfos() {
        return institutionInfoMapper.findInstitutionInfos();
    }
}

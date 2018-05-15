package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionRelationService;
import com.yuxin.wx.institution.mapper.InstitutionRelationMapper;
import com.yuxin.wx.model.institution.InstitutionRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstitutionRelationServiceImpl implements InstitutionRelationService {
    @Autowired
    private InstitutionRelationMapper institutionRelationMapper;

    @Override
    public void insert(InstitutionRelationVo institutionRelationVo) {
        institutionRelationMapper.insert(institutionRelationVo);
    }
}

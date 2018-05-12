package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionLabelService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InstitutionLabelMapper;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class InstitutionLabelServiceImpl extends BaseServiceImpl implements InstitutionLabelService {
    @Autowired
    private InstitutionLabelMapper institutionLabelMapper;

    @Override
    public void insert(InstitutionLabelVo institutionLabelVo) {
        institutionLabelMapper.insert(institutionLabelVo);
    }

    @Override
    public void update(InstitutionLabelVo institutionLabelVo) {
        institutionLabelMapper.update(institutionLabelVo);
    }

    @Override
    public void deleteInstitutionLabelById(Integer id) {
        institutionLabelMapper.deleteInstitutionLabelById(id);
    }

    @Override
    public List<InstitutionLabelVo> findSysLabelByInsId(Integer id) {
        return institutionLabelMapper.findSysLabelByInsId(id);
    }

    @Override
    public List<InstitutionLabelVo> findCustomLabelByInsId(Integer id) {
        return institutionLabelMapper.findCustomLabelByInsId(id);
    }

    @Override
    public List<InstitutionLabelVo> findSpecialServiceByInsId(Integer id) {
        return institutionLabelMapper.findSpecialServiceByInsId(id);
    }

    @Override
    public Integer findSpecialServiceByInsIdCount(Integer id) {
        return institutionLabelMapper.findSpecialServiceByInsIdCount(id);
    }


    @Override
    public List<InstitutionLabelVo> findTeacherLabelByInsId(Integer insId) {
        return institutionLabelMapper.findTeacherLabelByInsId(insId);
    }

    @Override
    public List<InstitutionLabelVo> getTeacherLabelsByTeacherId(Integer tid) {
        return institutionLabelMapper.getTeacherLabelsByTeacherId(tid);
    }



}

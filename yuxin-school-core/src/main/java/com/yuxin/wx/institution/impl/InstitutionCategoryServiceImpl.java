package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionCategoryService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InstitutionCategoryMapper;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstitutionCategoryServiceImpl extends BaseServiceImpl implements InstitutionCategoryService {
    @Autowired
    private InstitutionCategoryMapper institutionCategoryMapper;

    @Override
    public void insert(InstitutionCategoryVo institutionCategoryVo) {
        institutionCategoryMapper.insert(institutionCategoryVo);
    }

    @Override
    public void update(InstitutionCategoryVo institutionCategoryVo) {
        institutionCategoryMapper.update(institutionCategoryVo);
    }

    @Override
    public void deleteInstitutionCategoryById(Integer id) {
        institutionCategoryMapper.deleteInstitutionCategoryById(id);
    }

    @Override
    public InstitutionCategoryVo findInstitutionCategoryByParentId(Integer parentId) {
        return institutionCategoryMapper.findInstitutionCategoryByParentId(parentId);
    }

    @Override
    public InstitutionCategoryVo findInstitutionCategoryById(Integer id) {
        return institutionCategoryMapper.findInstitutionCategoryById(id);
    }

    @Override
    public List<InstitutionCategoryVo> findInstitutionCategorys() {
        return institutionCategoryMapper.findInstitutionCategorys();
    }


}

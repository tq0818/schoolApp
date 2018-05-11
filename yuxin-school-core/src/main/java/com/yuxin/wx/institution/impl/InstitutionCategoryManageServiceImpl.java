package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InstitutionCategoryManageMapper;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/8.
 * 机构分类管理
 */
@Service
@Transactional
public class InstitutionCategoryManageServiceImpl extends BaseServiceImpl implements InstitutionCategoryManageService {

    @Autowired
    private InstitutionCategoryManageMapper institutionManageMapper;

    @Override
    public List<InstitutionCategoryVo> queryInstitutionCategorys(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategorys(params);
    }

    @Override
    public void saveInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo) {
        institutionManageMapper.insert(insCatInfo);
    }

    @Override
    public void updateInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo) {
        institutionManageMapper.update(insCatInfo);
    }

    @Override
    public InstitutionCategoryVo queryInstitutionCategoryByCondition(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategoryByCondition(params);
    }

    @Override
    public List<InstitutionCategoryVo> queryInstitutionCategorysByInsId(Integer id) {
        return institutionManageMapper.queryInstitutionCategorysByInsId(id);
    }


}

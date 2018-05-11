package com.yuxin.wx.api.institution;

import com.yuxin.wx.model.institution.InstitutionCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/8.
 * 机构分类管理
 */
public interface InstitutionCategoryManageService {


    /**
     * 查询机构分类信息 条件暂时自定义
     * @param params
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorys(Map<String, Object> params);


    /**
     * 保存机构分类信息
     * @param insCatInfo
     */
    void saveInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo);


    /**
     * 修改机构分类信息
     * @param insCatInfo
     */
    void updateInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo);

    /**
     * 根据条件查询分类信息 单条
     * @param params
     * @return
     */
    InstitutionCategoryVo queryInstitutionCategoryByCondition(Map<String, Object> params);

    /**
     * 根据机构id查询机构分类
     * @param id
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysByInsId(Integer id);
}

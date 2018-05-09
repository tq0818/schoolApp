package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionCategoryVo;

import java.util.List;

public interface InstitutionCategoryMapper {

    /**
     * 新增机构分类
     * @param institutionCategoryVo
     */
    void insert (InstitutionCategoryVo institutionCategoryVo);


    /**
     * 修改分类
     * @param institutionCategoryVo
     */
    void update (InstitutionCategoryVo institutionCategoryVo);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteInstitutionCategoryById(Integer id);

    /**
     * 根据父级id查询二级分类
     * @return
     */
    InstitutionCategoryVo findInstitutionCategoryByParentId(Integer parentId);


    /**
     * 根据id查看分类
     * @return
     */
    InstitutionCategoryVo findInstitutionCategoryById(Integer id);

    /**
     * 查询所有分类
     * @return
     */
    List<InstitutionCategoryVo> findInstitutionCategorys();
}

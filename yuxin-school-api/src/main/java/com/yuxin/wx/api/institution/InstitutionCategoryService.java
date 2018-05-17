package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;

import java.util.List;
import java.util.Map;

public interface InstitutionCategoryService {

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

    /**
     * 查询所有一级分类
     * @return
     */
    List<InstitutionCategoryVo> findFistCategorys();

    List<InstitutionCategoryVo> findSecondCategorys();

    List<InstitutionCategoryVo> findCecondCategorys(Integer id);




}

package com.yuxin.wx.institution.mapper;


import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/8.
 */
public interface InstitutionCategoryManageMapper extends BaseMapper<InstitutionCategoryVo> {

    List<InstitutionCategoryVo> queryInstitutionCategorys(Map<String, Object> params);

    InstitutionCategoryVo queryInstitutionCategoryByCondition(Map<String, Object> params);

    List<InstitutionCategoryVo> findFistCategorys();

    List<InstitutionCategoryVo> findCecondCategorys(Integer id );
}

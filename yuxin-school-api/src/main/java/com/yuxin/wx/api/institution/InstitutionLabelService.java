package com.yuxin.wx.api.institution;

import com.yuxin.wx.model.institution.InstitutionLabelVo;

public interface InstitutionLabelService {

    /**
     * 标签
     * @param institutionLabelVo
     */
    void insert (InstitutionLabelVo institutionLabelVo);

    /**
     * 修改标签
     * @param institutionLabelVo
     */
    void update (InstitutionLabelVo institutionLabelVo);

    /**
     * 删除标签
     * @param id
     */
    void deleteInstitutionLabelById(Integer id);


}

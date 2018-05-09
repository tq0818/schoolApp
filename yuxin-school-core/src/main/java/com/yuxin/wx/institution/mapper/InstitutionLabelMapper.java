package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionLabelVo;

import java.util.List;

public interface InstitutionLabelMapper {

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

    /**
     * 根据机构id查找系统标签
     * @param id
     * @return
     */
    List<InstitutionLabelVo> findSysLabelByInsId(Integer id);

    /**
     * 根据机构id查找自定义标签
     * @param id
     * @return
     */
    List<InstitutionLabelVo> findCustomLabelByInsId(Integer id);

    /**
     * 根据机构id查找特殊服务
     * @param id
     * @return
     */
    List<InstitutionLabelVo> findSpecialServiceByInsId(Integer id);
    Integer findSpecialServiceByInsIdCount(Integer id);
}

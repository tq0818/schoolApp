package com.yuxin.wx.api.institution;

import com.yuxin.wx.model.institution.InstitutionLabelVo;

import java.util.List;

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

    /**
     * 根据机构id获取机构下所有名师的自定义标签
     *
     * @param insId
     * @return
     */
    List<InstitutionLabelVo> findTeacherLabelByInsId(Integer insId);



    /**
     * 根据名师id获取标签列表
     * @param tid   名师id
     * @return
     */
    List<InstitutionLabelVo> getTeacherLabelsByTeacherId(Integer tid);

    /**
     * 获取线下课程标签
     * @param cid
     * @return
     */
    List<InstitutionLabelVo> getClassLabels(Integer cid);

}

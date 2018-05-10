package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionInfoVo;

import java.util.List;

public interface InstitutionInfoMapper {

    /**
     * 新增机构
     * @param institutionInfoVo
     */
    void insert (InstitutionInfoVo institutionInfoVo);

    /**
     * 修改机构
     * @param institutionInfoVo
     */
    void update (InstitutionInfoVo institutionInfoVo);

    /**
     * 根据id查看机构
     * @return
     */
    InstitutionInfoVo findInstitutionInfoById(Integer id);

    /**
     * 查看满足条件的机构
     * @return
     */
    InstitutionInfoVo findInstitutionInfo(InstitutionInfoVo institutionInfoVo);

    /**
     * 查询所有机构
     * @return
     */
    List<InstitutionInfoVo> findInstitutionInfos(InstitutionInfoVo institutionInfoVo);

    Integer findInstitutionInfosCount(InstitutionInfoVo institutionInfoVo);

    Integer updateInsById (InstitutionInfoVo institutionInfoVo);

    /**
     * 查看机构是否有管理员
     * @param id
     * @return
     */
    InstitutionInfoVo checkUser(Integer id);
}

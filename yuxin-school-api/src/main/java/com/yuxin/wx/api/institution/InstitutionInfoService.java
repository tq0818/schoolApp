package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionInfoVo;

import java.util.List;

public interface InstitutionInfoService {

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
    PageFinder<InstitutionInfoVo> findInstitutionInfos(InstitutionInfoVo insInfoVo);

    void updateInsById (InstitutionInfoVo institutionInfoVo);

    void updateInsManageById (InstitutionInfoVo institutionInfoVo);

    /**
     * 查看机构是否有管理员
     * @param id
     * @return
     */
    InstitutionInfoVo checkUser(Integer id);

    /**
     * 根据userId查询机构信息
     * @param params
     * @return
     */
    InstitutionInfoVo queryInstitutionByUserId(Map<String,Object> params);
    /**
     * 判断机构名称是否重复
     * @param name
     * @return
     */
    InstitutionInfoVo insCheckName(String name);



}

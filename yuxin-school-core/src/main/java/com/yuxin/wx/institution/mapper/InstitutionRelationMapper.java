package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionRelationVo;

public interface InstitutionRelationMapper {

    void insert(InstitutionRelationVo institutionRelationVo);

    void delete(Integer id);

    void update(InstitutionRelationVo institutionRelationVo);

}

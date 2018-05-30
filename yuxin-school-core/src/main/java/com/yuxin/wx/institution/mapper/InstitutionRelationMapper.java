package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionRelationVo;

import java.util.List;

public interface InstitutionRelationMapper {

    void insert(InstitutionRelationVo institutionRelationVo);

    void delete(Integer id);

    void update(InstitutionRelationVo institutionRelationVo);

    //根据分类id，机构id，查询该机构是否为推荐状态
    InstitutionRelationVo findByinsIdOneIdTwoId(InstitutionRelationVo institutionRelationVo);

    List<InstitutionRelationVo> findByinsId(Integer insId);

    void deleteByInsIdOneIdTwoId(InstitutionRelationVo institutionRelationVo);

}

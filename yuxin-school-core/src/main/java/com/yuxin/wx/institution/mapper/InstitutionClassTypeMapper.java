package com.yuxin.wx.institution.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

/**
 * @author liutingrong
 */
public interface InstitutionClassTypeMapper {
    /**
     * 新建一个课程
     * @param entity
     */
    void insert(InstitutionClassTypeVo entity);

    /**
     * 批量新建课程
     * @param list
     */
    void batchInsert(List<InstitutionClassTypeVo> list);

    /**
     * 删除一个课程
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除课程
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新一个课程
     * @param entity
     */
    void update(InstitutionClassTypeVo entity);

    /**
     * 获取一个课程
     * @param id
     * @return
     */
    InstitutionClassTypeVo findById(Integer id);

    /**
     * 获取所有课程
     * @return
     */
    List<InstitutionClassTypeVo> queryAll();

    /**
     * 根据条件获取课程列表
     * @param map
     * @return
     */
    List<InstitutionClassTypeVo> page(Map<String, Object> map);

    /**
     * 根据条件获取课程数量
     * @param map
     * @return
     */
    int pageCount(Map<String, Object> map);

    /**
     * 根据机构id获取机构拥有推荐课程数量
     * @param insId
     * @return
     */
    int getRecommendCountByClassTypeId(Integer insId);

}

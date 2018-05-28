package com.yuxin.wx.institution.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.institution.ClassTypeOnlineFindVo;
import com.yuxin.wx.model.institution.InsClassRelationVO;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import com.yuxin.wx.model.institution.ClassTypeOnlineVo;

/**
 * @author liutingrong
 */
public interface InstitutionClassTypeMapper {
    /**
     * 新建一个课程
     *
     * @param entity
     */
    void insert(InstitutionClassTypeVo entity);

    /**
     * 批量新建课程
     *
     * @param list
     */
    void batchInsert(List<InstitutionClassTypeVo> list);

    /**
     * 删除一个课程
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除课程
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新一个课程
     *
     * @param entity
     */
    void update(InstitutionClassTypeVo entity);

    /**
     * 获取一个课程
     *
     * @param id
     * @return
     */
    InstitutionClassTypeVo findById(Integer id);

    /**
     * 获取所有课程
     *
     * @return
     */
    List<InstitutionClassTypeVo> queryAll();

    /**
     * 获取某个机构所有课程
     * @return
     */
    List<InstitutionClassTypeVo> queryAllByIns(InstitutionClassTypeVo institutionClassTypeVo);

    /**
     * 根据条件获取课程列表
     *
     * @param map
     * @return
     */
    List<InstitutionClassTypeVo> page(Map<String, Object> map);

    /**
     * 根据条件获取课程数量
     *
     * @param map
     * @return
     */
    int pageCount(Map<String, Object> map);

    /**
     * 根据机构id获取机构拥有推荐课程数量
     *
     * @param insId
     * @return
     */
    int getRecommendCountByClassTypeId(Integer insId);

    Integer findIsrecommendCount(Map<String, Object> map);

    /**
     * 根据条件获取在线课程列表信息
     *
     * @param map
     * @return
     */
    List<ClassTypeOnlineVo> pageOnline(Map<String, Object> map);

    /**
     * 根据条件获取在线课程数量
     *
     * @param map
     * @return
     */
    int pageOnlineCount(Map<String, Object> map);

    /**
     * 获取指定机构线上课程已关联的课程数量
     * @param map
     * @return
     */
    int getOnlineCount(Map<String, Object> map);


    /**
     * 搜索线上课程信息
     *
     * @param map
     * @return
     */
    List<ClassTypeOnlineFindVo> findOnlineClassType(Map<String, Object> map);

    /**
     * 删除机构课程映射表的映射信息
     *
     * @param map
     */
    void deleteRelation(Map<String, Object> map);

    /**
     * 根据关系映射id删除在线课程关联信息
     *
     * @param id
     */
    void deleteOnlineRelation(Integer id);

    /**
     * 添加一个在线课程
     *
     * @param map
     */
    void addOnlineClass(Map<String, Object> map);

    /**
     * 添加一个线下课程
     * @param map
     */
    void addUnderlineClass(Map<String, Object> map);

    InsClassRelationVO findRelationById(Integer id);

    void delRelationLink(Integer id);

    void updateSourtBantch(Integer insId);

    void updateSubSourtBantch(Map<String, Object> map);

    void addRelationLink(Integer id);

    void addSortRelationStep1(Map<String, Object> map);

    void  addSortRelationStep2(Integer id);

    void subSortRelationStep1(Map<String, Object> map);

    void  subSortRelationStep2(Integer id);


    int getCountOfOnlineClassyCidInsId(Map<String,Object> map);


    int countUnderllineClass(Map<String,Object> map);

}

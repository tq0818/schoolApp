package com.yuxin.wx.institution.mapper;


import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.institution.CaseWhenVO;
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

    List<InstitutionCategoryVo> findSecondCategorys();

    List<InstitutionCategoryVo> findCecondCategorys(Integer id);

    /**
     * 根据机构id查询机构分类
     *
     * @param id
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysByInsId(Integer id);

    /**
     * 获取首页分类推荐列表数量 （后台）
     *
     * @return
     */
    int queryCateListCount();


    /**
     * 获取首页分类推荐列表 （后台）
     *
     * @param map
     * @return
     */
    List<InstitutionCategoryVo> queryCateList(Map<String, Object> map);

    /**
     * 更新某个推荐分类的推荐状态
     *
     * @param map
     */
    void updateRecommendStatusById(Map<String, Object> map);

    /**
     *更新某个推荐分类的推荐状态  first_recommend
     * @param map
     */
    int updateRecommendStatusById1(Map<String, Object> map);


    InstitutionCategoryVo getCateById(Integer id);


    /**
     * 提高某个排名分类的排名
     *
     * @param sort
     */
    int increaseSort(Integer sort);

    /**
     * 提高某个排名分类的排名  sort3
     * @param sort
     * @return
     */
    int increaseSort3(Integer sort);

    /**
     * 提升某个分类排名后的所有分类的排名
     * @param sort
     */
    void increaseSortAfter(Integer sort);

    /**
     * 提升某个分类排名后的所有分类的排名3
     * @param sort
     */
    void increaseSortAfter3(Integer sort);


    /**
     * 指定降低某个排名的名次一点
     *
     * @param sort 要降低的排名
     */
    int reduceSort(Integer sort);

    /**
     * 指定降低某个排名的名次一点 , 针对sort3
     * @param sort
     * @return
     */
    int reduceSort3(Integer sort);

    /**
     * 更新某个分类的排名 key ： sort , id
     *
     * @param map
     */
    int updateSort(Map<String, Object> map);

    /**
     * 更新某个分类的排名 key ： sort , id
     * 针对sort3字段
     * @param map
     */
    int updateSort3(Map<String, Object> map);

    /**
     * 获取当前分类中是推荐状态的个数 first_recommend
     */
    int queryRecommendCount();

    /**
     * 获取当前分类中是推荐状态的个数  third_recommend
     * @return
     */
    int queryRecommendCount3();

    /**
     * 获取所有`is_enable` = 1  的分类信息
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysEnabled();

    /**
     * 获取所有`is_enable` = 1 ,first_recommend  的分类信息
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysEnabled1();

    /**
     * 查询分类总记录数
     * @param params
     * @return
     */
    int queryInstitutionCategorysCount(Map<String, Object> params);

    /**
     * 获取最小排序
     * @param insCatInfo
     * @return
     */
    Integer queryMinSortByIds(InstitutionCategoryVo insCatInfo);


    List<InstitutionCategoryVo> queryInstitutionCategorysAfterSort(Integer sort);

    /**
     * 首页列表推荐获取列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getIndexRecommendList(Map<String,Object> map);

    int getIndexRecommendListCount(Map<String,Object> map);

    /**
     * 更新首页列表推荐的某个机构的推荐状态
     * @param map
     * @return
     */
    int alterIndexRecommendStatusNo(Map<String,Object> map);

    /**
     *
     * @param map
     * @return
     */
    int alterIndexRecommendStatusYes(Map<String,Object> map);


    int increaseIndexRecommendAfterAll(List<CaseWhenVO> list);

    List<Map<String,Object>> getRelationAfterSortAll(Integer sort);

    /**
     *获取首页列表推荐的某个机构的信息
     * @param map
     * @return
     */
    Map<String,Object> getIndexRecommendInfo(Map<String,Object> map);

    List<Map<String, Object>> getIndexRecommendInfoList(Map<String,Object> map);

    /**
     * 获取当前推荐的机构数量
     * @return
     */
    int getIndexRecommendYesCountAll();

    /**
     *获取指定id的分类信息
     * @param id
     * @return
     */
    Map<String,Object> getTypeEntityById(Integer id);

    /**
     * 根据排名和类型id获取关联信息，适用于分类为一级分类
     * @param map
     * @return
     */
    Map<String,Object> getRelationByTypeIdSort1(Map<String,Object> map);

    /**
     * 根据排名和类型id获取关联信息，适用于分类为二级分类
     * @param map
     * @return
     */
    Map<String,Object> getRelationByTypeIdSort2(Map<String,Object> map);


    int exchangeSortByCaseWhen(List<CaseWhenVO> list);

    int getMaxSortByTypeId1(Integer typeId);

    int getMaxSortByTypeId2(Integer typeId);

    int exchangeSortIndexType(List<CaseWhenVO> list);


   Map<String,Object> getAroundRelation(Map<String,Object> map);

}

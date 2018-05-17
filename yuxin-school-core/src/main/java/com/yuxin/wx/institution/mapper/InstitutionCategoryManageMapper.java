package com.yuxin.wx.institution.mapper;


import com.yuxin.wx.common.BaseMapper;
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


    InstitutionCategoryVo getCateById(Integer id);


    /**
     * 提高某个排名分类的排名
     *
     * @param sort
     */
    void increaseSort(Integer sort);

    /**
     * 提升某个分类排名后的所有分类的排名，用在设置 is_enable = 0 的时候更新排名信息
     * @param sort
     */
    void increaseSortAfter(Integer sort);

    /**
     * 指定降低某个排名的名次一点
     *
     * @param sort 要降低的排名
     */
    void reduceSort(Integer sort);

    /**
     * 更新某个分类的排名 key ： sort , id
     *
     * @param map
     */
    void updateSort(Map<String, Object> map);

    /**
     * 获取当前分类中是推荐状态的个数
     */
    int queryRecommendCount();

    /**
     * 获取所有`is_enable` = 1  的分类信息
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysEnabled();

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

}

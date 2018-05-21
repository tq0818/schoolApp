package com.yuxin.wx.api.institution;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/8.
 * 机构分类管理
 */
public interface InstitutionCategoryManageService {


    /**
     * 查询机构分类信息 条件暂时自定义
     * @param params
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorys(Map<String, Object> params);


    /**
     * 保存机构分类信息
     * @param insCatInfo
     */
    void saveInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo);


    /**
     * 修改机构分类信息
     * @param insCatInfo
     */
    void updateInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo);

    /**
     * 根据条件查询分类信息 单条
     * @param params
     * @return
     */
    InstitutionCategoryVo queryInstitutionCategoryByCondition(Map<String, Object> params);

    /**
     * 根据机构id查询机构分类
     * @param id
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysByInsId(Integer id);


    /**
     * 获取首页分类推荐列表 （后台）
     * @param
     * @return
     */
    PageFinder<InstitutionCategoryVo> queryCateList(Integer pageStart , Integer pageSize);


    /**
     * 更新某个推荐分类的推荐状态 third_recommend
     * @param status 更新后的状态
     * @param id
     * @param oldSort   更新前的排序 ， 用于更新其他推荐状态的排序问题
     */
    void updateRecommendStatusById(Integer status , Integer id , Integer oldSort);

    /**
     * 更新某个推荐分类的推荐状态 first_recommend
     * @param status 更新后的状态
     * @param id
     * @param oldSort 更新前的排序 ， 用于更新其他推荐状态的排序问题
     */
    void updateRecommendStatusById1(Integer status , Integer id , Integer oldSort);

    /**
     * 根据id获取分类信息
     * @param id id
     * @return
     */
    InstitutionCategoryVo getCateById(Integer id);

    boolean updateSort(InstitutionCategoryVo entity , boolean isIncrease);

    /**
     * 获取当前是推荐状态的分类个数
     * @return
     */
    int queryRecommendCount();


    /**
     * 获取所有`is_enable` = 1  的分类信息
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysEnabled();

    /**
     * 获取所有`is_enable` = 1  , first_recommend 作为推荐标志 的分类信息
     * @return
     */
    List<InstitutionCategoryVo> queryInstitutionCategorysEnabled1();

    /**
     * 查询分类总记录
     * @param params
     * @return
     */
    int queryInstitutionCategorysCount(Map<String, Object> params);

    /**
     * 更新指定排名后的所有分类排名
     * @param baseSort
     * @return
     */
    int flushSortAll(Integer baseSort);


    List<Map<String,Object>> getIndexRecommendList(int typeId, String name,Integer status , int pageStart, int pageSize);

    int getIndexRecommendListCount(int typeId, String name,Integer status);

    /**
     * 获取指定分类的机构状态是推荐的个数
     * @param typeId
     * @param name
     * @return
     */
    int getIndexRecommendYesCount(int typeId, String name);


    /**
     * 更新首页列表推荐的某个机构的推荐状态
     * @param map
     * @return
     */
    int alterIndexRecommendStatus(Map<String,Object> map);

    /**
     * 根据分类id获取该分类下状态为推荐的机构个数
     * @param typeId
     * @return
     */
    int getIndexRecommendYesCount(Integer typeId);

    int getMaxSortByTypeId(Integer typeId);

    /**
     * 调整某个首页列表推荐的排序
     * @param typeId    当前分类id
     * @param rid   关联映射id
     * @param addFlag   提高或者降低排名
     * @return
     */
    boolean updateSort(Integer typeId,Integer rid,boolean addFlag);

}

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
     * 更新某个推荐分类的推荐状态
     * @param status 更新后的状态
     * @param id
     * @param oldSort   更新前的排序 ， 用于更新其他推荐状态的排序问题
     */
    void updateRecommendStatusById(Integer status , Integer id , Integer oldSort);

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
     * 查询分类总记录
     * @param params
     * @return
     */
    int queryInstitutionCategorysCount(Map<String, Object> params);
}

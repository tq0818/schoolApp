package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionCategoryManageMapper;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lym_gxm on 18/5/8.
 * 机构分类管理
 */
@Service
@Transactional
public class InstitutionCategoryManageServiceImpl extends BaseServiceImpl implements InstitutionCategoryManageService {

    @Autowired
    private InstitutionCategoryManageMapper institutionManageMapper;

    @Override
    public List<InstitutionCategoryVo> queryInstitutionCategorys(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategorys(params);
    }

    @Override
    public void saveInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo) {
        institutionManageMapper.insert(insCatInfo);
    }

    @Override
    public void updateInstitutionCategoryInfo(InstitutionCategoryVo insCatInfo) {
        institutionManageMapper.update(insCatInfo);
        //获取禁用ids最小排序
        Integer minSort = institutionManageMapper.queryMinSortByIds(insCatInfo);
        //更新排序
        this.flushSortAll(minSort);
    }

    @Override
    public InstitutionCategoryVo queryInstitutionCategoryByCondition(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategoryByCondition(params);
    }

    @Override
    public List<InstitutionCategoryVo> queryInstitutionCategorysByInsId(Integer id) {
        return institutionManageMapper.queryInstitutionCategorysByInsId(id);
    }


    @Override
    public PageFinder<InstitutionCategoryVo> queryCateList(Integer pageStart, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("firstIndex", pageStart * pageSize);
        map.put("pageSize", pageSize);
        //#{firstIndex},#{pageSize}
        try {
            List<InstitutionCategoryVo> list = institutionManageMapper.queryCateList(map);
            int count = institutionManageMapper.queryCateListCount();
            return new PageFinder<InstitutionCategoryVo>(pageStart, pageSize, count, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 更新指定分类的推荐状态
     *
     * @param status  更新后的状态
     * @param id      id
     * @param oldSort
     */
    @Override
    public void updateRecommendStatusById(Integer status, Integer id, Integer oldSort) {
        Map<String, Object> map = new HashMap<>();
        map.put("isRecommend", status);
        map.put("id", id);

        if (status == 0 && null != oldSort) {
            //取消分类推荐状态，此时需要更新排序
            institutionManageMapper.increaseSortAfter(oldSort);
        } else if (status == 1) {
            //将一个分类状态变为推荐
            //获取当前已经是推荐状态的分类的个数num，新的分类sort = num + 1
            int num = institutionManageMapper.queryRecommendCount();
            map.put("sort", num + 1);
            institutionManageMapper.updateSort(map);
        } else if (status == 0 && null == oldSort) {
            //取消一个分类的推荐状态，因为该分类排序为空，则无法更新其他分类排序
            institutionManageMapper.updateRecommendStatusById(map);
            return;
        } else {
            // status 参数传递错误
            return;
        }

        institutionManageMapper.updateRecommendStatusById(map);
    }

    /**
     * @param id id
     * @return
     */
    @Override
    public InstitutionCategoryVo getCateById(Integer id) {
        return institutionManageMapper.getCateById(id);
    }

    @Override
    public boolean updateSort(InstitutionCategoryVo entity, boolean isIncrease) {
        if (null == entity) {
            //实体为空，无法更新排序
            return false;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", entity.getId());
        try {
            if (isIncrease) {
                //提高某个分类的排序
                //提高自己的排名之前，先把自己前一位降低一个排名
                institutionManageMapper.reduceSort(entity.getSort() - 1);
                map.put("sort", entity.getSort() - 1);
                institutionManageMapper.updateSort(map);
                return true;
            } else {
                //降低排名
                //提高身后的某个分类的排名
                institutionManageMapper.increaseSort(entity.getSort() + 1);
                map.put("sort", entity.getSort() + 1);
                institutionManageMapper.updateSort(map);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


    @Override
    public int queryRecommendCount() {
        return institutionManageMapper.queryRecommendCount();
    }


    @Override
    public List<InstitutionCategoryVo> queryInstitutionCategorysEnabled() {
        return institutionManageMapper.queryInstitutionCategorysEnabled();
    }

    @Override
    public int queryInstitutionCategorysCount(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategorysCount(params);
    }

    /**
     *
     * @param baseSort
     * @return
     */
    @Override
    public int flushSortAll(Integer baseSort) {
       try{
           List<InstitutionCategoryVo> list =  institutionManageMapper.queryInstitutionCategorysAfterSort(baseSort);
           Map<String,Object> map = new HashMap<>();
           int num = 0;
           for(int i = 0;i<list.size();i++){
               map.put("sort",baseSort + i);
               map.put("id",list.get(i).getId());
               institutionManageMapper.updateSort(map);
               num ++ ;
           }
           return num;
       }catch(Exception e){
           e.printStackTrace();
       }
        return -1;
    }
}

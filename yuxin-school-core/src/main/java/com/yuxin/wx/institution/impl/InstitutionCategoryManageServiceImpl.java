package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionCategoryManageMapper;
import com.yuxin.wx.institution.mapper.InstitutionInfoMapper;
import com.yuxin.wx.model.institution.CaseWhenVO;
import com.yuxin.wx.model.institution.InstitutionCategoryVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by lym_gxm on 18/5/8.
 * 机构分类管理
 */
@Service
@Transactional
public class InstitutionCategoryManageServiceImpl extends BaseServiceImpl implements InstitutionCategoryManageService {

    private Log log = LogFactory.getLog("InstitutionCategoryManageServiceImpl");

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
     * 更新指定分类的推荐状态 first_recommend
     * @param status 更新后的状态
     * @param id
     * @param oldSort 更新前的排序 ， 用于更新其他推荐状态的排序问题
     */
    @Override
    public void updateRecommendStatusById1(Integer status, Integer id, Integer oldSort) {
        Map<String, Object> map = new HashMap<>();
        map.put("isRecommend", status);
        map.put("id", id);

        institutionManageMapper.updateRecommendStatusById1(map);
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
              int resultNum =  institutionManageMapper.reduceSort(entity.getSort() - 1);
                if(resultNum <= 0){
                    log.error("=====> 调整分类排序result = "+resultNum);
                    return false;
                }
                map.put("sort", entity.getSort() - 1);
                institutionManageMapper.updateSort(map);
                return true;
            } else {
                //降低排名
                //提高身后的某个分类的排名
              int resultNum =  institutionManageMapper.increaseSort(entity.getSort() + 1);
              if(resultNum <= 0){
                  log.error("=====> 调整分类排序result = "+resultNum);
                  return false;
              }
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
    public List<InstitutionCategoryVo> queryInstitutionCategorysEnabled1() {
        return institutionManageMapper.queryInstitutionCategorysEnabled1();
    }

    @Override
    public int queryInstitutionCategorysCount(Map<String, Object> params) {
        return institutionManageMapper.queryInstitutionCategorysCount(params);
    }

    /**
     * @param baseSort
     * @return
     */
    @Override
    public int flushSortAll(Integer baseSort) {
        try {
            if(baseSort <= 0){
                return -1;
            }

            //查询所有en_abled = 1的数据，全部更新
            List<InstitutionCategoryVo> list = institutionManageMapper.queryInstitutionCategorysAfterSort(baseSort);
            List<CaseWhenVO> voList = new LinkedList<>();
            CaseWhenVO whenVO = null;
            int sort = 1;
            for(InstitutionCategoryVo vo : list){
                whenVO = new CaseWhenVO();
                whenVO.setId(vo.getId());
                whenVO.setSort(sort++);
                voList.add(whenVO);
            }
           /* Map<String, Object> map = new HashMap<>();
            int num = 0;
            for (int i = 0; i < list.size(); i++) {
                map.put("sort", baseSort + i);
                map.put("id", list.get(i).getId());
                institutionManageMapper.updateSort(map);
                num++;
            }*/

            institutionManageMapper.exchangeSortIndexType(voList);

            return (null == list ? 0 : list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Map<String, Object>> getIndexRecommendList(int typeId, String name,Integer status, int pageStart, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeId", typeId);
        map.put("name", name);
        map.put("pageStart", pageStart);
        map.put("pageSize", pageSize);
        map.put("status",status);
        // int count = institutionManageMapper.getIndexRecommendListCount(map);
        List<Map<String, Object>> list = institutionManageMapper.getIndexRecommendList(map);

        return list;
    }

    @Override
    public int getIndexRecommendListCount(int typeId, String name,Integer status) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeId", typeId);
        map.put("typeName", (null == name || "".equals(name.trim())) ? null : name);
        map.put("status",status);

        return institutionManageMapper.getIndexRecommendListCount(map);
    }

    @Override
    public int alterIndexRecommendStatus(Map<String, Object> map) {
        //获取当前分类信息，用于判断排序算法
        Map<String,Object> typeEntity = institutionManageMapper.getTypeEntityById((Integer) map.get("typeId"));
        if (null == typeEntity ) {
            return -1;
        }

        //获取中间表排序数据
        Map<String, Object> info = institutionManageMapper.getIndexRecommendInfo(map);
        if (null == info) {
            return -1;
        }

        if (0 == (Integer) info.get("is_recommend")) {
            //将一个机构置为推荐状态的时候需要更新排名信息
            //获取指定分类下当前推荐状态为1的数量
            int count = institutionManageMapper.getIndexRecommendYesCountAll();
            map.put("sort", count + 1);
            return institutionManageMapper.alterIndexRecommendStatusYes(map);
        } else {
            //将一个机构置为不推荐状态的时候需要更新排其后的所有机构排名
            //获取指定排名后的所有中间表排名信息
            Integer sort = (Integer) info.get("sort");
            List<Map<String,Object>> list = institutionManageMapper.getRelationAfterSortAll(sort);
            if(null == list || list.size() == 0){
                return institutionManageMapper.alterIndexRecommendStatusNo(map);
            }

            List<CaseWhenVO> caseWhenVOList = new LinkedList<>();
            CaseWhenVO vo = null;
            for(Map<String,Object> tempMap : list){
                vo = new CaseWhenVO();
                vo.setId((Integer)tempMap.get("id"));
                vo.setSort(sort++);
                caseWhenVOList.add(vo);
            }
            //更新排序
            institutionManageMapper.increaseIndexRecommendAfterAll(caseWhenVOList);

            return institutionManageMapper.alterIndexRecommendStatusNo(map);
        }


    }

    /**
     * 获取指定分类的机构状态是推荐的个数
     *
     * @param typeId
     * @param name
     * @return
     */
    @Override
    public int getIndexRecommendYesCount(int typeId, String name) {

        return 0;
    }


    @Override
    public int getIndexRecommendYesCount(Integer typeId) {
        return institutionManageMapper.getIndexRecommendYesCountAll();
       /*
        Map<String,Object> typeEntity = institutionManageMapper.getTypeEntityById(typeId);
        if (null == typeEntity ) {
            return -1;
        }
        //几级分类
        int type = (Integer)typeEntity.get("code_level");

        if(type == 1){
            //1级分类

        }else{
            //2级分类
            return institutionManageMapper.getIndexRecommendYesCount2(typeId);
        }
*/
    }

    /**
     *
     * @param typeId    当前分类id
     * @param rid   关联映射id
     * @param addFlag   提高或者降低排名
     * @return
     */
    @Override
    public boolean updateSort(Integer typeId, Integer rid, boolean addFlag) {
        //获取当前分类信息，用于判断排序算法
        Map<String,Object> typeEntity = institutionManageMapper.getTypeEntityById(typeId);
        if (null == typeEntity ) {
            return false;
        }
        Map<String,Object> param = new HashMap<>();
        param.put("rid",rid);
        Map<String, Object> info = institutionManageMapper.getIndexRecommendInfo(param);
        if (null == info || info.get("sort") == null) {
            return false;
        }

        //当前要更新的关联信息的排序
        Integer sort = (Integer)info.get("sort");
        //判断分类是一级分类还是二级分类
        int type = (Integer)typeEntity.get("code_level") ;
        //获取该分类下的相邻的一个排序实体，sort不一定挨着
        Map<String,Object> aroundMap = new HashMap<>();
        aroundMap.put("type",type);
        aroundMap.put("typeId",typeId);
        aroundMap.put("flag",addFlag ? 1 : 0);
        aroundMap.put("sort",sort);

        Map<String,Object> aroundRelation = institutionManageMapper.getAroundRelation(aroundMap);
        if(null == aroundRelation){
            log.error("调整排序,获取周围排序失败，跳过调整 ......");
            return false;
        }


        //前一个或者后一个的排名信息
        int sort2 = (Integer)aroundRelation.get("sort");

        List<CaseWhenVO> list = new ArrayList<>(2); //已知list需要的长度，直接指定list的长度
        list.add(new CaseWhenVO(rid,sort2));
        list.add(new CaseWhenVO((Integer)aroundRelation.get("id"),sort));


        int result = institutionManageMapper.exchangeSortByCaseWhen(list);

        return true;
    }


    @Override
    public int getMaxSortByTypeId(Integer typeId) {
        //获取当前分类信息
        Map<String,Object> typeEntity = institutionManageMapper.getTypeEntityById(typeId);
        //判断分类是一级分类还是二级分类
        int type = (Integer)typeEntity.get("code_level") ;
        Integer maxSort = 0;
        if(type == 1){
            //分类是一级分类
           Integer  temp =  institutionManageMapper.getMaxSortByTypeId1(typeId);
           maxSort = maxSort > temp ? maxSort : temp;
        }else{
            Integer  temp =  institutionManageMapper.getMaxSortByTypeId2(typeId);
            maxSort = maxSort > temp ? maxSort : temp;
        }
        return maxSort;
    }
}

package com.yuxin.wx.institution.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.institution.mapper.InstitutionStyleMapper;
import com.yuxin.wx.model.institution.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionClassTypeMapper;

@Service
@Transactional
public class InstitutionClassTypeServiceImpl extends BaseServiceImpl implements InstitutionClassTypeService {

    @Autowired
    private InstitutionStyleMapper styleMapper;

    @Autowired
    private InstitutionClassTypeMapper institutionClassTypeMapper;

    @Override
    public void insert(InstitutionClassTypeVo entity) {
        institutionClassTypeMapper.insert(entity);
    }

    @Override
    public void batchInsert(List<InstitutionClassTypeVo> list) {
        institutionClassTypeMapper.batchInsert(list);
    }

    @Override
    public void deleteById(Integer id) {
        institutionClassTypeMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        institutionClassTypeMapper.deleteByIds(ids);
    }

    @Override
    public void update(InstitutionClassTypeVo entity) {
        institutionClassTypeMapper.update(entity);
    }

    @Override
    public InstitutionClassTypeVo findById(Integer id) {
        return institutionClassTypeMapper.findById(id);
    }

    @Override
    public List<InstitutionClassTypeVo> queryAll() {
        return institutionClassTypeMapper.queryAll();
    }

    @Override
    public List<InstitutionClassTypeVo> queryAllByIns(InstitutionClassTypeVo institutionClassTypeVo) {
        return institutionClassTypeMapper.queryAllByIns(institutionClassTypeVo);
    }


    @Override
    public PageFinder<InstitutionClassTypeVo> page(Integer insId, Integer status, int pageStart, int pageSize) {
        Map<String, Object> map = new HashMap<>();

        //重新赋值课程状态，前端传递参数0代表全部，1代表已经上线，2代表下线
        status = (status == 0 ? null : (status == 1 ? 1 : 0));

        map.put("isShelves", status);
        map.put("insId", insId);
        map.put("firstIndex", pageStart * pageSize);
        map.put("pageSize", pageSize);
        //#{firstIndex},#{pageSize}
        try {
            int count = institutionClassTypeMapper.pageCount(map);
            List<InstitutionClassTypeVo> list = institutionClassTypeMapper.page(map);

            return new PageFinder<InstitutionClassTypeVo>(pageStart, pageSize, count, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Integer findIsrecommendCount(Map<String, Object> map) {
        return institutionClassTypeMapper.findIsrecommendCount(map);
    }


    @Override
    public int getRecommendCountByClassTypeId(Integer insId) {
        return institutionClassTypeMapper.getRecommendCountByClassTypeId(insId);
    }


    /**
     * 根据条件获取在线课程列表信息
     *
     * @param map
     * @return
     */
    @Override
    public List<ClassTypeOnlineVo> pageOnline(Map<String, Object> map) {
        return institutionClassTypeMapper.pageOnline(map);
    }

    /**
     * 根据条件获取在线课程数量
     *
     * @param map
     * @return
     */
    @Override
    public int pageOnlineCount(Map<String, Object> map) {
        return institutionClassTypeMapper.pageOnlineCount(map);
    }


    @Override
    public int getOnlineCount(Map<String, Object> map) {
        return institutionClassTypeMapper.getOnlineCount(map);
    }

    /**
     * 查询在线课程,包含是否关联
     *
     * @param map
     * @return
     */
    @Override
    public List<ClassTypeOnlineFindVo> findOnlineClassType(Map<String, Object> map) {
        return institutionClassTypeMapper.findOnlineClassType(map);
    }

    /**
     * 删除映射表中映射信息
     *
     * @param map
     */
    @Override
    public void deleteRelation(Map<String, Object> map) {
        institutionClassTypeMapper.deleteRelation(map);
    }


    /**
     * 根据关系映射id删除在线课程关联信息
     *
     * @param id
     */
    @Override
    public void deleteOnlineRelation(Integer id) {
        institutionClassTypeMapper.deleteOnlineRelation(id);
    }


    /**
     * 添加一个在线课程
     *
     * @param map
     */
    @Override
    public void addOnlineClass(Map<String, Object> map) {
        institutionClassTypeMapper.addOnlineClass(map);
    }

    /**
     * 关联或者取消关联线上课程并更新排序信息
     *
     * @param insId
     * @param
     * @param rid
     * @return
     */
    @Override
    public boolean linkOpenClass(Integer insId, Integer rid) {
        InsClassRelationVO entity = institutionClassTypeMapper.findRelationById(rid);
        //关联信息为空或者不是该机构的
        if (null == entity || entity.getInsId() - insId != 0) {
            return false;
        }

        try {

            if (entity.getIsLink() == 0) {
                //新增一个关联课程

                //将原来的关联课程sort依次 +1
                institutionClassTypeMapper.updateSourtBantch(insId);
                //将该关联信息置为关联状态
                institutionClassTypeMapper.addRelationLink(entity.getId());

                return true;

            } else {

                Map<String, Object> map = new HashMap<>();
                map.put("sort", entity.getSort());
                map.put("insId", insId);
                institutionClassTypeMapper.updateSubSourtBantch(map);

                institutionClassTypeMapper.delRelationLink(entity.getId());

                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean updateSortOpenClass(Integer insId, Integer rid, String method) {
        try {
            InsClassRelationVO entity = institutionClassTypeMapper.findRelationById(rid);
            //关联信息为空或者不是该机构的
            if (null == entity || entity.getInsId() - insId != 0) {
                return false;
            }

            if ("add".equals(method)) {
                Map<String, Object> map = new HashMap<>();
                map.put("sort", entity.getSort() - 1);
                map.put("insId", insId);

                institutionClassTypeMapper.addSortRelationStep1(map);
                institutionClassTypeMapper.addSortRelationStep2(entity.getId());
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("sort", entity.getSort() + 1);
                map.put("insId", insId);

                institutionClassTypeMapper.subSortRelationStep1(map);
                institutionClassTypeMapper.subSortRelationStep2(entity.getId());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public int getCountOfOnlineClassyCidInsId(Map<String, Object> map) {
        return institutionClassTypeMapper.getCountOfOnlineClassyCidInsId(map);
    }

    @Override
    public void addStyle(InstitutionStyle entity) {

        styleMapper.insertInsStyle(entity);

    }

    @Override
    public List<InstitutionStyle> getStyleByClassId(Integer cid) {
        InstitutionStyle search = new InstitutionStyle();
        search.setRelationId(cid);
        search.setIsVideo(0);
        search.setSourceFlag(1);
        search.setType(2);

         return styleMapper.queryInstitutionStyle(search);
    }

    @Override
    public void updateStyle(InstitutionStyle entity) {
        styleMapper.updateInsStyle(entity);
    }

    @Override
    public void delStyle(Integer sid) {
        styleMapper.deleteInsStyle(sid);
    }

    @Override
    public void addUnderlineClass(Map<String, Object> map) {
        institutionClassTypeMapper.addUnderlineClass(map);
    }

    @Override
    public int countUnderllineClass(Integer insId, String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("insId",insId);
        map.put("name",name);
        return institutionClassTypeMapper.countUnderllineClass(map);
    }


    @Override
    public int countUnderllineClass(Integer insId, String name, Integer classId) {
        Map<String,Object> map = new HashMap<>();
        map.put("insId",insId);
        map.put("name",name);
        map.put("classId",classId);
        return institutionClassTypeMapper.countUnderllineClass(map);
    }
}

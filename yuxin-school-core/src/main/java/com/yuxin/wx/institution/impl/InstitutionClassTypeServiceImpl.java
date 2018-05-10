package com.yuxin.wx.institution.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionClassTypeMapper;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

@Service
@Transactional
public class InstitutionClassTypeServiceImpl extends BaseServiceImpl implements InstitutionClassTypeService {

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
    public PageFinder<InstitutionClassTypeVo> page(Integer insId, Integer status, int pageStart, int pageSize) {
        Map<String, Object> map = new HashMap<>();

        //重新赋值课程状态，前端传递参数0代表全部，1代表已经上线，2代表下线
        status = (status == 0 ? null : (status == 1 ? 1 : 0));

        map.put("isShelves", status);
        map.put("insId", insId);
        map.put("firstIndex", pageStart*pageSize);
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
    public int getRecommendCountByClassTypeId(Integer insId) {
        return institutionClassTypeMapper.getRecommendCountByClassTypeId(insId);
    }

}

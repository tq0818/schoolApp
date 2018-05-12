package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionTeacherService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.institution.mapper.InstitutionTeacherMapper;
import com.yuxin.wx.model.institution.InstitutionTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InstitutionTeacherServiceImpl extends BaseServiceImpl implements InstitutionTeacherService {
    @Autowired
    private InstitutionTeacherMapper institutionClassTypeMapper;

    @Override
    public List<InstitutionTeacher> loadTeacherByInstitutionId(Integer insId) {
        return institutionClassTypeMapper.loadTeacherByInstitutionId(insId);
    }

    @Override
    public void insert(InstitutionTeacher entity) {
        if(null !=  entity){
            institutionClassTypeMapper.insert(entity);
        }

    }

    @Override
    public void batchInsert(List<InstitutionTeacher> list) {
        if(null != list && list.size() != 0){
            institutionClassTypeMapper.batchInsert(list);
        }
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
    public void update(InstitutionTeacher entity) {
        if(null != entity){
            institutionClassTypeMapper.update(entity);
        }
    }

    @Override
    public InstitutionTeacher findById(Integer id) {
        return institutionClassTypeMapper.findById(id);
    }

    @Override
    public List<InstitutionTeacher> queryAll() {
        return institutionClassTypeMapper.queryAll();
    }


    @Override
    public InstitutionTeacher getTeacherByTidInsId(Map<String, Object> map) {
        return institutionClassTypeMapper.getTeacherByTidInsId(map);
    }

    @Override
    public boolean addTeacher(InstitutionTeacher teacher, Integer insId) {
        int insertNum = institutionClassTypeMapper.insert(teacher);
        Integer tid = teacher.getId();
        System.out.println("=======> 新增机构名师id为 : "+tid);
        if(insertNum != 1){
            return false;
        }

        if(null == tid){
            return false;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("tid",tid);
        map.put("insId",insId);

        institutionClassTypeMapper.addTeacherInsRelation( map);

        return true;
    }
}

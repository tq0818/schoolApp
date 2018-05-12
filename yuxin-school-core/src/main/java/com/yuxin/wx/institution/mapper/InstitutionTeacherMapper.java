package com.yuxin.wx.institution.mapper;

import com.yuxin.wx.model.institution.InstitutionTeacher;

import java.util.List;
import java.util.Map;

/**
 * @author liutingrong
 */
public interface InstitutionTeacherMapper {

    /**
     * 获取指定机构的名师列表
     *
     * @param insId
     * @return
     */
    List<InstitutionTeacher> loadTeacherByInstitutionId(Integer insId);

    /**
     * 新建一个机构名师
     * @param entity
     */
    int insert(InstitutionTeacher entity);

    /**
     * 批量新建
     * @param list
     */
    void batchInsert(List<InstitutionTeacher> list);

    /**
     * 删除指定id的名师
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新一个名师
     * @param entity
     */
    void update(InstitutionTeacher entity);

    /**
     * 查询指定id的名师
     * @param id
     * @return
     */
    InstitutionTeacher findById(Integer id);

    /**
     * 查询所有的名师
     * @return
     */
    List<InstitutionTeacher> queryAll();

    /**
     * 根据名师id，机构id获取名师信息
     * @param map
     * @return
     */
    InstitutionTeacher getTeacherByTidInsId(Map<String,Object> map);


    int addTeacherInsRelation(Map<String,Object> map);


}

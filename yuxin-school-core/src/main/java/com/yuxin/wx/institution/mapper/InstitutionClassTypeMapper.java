package com.yuxin.wx.institution.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

/**
 * 
 * @author liutingrong
 *
 */
//extends BaseMapper<InstitutionClassTypeVo>
public interface InstitutionClassTypeMapper   {
	public void insert(InstitutionClassTypeVo entity);
	public void batchInsert(List<InstitutionClassTypeVo> list);
	public void deleteById(Integer id);
	public void deleteByIds(List<Integer> ids);
	public void update(InstitutionClassTypeVo entity);
	public InstitutionClassTypeVo findById(Integer id);
	public List<InstitutionClassTypeVo> queryAll();
	
	public List<InstitutionClassTypeVo> page(Map<String,Object> map);
	
	public int pageCount(Map<String,Object> map);
	
	
}

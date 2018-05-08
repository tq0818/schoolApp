package com.yuxin.wx.api.institution;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

public interface InstitutionClassTypeService {
	 void insert(InstitutionClassTypeVo entity);
	 void batchInsert(List<InstitutionClassTypeVo> list);
	 void deleteById(Integer id);
	 void deleteByIds(List<Integer> ids);
	 void update(InstitutionClassTypeVo entity);
	 InstitutionClassTypeVo findById(Integer id);
	 List<InstitutionClassTypeVo> queryAll();
	
	 PageFinder<InstitutionClassTypeVo> page(Integer status,int pageStart,int pageSize);
	
}

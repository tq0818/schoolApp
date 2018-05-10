package com.yuxin.wx.api.institution;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

public interface InstitutionClassTypeService {
	 /**
	  * 新建课程
	  * @param entity
	  */
	 void insert(InstitutionClassTypeVo entity);
	 /**
	  * 批量新建课程
	  * @param list
	  */
	 void batchInsert(List<InstitutionClassTypeVo> list);
	 /**
	  * 根据课程Id删除课程
	  * @param id
	  */
	 void deleteById(Integer id);
	 /**
	  * 根据课程ID批量删除课程
	  * @param ids
	  */
	 void deleteByIds(List<Integer> ids);
	 /**
	  * 更新课程
	  * @param entity
	  */
	 void update(InstitutionClassTypeVo entity);
	 /**
	  * 根据课程id获取课程
	  * @param id
	  * @return
	  */
	 InstitutionClassTypeVo findById(Integer id);
	 /**
	  * 获取所有课程
	  * @return
	  */
	 List<InstitutionClassTypeVo> queryAll();
	
	
	 PageFinder<InstitutionClassTypeVo> page(Integer insId,Integer status,int pageStart,int pageSize);


	int getRecommendCountByClassTypeId(Integer insId);

	
}

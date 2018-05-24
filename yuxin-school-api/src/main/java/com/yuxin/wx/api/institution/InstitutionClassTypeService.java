package com.yuxin.wx.api.institution;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.institution.ClassTypeOnlineFindVo;
import com.yuxin.wx.model.institution.ClassTypeOnlineVo;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;
import com.yuxin.wx.model.institution.InstitutionStyle;

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

	/**
	 * 获取某个机构所有课程
	 * @return
	 */
	List<InstitutionClassTypeVo> queryAllByIns(InstitutionClassTypeVo institutionClassTypeVo);
	
	
	 PageFinder<InstitutionClassTypeVo> page(Integer insId,Integer status,int pageStart,int pageSize);


	int getRecommendCountByClassTypeId(Integer insId);


	/**
	 * 根据条件获取在线课程列表信息
	 * @param map
	 * @return
	 */
	List<ClassTypeOnlineVo> pageOnline(Map<String, Object> map);

	/**
	 * 根据条件获取在线课程数量
	 * @param map
	 * @return
	 */
	int pageOnlineCount(Map<String, Object> map);

	/**
	 * 获取指定机构线上课程已关联的课程数量
	 * @param map
	 * @return
	 */
	int getOnlineCount(Map<String, Object> map);

	/**
	 * 获取线上课程列表信息
	 * @param map
	 * @return
	 */
	List<ClassTypeOnlineFindVo> findOnlineClassType(Map<String, Object> map);

	/**
	 * 删除映射表中映射信息
	 * @param map
	 */
	void deleteRelation(Map<String, Object> map);
	/**
	 * 根据关系映射id删除在线课程关联信息
	 * @param id
	 */
	void deleteOnlineRelation(Integer id);

	/**
	 * 添加一个在线课程
	 * @param map
	 */
	void addOnlineClass(Map<String, Object> map);

	/**
	 * 添加一个线下课程
	 * @param map
	 */
	void addUnderlineClass(Map<String, Object> map);

	/**
	 * 关联或者取消关联课程，并更新排序
	 * @param insId
	 * @param rid
	 * @return
	 */
	boolean  linkOpenClass(Integer insId,Integer rid);


	boolean  updateSortOpenClass(Integer insId,Integer rid,String method);

	/**
	 *
	 * @param map
	 * @return
	 */
	int getCountOfOnlineClassyCidInsId(Map<String,Object> map);


	void addStyle(InstitutionStyle entity);

	/**
	 * 获取某个线下课程的风采列表
	 * @param cid
	 * @return
	 */
	List<InstitutionStyle> getStyleByClassId(Integer cid);


	void updateStyle(InstitutionStyle entity);

	void delStyle(Integer sid);

	int countUnderllineClass(Integer insId,String name);


}

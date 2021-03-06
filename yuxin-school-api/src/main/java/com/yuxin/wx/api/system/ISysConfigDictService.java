package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigDict;
/**
 * Service Interface:SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigDictService  {
	/**
	 * 
	* @Title: saveSysConfigDict
	* @Description: 新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigDict sysConfigDict);
	
	/**
	 * 
	* @Title: batchSaveSysConfigDict 
	* @Description: 批量新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigDict> sysConfigDict);
	
	/**
	 * 
	* @Title: updateSysConfigDict 
	* @Description: 编辑SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigDict sysConfigDict);
	
	/**
	 * 
	* @Title: deleteSysConfigDictById 
	* @Description: 根据id删除SysConfigDict
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigDictById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigDictByIds 
	* @Description: 根据id批量删除SysConfigDict
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigDictByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigDict findSysConfigDictById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigDict findSysConfigDictByCode(SysConfigDict dict);
	
	/**
	 * 
	* @Title: findSysConfigDictByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigDict>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigDict> findSysConfigDictByPage(SysConfigDict search);

	List<SysConfigDict> findSysConfigDictList();
	
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 查询教室相关 字典表
	 * @author 周文斌
	 * @date 2015-5-6 下午2:23:57
	 * @version 1.0
	 * @return
	 */
	List<SysConfigDict> findDictByClassroom();
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 根据dict_code查询
	 * @author 权飞虎
	 * @date 2015年5月12日 下午7:05:42
	 * @modifier
	 * @modify-date 2015年5月12日 下午7:05:42
	 * @version 1.0
	 * @param code
	 * @return
	 */
	List<SysConfigDict> findByDicCode(String code);
	
	List<SysConfigDict> findConfigDictList();
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-22 下午2:31:20
	 * @modifier
	 * @modify-date 2015-5-22 下午2:31:20
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigDict> findByCompanyId(Integer companyId);
	
    List<SysConfigDict> findAll();
    
    List<SysConfigDict> queryConfigDictListByDictCode(SysConfigDict sysConfigDict);
	SysConfigDict queryConfigDictValue(SysConfigDict sysConfigDict);

    List<SysConfigDict> querySchoolListByStepCode(SysConfigDict areaDict);

    List<SysConfigDict> findSchoolBySchoolType(Map<String, Object> map);
}
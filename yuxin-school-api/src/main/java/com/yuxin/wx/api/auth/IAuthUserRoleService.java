package com.yuxin.wx.api.auth;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;

/**
 * Service Interface:AuthUserRole
 * @author wang.zx
 * @date 2015-1-26
 */
public interface IAuthUserRoleService  {
	/**
	 * 
	* @Title: saveAuthUserRole
	* @Description: 新增AuthUserRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void insert(AuthUserRole T);
	
	/**
	 * 
	* @Title: batchSaveAuthUserRole 
	* @Description: 批量新增AuthUserRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void batchInsert(List<AuthUserRole> T);
	
	/**
	 * 
	* @Title: updateAuthUserRole 
	* @Description: 编辑AuthUserRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void update(AuthUserRole T);
	
	/**
	 * 
	* @Title: deleteAuthUserRoleById 
	* @Description: 根据id删除AuthUserRole
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthUserRoleById(Integer id);
	
	/**
	 * 
	* @Title: deleteAuthUserRoleByIds 
	* @Description: 根据id批量删除AuthUserRole
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthUserRoleByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findAuthUserRoleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	AuthUserRole findAuthUserRoleById(Integer id);
	
	/**
	 * 
	* @Title: findAuthUserRoleByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthUserRole>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	List<AuthUserRole> findAuthUserRoleByPage(AuthUserRole search);
	
	/**
	 * 
	 * Class Name: IAuthUserRoleService.java
	 * @Description: 根据用户获取权限列表
	 * @author Chopin
	 * @date 2015年2月1日
	 * @version 1.0
	 * @param userId
	 * @return
	 */
	String findUserRoles(Integer userId);
	/**
	 * 
	 * Class Name: IAuthUserRoleService.java
	 * @Description: 根据用户获取权限列表
	 * @author Chopin
	 * @date 2015年2月1日
	 * @version 1.0
	 * @param userId
	 * @return
	 */
	Set<String> queryUserRoles(String userName);
	
 /**
  * 
  * Class Name: IAuthUserRoleService.java
  * @Description: 根据角色获取权限列表
  * @author Chopin
  * @date 2015年2月1日
  * @version 1.0
  * @param id
  * @return
  */
 List<AuthUserRole> findByRoleId(String userId);
 /**
  * Class Name: IAuthUserRoleService.java
  * @Description: 获取用户权限列表
  * @author Chopin
  * @param userId
  * @return
  */
 Set<String> findUserPermissions(String userName);
 
 /**
  * 
  * Class Name: IAuthUserRoleService.java
  * @Description: 根据id删除用户角色信息
  * @author zhang.zx
  * @date 2015年12月22日 下午4:22:29
  * @modifier
  * @modify-date 2015年12月22日 下午4:22:29
  * @version 1.0
  * @param id
  */
 void deleteByRoleId(Integer id);

/**
 * 查询角色ID
 * @param params
 * @return
 */
List<AuthUserRole> queryRoleIds(Map<String,Object> params);
}
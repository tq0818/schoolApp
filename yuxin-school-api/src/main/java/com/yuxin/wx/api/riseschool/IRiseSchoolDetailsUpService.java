package com.yuxin.wx.api.riseschool;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.base.IBaseService;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.SysDictVo;

/**
 * 学校详情及升学情况
 */
public interface IRiseSchoolDetailsUpService{
	/**
	 * @Description: 新增RiseSchoolDetailsUp
	 */
	void insert(RiseSchoolDetailsUp riseSchoolDetailsUp);
	/**
	 * @Description: 修改RiseSchoolDetailsUp
	 */
	void update(RiseSchoolDetailsUp riseSchoolDetailsUp);
	/**
	 * @Description: 根据学校id和字典编码查询
	 */
	RiseSchoolDetailsUp findByidAndCode(Map map);
	/**
	 * 回显内容
	 * @param map
	 * @return
	 */
	RiseSchoolDetailsUp queryRiseDetails(Map map);
	/**
	 * 根据名称找字典表
	 * @param name
	 * @return
	 */
	SysDictVo findsysDictByName(Map map);
	/**
	 * 当前学校全部编辑了的内容
	 */
	List<RiseSchoolDetailsUp> queryAllRiseDetails(Map map);
}

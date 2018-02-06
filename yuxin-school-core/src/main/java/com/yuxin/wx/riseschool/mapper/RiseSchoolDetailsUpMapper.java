package com.yuxin.wx.riseschool.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.SysDictVo;

/**
 * 学校详情及升学情况
 */
public interface RiseSchoolDetailsUpMapper extends BaseMapper<RiseSchoolDetailsUp> {
	/**
	 * 根据学校id和字典code查询
	 */
	RiseSchoolDetailsUp findByidAndCode(Map map);
	/**
	 * 回显内容
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
	List<RiseSchoolDetailsUp> queryAllRiseDetails(Integer shcoolId);
}

package com.yuxin.wx.riseschool.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;

/**
 * 动态
 */
public interface RiseSchoolDynamicMapper extends BaseMapper<RiseSchoolDynamicVo> {
	/**
	 * 查询全部
	 */
	List<RiseSchoolDynamicVo> queryAllDynamic(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * 查询总数
	 */
	Integer queryAllDynamicCount(Integer schoolId);
	/**
	 * @Description: 新增
	 */
	void insert(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * @Description: 修改
	 */
	void update(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * 查看
	 * @param id
	 * @return
	 */
	RiseSchoolDynamicVo checkDynamic(Integer id);
	/**
	 * 删除
	 */
	void deleteDynamic(Integer id);
}

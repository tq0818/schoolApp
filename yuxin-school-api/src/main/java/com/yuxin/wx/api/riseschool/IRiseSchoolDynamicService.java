package com.yuxin.wx.api.riseschool;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;

/**
 * 学校动态
 */
public interface IRiseSchoolDynamicService {
	/**
	 * @Description: 新增
	 */
	void insert(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * @Description: 修改
	 */
	void update(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * @Description: 根据学校id
	 */
	RiseSchoolDynamicVo checkDynamic(Integer id);
	/**
	 * 查询全部
	 */
	PageFinder<RiseSchoolDynamicVo> queryAllDynamic(RiseSchoolDynamicVo riseSchoolDynamic);
	/**
	 * 删除
	 */
	void deleteDynamic(Integer id);
}

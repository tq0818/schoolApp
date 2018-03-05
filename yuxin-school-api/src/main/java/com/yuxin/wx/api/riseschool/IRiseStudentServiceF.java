package com.yuxin.wx.api.riseschool;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.RiseStudentVo;

/**
 * 后台学员管理
 */
public interface IRiseStudentServiceF {
	/**
	 * 查询全部学生
	 */
	List<RiseStudentVo> queryAllStudent(Map map);
	/**
	 * 查询全部学校
	 * @return
	 */
	List<RiseSchoolManageVo> queryAllSchool();
}

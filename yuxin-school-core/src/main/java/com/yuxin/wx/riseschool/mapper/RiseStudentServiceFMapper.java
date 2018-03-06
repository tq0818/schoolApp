package com.yuxin.wx.riseschool.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.RiseStudentVo;

/**
 * 后台学员管理
 */
public interface RiseStudentServiceFMapper extends BaseMapper<RiseSchoolDynamicVo> {
	/**
	 * 查询全部
	 */
	List<RiseStudentVo> queryAllStudent(RiseStudentVo riseStudent);
	/**
	 * 查询全部学校
	 * @return
	 */
	List<RiseSchoolManageVo> queryAllSchool();
	/**
	 * 查询个数
	 * @return
	 */
	Integer queryAllStudentCount(RiseStudentVo riseStudent);
	void passStudent(Map map);
	RiseStudentVo findById(String id);
	String findSchoolNo(String id);
	//学生人数
	String findStudentCount();
	//通过更新状态
	void updateIsCheck(String id);
}

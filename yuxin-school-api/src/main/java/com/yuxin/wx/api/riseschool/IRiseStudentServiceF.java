package com.yuxin.wx.api.riseschool;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.riseschool.RiseEduExperience;
import com.yuxin.wx.model.riseschool.RiseNopassReason;
import com.yuxin.wx.model.riseschool.RisePersonalHonor;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.RiseStudentVo;
import com.yuxin.wx.model.user.UsersFront;

/**
 * 后台学员管理
 */
public interface IRiseStudentServiceF {
	/**
	 * 查询全部学生
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
	//查询学生信息
	RiseStudentVo findById(String id);
	//学校编号
	String findSchoolNo(String id);
	//学生人数
	String findStudentCount();
	//通过更新状态
	void updateIsCheck(String id);
	void updateIsCheckNoPass(RiseNopassReason reason);
	//查询教育经历
	List<RiseEduExperience> findExperience(String id);
	//查询个人荣誉
	List<RisePersonalHonor> findHonor(String id);
	//不通过原因
	List<RiseNopassReason> queryNoPass();
	UsersFront findUserByStudentId(Integer id);
}

package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.IRiseStudentServiceF;
import com.yuxin.wx.model.riseschool.RiseEduExperience;
import com.yuxin.wx.model.riseschool.RiseNopassReason;
import com.yuxin.wx.model.riseschool.RisePersonalHonor;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.RiseStudentVo;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.riseschool.mapper.RiseStudentServiceFMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台学员管理
 */
@Service
@Transactional
public class RiseStudentServiceFImpl implements IRiseStudentServiceF{
	@Autowired
	private RiseStudentServiceFMapper riseStudentServiceFMapper;

	@Override
	public List<RiseStudentVo> queryAllStudent(RiseStudentVo riseStudent) {
		List<RiseStudentVo> list = riseStudentServiceFMapper.queryAllStudent(riseStudent);
		return list;
	}

	@Override
	public List<RiseSchoolManageVo> queryAllSchool() {
		List<RiseSchoolManageVo> list = riseStudentServiceFMapper.queryAllSchool();
		return list;
	}

	@Override
	public Integer queryAllStudentCount(RiseStudentVo riseStudent) {
		Integer count = riseStudentServiceFMapper.queryAllStudentCount(riseStudent);
		return count;
	}

	@Override
	public void passStudent(Map map) {
		riseStudentServiceFMapper.passStudent(map);
		
	}

	@Override
	public RiseStudentVo findById(String id) {
		RiseStudentVo riseStudentVo = riseStudentServiceFMapper.findById(id);
		return riseStudentVo;
	}

	@Override
	public String findSchoolNo(String id) {
		String schoolNo = riseStudentServiceFMapper.findSchoolNo(id);
		return schoolNo;
	}

	@Override
	public String findStudentCount() {
		String studentCount = riseStudentServiceFMapper.findStudentCount();
		return studentCount;
	}

	@Override
	public void updateIsCheck(String id) {
		riseStudentServiceFMapper.updateIsCheck(id);
		
	}

	@Override
	public List<RiseEduExperience> findExperience(String id) {
		List<RiseEduExperience> experience = riseStudentServiceFMapper.findExperience(id);
		return experience;
	}

	@Override
	public List<RisePersonalHonor> findHonor(String id) {
		List<RisePersonalHonor> findHonor = riseStudentServiceFMapper.findHonor(id);
		return findHonor;
	}

	@Override
	public List<RiseNopassReason> queryNoPass() {
		List<RiseNopassReason> noPass = riseStudentServiceFMapper.queryNoPass();
		return noPass;
	}

	@Override
	public void updateIsCheckNoPass(RiseNopassReason reason) {
		riseStudentServiceFMapper.updateIsCheckNoPass(reason);
		
	}

	@Override
	public UsersFront findUserByStudentId(Integer id) {
		UsersFront usersFront = riseStudentServiceFMapper.findUserByStudentId(id);
		return usersFront;
	}

}

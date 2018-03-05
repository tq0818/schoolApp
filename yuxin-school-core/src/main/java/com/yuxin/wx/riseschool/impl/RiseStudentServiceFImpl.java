package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.IRiseStudentServiceF;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.RiseStudentVo;
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
	public List<RiseStudentVo> queryAllStudent(Map map) {
		List<RiseStudentVo> list = riseStudentServiceFMapper.queryAllStudent(map);
		return list;
	}

	@Override
	public List<RiseSchoolManageVo> queryAllSchool() {
		List<RiseSchoolManageVo> list = riseStudentServiceFMapper.queryAllSchool();
		return list;
	}

}

package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.IRiseSchoolDetailsUpService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.riseschool.RiseSchoolDetailsUp;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.riseschool.mapper.RiseSchoolDetailsUpMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学校详情及升学情况
 */
@Service
@Transactional
public class RiseSchoolDetailsUpImpl implements IRiseSchoolDetailsUpService{
	@Autowired
	private RiseSchoolDetailsUpMapper riseSchoolDetailsUpMapper;
	
	@Override
	public void insert(RiseSchoolDetailsUp riseSchoolDetailsUp) {
		riseSchoolDetailsUpMapper.insert(riseSchoolDetailsUp);
		
	}

	@Override
	public void update(RiseSchoolDetailsUp riseSchoolDetailsUp) {
		riseSchoolDetailsUpMapper.update(riseSchoolDetailsUp);
		
	}
	/**
	 * 根据学校id和字典code查询
	 */
	@Override
	public RiseSchoolDetailsUp findByidAndCode(Map map) {
		RiseSchoolDetailsUp findByidAndCode = riseSchoolDetailsUpMapper.findByidAndCode(map);
		return findByidAndCode;
	}
	/**
	 * 回显内容
	 */
	@Override
	public RiseSchoolDetailsUp queryRiseDetails(Map map) {
		RiseSchoolDetailsUp findByidAndCode = riseSchoolDetailsUpMapper.queryRiseDetails(map);
		return findByidAndCode;
	}

	@Override
	public SysDictVo findsysDictByName(Map map) {
		SysDictVo sysDictVo = riseSchoolDetailsUpMapper.findsysDictByName(map);
		return sysDictVo;
	}

	@Override
	public List<RiseSchoolDetailsUp> queryAllRiseDetails(Integer shcoolId) {
		List<RiseSchoolDetailsUp> list = riseSchoolDetailsUpMapper.queryAllRiseDetails(shcoolId);
		return list;
	}

}

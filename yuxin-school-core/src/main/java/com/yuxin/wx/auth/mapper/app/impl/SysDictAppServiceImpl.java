package com.yuxin.wx.auth.mapper.app.impl;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassType;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.app.ISysDictAppService;
import com.yuxin.wx.auth.mapper.app.SysDictAppMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.app.SysDictApp;

@Service
@Transactional
public class SysDictAppServiceImpl extends BaseServiceImpl implements ISysDictAppService {
	@Autowired
	private SysDictAppMapper sysDictAppMapper;

	@Override
	public List<SysDictApp> findSysDictAppByParentId(SysDictApp sda) {
		List<SysDictApp> list = sysDictAppMapper.findSysDictAppByParentId(sda);
		return list;
	}

/*	@Override
	public List<SysDictApp> findSysDictAppFirst() {
		List<SysDictApp> list = sysDictAppMapper.findSysDictAppFirst();
		return list;
	}*/


}

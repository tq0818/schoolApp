package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.IRiseSchoolDynamicService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolDynamicVo;
import com.yuxin.wx.riseschool.mapper.RiseSchoolDynamicMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 动态
 */
@Service
@Transactional
public class RiseSchoolDynamicServiceImpl implements IRiseSchoolDynamicService{
	@Autowired
	private RiseSchoolDynamicMapper riseSchoolDynamicMapper;

	@Override
	public void insert(RiseSchoolDynamicVo riseSchoolDynamic) {
		riseSchoolDynamicMapper.insert(riseSchoolDynamic);
	}

	@Override
	public void update(RiseSchoolDynamicVo riseSchoolDynamic) {
		riseSchoolDynamicMapper.update(riseSchoolDynamic);
		
	}

	@Override
	public RiseSchoolDynamicVo checkDynamic(Integer id) {
		RiseSchoolDynamicVo dynamicVo = riseSchoolDynamicMapper.checkDynamic(id);
		return dynamicVo;
	}

	@Override
	public PageFinder<RiseSchoolDynamicVo> queryAllDynamic(RiseSchoolDynamicVo riseSchoolDynamic) {
		riseSchoolDynamic.setPageSize(10);
		//动态
		List<RiseSchoolDynamicVo> list = riseSchoolDynamicMapper.queryAllDynamic(riseSchoolDynamic);
		for (RiseSchoolDynamicVo riseSchoolDynamicVo : list) {
			String updateTime = riseSchoolDynamicVo.getUpdateTime();
			String s = updateTime.substring(0, updateTime.length()-5);
			riseSchoolDynamicVo.setUpdateTime(s);
		}
		//总条数
		Integer count = riseSchoolDynamicMapper.queryAllDynamicCount(riseSchoolDynamic.getRiseSchoolId());
		PageFinder<RiseSchoolDynamicVo> pageFinder = new PageFinder<RiseSchoolDynamicVo>(riseSchoolDynamic.getPage(),riseSchoolDynamic.getPageSize(),count,list); 
		return pageFinder;
	}

	@Override
	public void deleteDynamic(Integer id) {
		riseSchoolDynamicMapper.deleteDynamic(id);
	}
	
	

}

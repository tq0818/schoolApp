package com.yuxin.wx.auth.mapper.app;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.app.AppTagVo;
import com.yuxin.wx.model.app.SysDictApp;



public interface SysDictAppMapper extends BaseMapper<SysDictApp>{
	List<SysDictApp> findSysDictAppByParentId(SysDictApp sda);

	/*List<SysDictApp> findSysDictAppFirst();*/


	List<SysDictApp> getStudySectionById(Integer modelId);

	List<SysDictApp> findSysDictAppByParentById(Integer modelId);

	String  getModelById(Integer modelId);
	
	List<SysDictApp> findSysDictAppByCode(Map<String,Object>code);

	List<AppTagVo> queryAppTagList();
}

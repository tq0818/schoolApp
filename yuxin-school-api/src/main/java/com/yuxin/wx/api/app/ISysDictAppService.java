package com.yuxin.wx.api.app;

import java.util.List;
import java.util.Map;


import com.yuxin.wx.model.app.AppTagVo;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.commodity.CommodityVo;


public interface ISysDictAppService {
	
	
	
	List<SysDictApp> findSysDictAppByParentId(SysDictApp sda);

	/*List<SysDictApp> findSysDictAppFirst();*/

	
	List<SysDictApp> getStudySectionById(Integer modelId);

	String  getModelById(Integer modelId);

	List<SysDictApp> findSysDictAppByCode(String code);

	/**
	 * 查询appTags列表
	 * @return
     */
	List<AppTagVo> queryAppTagList();

	/**
	 * 改变开关状态
	 * @param params
     */
	void changeTheSwith(Map<String, Object> params);
}

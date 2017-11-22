package com.yuxin.wx.api.app;

import java.util.List;


import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.commodity.CommodityVo;


public interface ISysDictAppService {
	
	
	
	List<SysDictApp> findSysDictAppByParentId(SysDictApp sda);

	/*List<SysDictApp> findSysDictAppFirst();*/

	
	List<SysDictApp> getStudySectionById(Integer modelId);

	String  getModelById(Integer modelId);

	
	
}

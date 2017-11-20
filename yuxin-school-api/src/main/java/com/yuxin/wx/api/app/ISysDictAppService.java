package com.yuxin.wx.api.app;

import java.util.List;


import com.yuxin.wx.model.app.SysDictApp;


public interface ISysDictAppService {
	
	
	
	List<SysDictApp> findSysDictAppByParentId(SysDictApp sda);

	/*List<SysDictApp> findSysDictAppFirst();*/
	
	
}

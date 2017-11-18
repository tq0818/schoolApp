package com.yuxin.wx.api.app;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.base.IBaseService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;

public interface ISysDictAppService {
	
	
	
	List<SysDictApp> findSysDictAppByParentId(Integer id);
	
	
}

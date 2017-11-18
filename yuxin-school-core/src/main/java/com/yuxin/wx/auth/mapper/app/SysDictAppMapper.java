package com.yuxin.wx.auth.mapper.app;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.model.classes.ClassType;



public interface SysDictAppMapper extends BaseMapper<SysDictApp>{
	List<SysDictApp> findSysDictAppByParentId(Integer id);
}

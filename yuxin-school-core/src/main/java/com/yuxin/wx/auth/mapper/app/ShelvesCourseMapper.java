package com.yuxin.wx.auth.mapper.app;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.app.ShelvesCourse;
import com.yuxin.wx.model.app.SysDictApp;
import com.yuxin.wx.model.commodity.CommoditySpecial;
import com.yuxin.wx.vo.classes.ClassTypeVo;

public interface ShelvesCourseMapper extends BaseMapper<ShelvesCourse>{
	public List<ShelvesCourse> queryShelvesCoursesByPage(Map<String, Object> map);
}

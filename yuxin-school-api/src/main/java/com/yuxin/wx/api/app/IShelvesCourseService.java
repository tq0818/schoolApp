package com.yuxin.wx.api.app;

import java.io.Serializable;
import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.app.ShelvesCourse;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.CommoditySpecial;
import com.yuxin.wx.vo.classes.ClassTypeVo;

public interface IShelvesCourseService {
	PageFinder<ClassTypeVo> queryShelvesCoursesByPage(
			String categoryid,
			String gradeid,
			String subjectid,
			String knowledgeid,
			String knowledgeProid,
			String stageid,
			String typeCode,
			Integer pageNum,
			Integer pageSize,
			Integer firstIndex
			);
	
	void update (ClassTypeVo classTypeVo);
	void updateAll (ClassTypeVo classTypeVo);
//	void updateAll (List<ClassTypeVo> list);
	
	ShelvesCourse findOne(Serializable id);
}

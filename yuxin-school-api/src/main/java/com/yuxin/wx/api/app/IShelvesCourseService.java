package com.yuxin.wx.api.app;

import java.io.Serializable;
import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.app.ShelvesCourse;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.CommoditySpecial;
import com.yuxin.wx.vo.classes.ClassTypeVo;

public interface IShelvesCourseService {
	public List<ShelvesCourse> queryShelvesCoursesByPage(
			String categoryName,
			String gradeName,
			String subjectName,
			String knowledgeName,
			String knowledgeProName,
			String stageName,
			String typeCode
			);
	
	void update (ClassTypeVo classTypeVo);
	
	ShelvesCourse findOne(Serializable id);
}

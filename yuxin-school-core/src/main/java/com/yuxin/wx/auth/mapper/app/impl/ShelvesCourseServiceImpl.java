package com.yuxin.wx.auth.mapper.app.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.app.IShelvesCourseService;
import com.yuxin.wx.auth.mapper.app.ShelvesCourseMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.app.ShelvesCourse;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.CommoditySpecial;
import com.yuxin.wx.vo.classes.ClassTypeVo;
@Service
@Transactional
public class ShelvesCourseServiceImpl implements IShelvesCourseService{
	@Autowired
	private ShelvesCourseMapper shelvesCourseMapper;

	@Override
	public PageFinder<ClassTypeVo> queryShelvesCoursesByPage(
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
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryid", categoryid);
		map.put("gradeid", gradeid);
		map.put("subjectid", subjectid);
		map.put("knowledgeid", knowledgeid);
		map.put("knowledgeProid", knowledgeProid);
		map.put("stageid", stageid);
		map.put("typeCode", typeCode);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("firstIndex", firstIndex);

		
		List<ClassTypeVo> list = shelvesCourseMapper.queryShelvesCoursesByPage(map);

		int rowCount = shelvesCourseMapper.queryShelvesCoursesCount(map);

		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(pageNum, pageSize, rowCount, list);
		return pageFinder;
	}

	@Override
	public void update(ClassTypeVo classTypeVo) {
		shelvesCourseMapper.update(classTypeVo);
		
	}

	@Override
	public ShelvesCourse findOne(Serializable id) {
		
		return shelvesCourseMapper.findOne(id);
	}

	@Override
	public void updateAll(ClassTypeVo classTypeVo) {
		shelvesCourseMapper.updateAll(classTypeVo);
		
	}
	

}

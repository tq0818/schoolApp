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
	public List<ShelvesCourse> queryShelvesCoursesByPage(
			String categoryName,
			String gradeName,
			String subjectName,
			String knowledgeName,
			String knowledgeProName,
			String stageName,
			String typeCode
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryName", categoryName);
		map.put("gradeName", gradeName);
		map.put("subjectName", subjectName);
		map.put("knowledgeName", knowledgeName);
		map.put("knowledgeProName", knowledgeProName);
		map.put("stageName", stageName);
		map.put("typeCode", typeCode);
		
		List<ShelvesCourse> list = shelvesCourseMapper.queryShelvesCoursesByPage(map);
		return list;
	}

	@Override
	public void update(ClassTypeVo classTypeVo) {
		shelvesCourseMapper.update(classTypeVo);
		
	}

	@Override
	public ShelvesCourse findOne(Serializable id) {
		
		return shelvesCourseMapper.findOne(id);
	}
	

}

package com.yuxin.wx.institution.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.institution.InstitutionClassTypeService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionClassTypeMapper;
import com.yuxin.wx.model.institution.InstitutionClassTypeVo;

@Service
@Transactional
public class InstitutionClassTypeServiceImpl extends BaseServiceImpl implements InstitutionClassTypeService {

	@Autowired
	private InstitutionClassTypeMapper institutionClassTypeMapper;

	@Override
	public void insert(InstitutionClassTypeVo entity) {
		institutionClassTypeMapper.insert(entity);
	}

	@Override
	public void batchInsert(List<InstitutionClassTypeVo> list) {
		institutionClassTypeMapper.batchInsert(list);
	}

	@Override
	public void deleteById(Integer id) {
		institutionClassTypeMapper.deleteById(id);
	}

	@Override
	public void deleteByIds(List<Integer> ids) {
		institutionClassTypeMapper.deleteByIds(ids);
	}

	@Override
	public void update(InstitutionClassTypeVo entity) {
		institutionClassTypeMapper.update(entity);
	}

	@Override
	public InstitutionClassTypeVo findById(Integer id) {
		return institutionClassTypeMapper.findById(id);
	}

	@Override
	public List<InstitutionClassTypeVo> queryAll() {
		return institutionClassTypeMapper.queryAll();
	}

	@Override
	public PageFinder<InstitutionClassTypeVo> page(Integer insId,Integer status, int pageStart, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("isShelves", status);
		map.put("insId", insId);
		int count = institutionClassTypeMapper.pageCount(map);
		List<InstitutionClassTypeVo> list = institutionClassTypeMapper.page(map);
		
		return new PageFinder<InstitutionClassTypeVo>(pageStart, pageSize, count, list);
	}

}

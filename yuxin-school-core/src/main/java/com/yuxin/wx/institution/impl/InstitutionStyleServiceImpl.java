package com.yuxin.wx.institution.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.riseschool.InstitutionStyleService;
import com.yuxin.wx.institution.mapper.InstitutionStyleMapper;
import com.yuxin.wx.model.institution.InsVideo;
import com.yuxin.wx.model.institution.InstitutionStyle;
@Service
@Transactional
public class InstitutionStyleServiceImpl implements InstitutionStyleService {
	@Autowired
	private InstitutionStyleMapper institutionStyleMapper;
	@Override
	public void insertInsStyle(InstitutionStyle institutionStyle) {
		institutionStyleMapper.insertInsStyle(institutionStyle);
	}

	@Override
	public void updateInsStyle(InstitutionStyle institutionStyle) {
		institutionStyleMapper.updateInsStyle(institutionStyle);
	}

	@Override
	public void deleteInsStyle(Integer primaryId) {
		institutionStyleMapper.deleteInsStyle(primaryId);
	}

	@Override
	public void deleteInsVideo(Integer relationId) {
		institutionStyleMapper.deleteInsVideo(relationId);
	}

	@Override
	public List<InstitutionStyle> queryInstitutionStyle(InstitutionStyle institutionStyle) {
		return institutionStyleMapper.queryInstitutionStyle(institutionStyle);
	}

	@Override
	public Integer queryInstitutionStyleCount(InstitutionStyle institutionStyle) {
		return institutionStyleMapper.queryInstitutionStyleCount(institutionStyle);
	}

	@Override
	public void insertInsVideo(InsVideo insVideo) {
		institutionStyleMapper.insertInsVideo(insVideo);
	}

	@Override
	public void updateInsVideo(InsVideo insVideo) {
		institutionStyleMapper.updateInsVideo(insVideo);
	}

	@Override
	public InsVideo queryInsVideo(InsVideo insVideo) {
		return institutionStyleMapper.queryInsVideo(insVideo);
	}

	@Override
	public InsVideo queryInsVideoByCcId(String videoCcId) {
		return institutionStyleMapper.queryInsVideoByCcId(videoCcId);
	}

	@Override
	public Integer queryInsVideoIdBySourceId(Integer sourceId) {
		return institutionStyleMapper.queryInsVideoIdBySourceId(sourceId);
	}

}

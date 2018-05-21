package com.yuxin.wx.institution.mapper;

import java.util.List;

import com.yuxin.wx.model.institution.InsVideo;
import com.yuxin.wx.model.institution.InstitutionStyle;

public interface InstitutionStyleMapper {
	void insertInsStyle(InstitutionStyle institutionStyle);
	void updateInsStyle(InstitutionStyle institutionStyle);
	void deleteInsStyle(Integer primaryId);
	void deleteInsVideo(Integer relationId);
	List<InstitutionStyle> queryInstitutionStyle(InstitutionStyle institutionStyle);
	Integer queryInstitutionStyleCount(InstitutionStyle institutionStyle);
	void insertInsVideo(InsVideo insVideo);
	void updateInsVideo(InsVideo insVideo);
	InsVideo queryInsVideo(InsVideo insVideo);
	InsVideo queryInsVideoByCcId(String videoCcId);
	Integer queryInsVideoIdBySourceId(Integer sourceId);
}

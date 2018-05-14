package com.yuxin.wx.api.riseschool;

import java.util.List;

import com.yuxin.wx.model.institution.InsVideo;
import com.yuxin.wx.model.institution.InstitutionStyle;

public interface InstitutionStyleService {
	/**
	 * 新增风采
	 * @param institutionStyle
	 */
	void insertInsStyle(InstitutionStyle institutionStyle);
	/**
	 * 更新风采
	 * @param institutionStyle
	 */
	void updateInsStyle(InstitutionStyle institutionStyle);
	/**
	 * 删除风采
	 * @param primaryId
	 */
	void deleteInsStyle(Integer primaryId);
	/**
	 * 删除视频信息
	 * @param relationId
	 */
	void deleteInsVideo(Integer relationId);
	/**
	 * 查询风采信息
	 * @param institutionStyle
	 * @return
	 */
	List<InstitutionStyle> queryInstitutionStyle(InstitutionStyle institutionStyle);
	/**
	 * 查询风采总数
	 * @param institutionStyle
	 * @return
	 */
	Integer queryInstitutionStyleCount(InstitutionStyle institutionStyle);
	/**
	 * 新增视频
	 * @param insVideo
	 */
	void insertInsVideo(InsVideo insVideo);
	/**
	 * 更新视频信息
	 * @param insVideo
	 */
	void updateInsVideo(InsVideo insVideo);
}

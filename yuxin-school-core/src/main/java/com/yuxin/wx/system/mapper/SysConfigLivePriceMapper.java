package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigLivePrice;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigLivePriceMapper extends BaseMapper<SysConfigLivePrice> {
	
	List<SysConfigLivePrice> findList();
	
	List<SysConfigLivePrice> findLivesLowerEast();
}
package com.yuxin.wx.vo.app;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.vo.commodity.CommodityVo;

public class SerchResultVO extends BaseEntity{
	/**
	 * 直播
	 */
	private List<CommodityVo> zbList;
	/**
	 * 录播
	 */
	private List<CommodityVo> lbList;
	/**
	 * 升学
	 */
	private List<CommodityVo> sxList;
	/**
	 * 真题
	 */
	private List<CommodityVo> ztList;
	public List<CommodityVo> getZbList() {
		return zbList;
	}
	public void setZbList(List<CommodityVo> zbList) {
		this.zbList = zbList;
	}
	public List<CommodityVo> getLbList() {
		return lbList;
	}
	public void setLbList(List<CommodityVo> lbList) {
		this.lbList = lbList;
	}
	public List<CommodityVo> getSxList() {
		return sxList;
	}
	public void setSxList(List<CommodityVo> sxList) {
		this.sxList = sxList;
	}
	public List<CommodityVo> getZtList() {
		return ztList;
	}
	public void setZtList(List<CommodityVo> ztList) {
		this.ztList = ztList;
	}
	
}

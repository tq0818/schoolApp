package com.yuxin.wx.vo.commodity;

import java.io.Serializable;
import java.util.List;

import com.yuxin.wx.vo.app.SysDictAppVO;

public class HomePageOResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CommodityVo> homeShowList;//首页列表结果
	private List<SysDictAppVO> categoryList;//分类结果
	public List<CommodityVo> getHomeShowList() {
		return homeShowList;
	}
	public void setHomeShowList(List<CommodityVo> homeShowList) {
		this.homeShowList = homeShowList;
	}
	public List<SysDictAppVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<SysDictAppVO> categoryList) {
		this.categoryList = categoryList;
	}

}

package com.yuxin.wx.api.banner;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.banner.Banner;

/**
 * 
 * @author jishangyang 2017年12月1日 下午1:54:31
 * @ClassName IBannerService
 * @Description 
 * @version V1.0
 */
public interface IBannerService  {
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午11:25:54
	 * @Method: findBannerAll 
	 * @Description: 根据状态查询banner信息
	 * @param banner
	 * @return 
	 * @throws
	 */
	List <Banner> findBannerAll(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午11:26:51
	 * @Method: findBannerPage 
	 * @Description: 分页查询banner信息
	 * @param banner
	 * @return 
	 * @throws
	 */
	PageFinder <Banner> findBannerPage(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月2日 上午1:05:08
	 * @Method: addBanner 
	 * @Description: 保存banner信息
	 * @param banner 
	 * @throws
	 */
	Integer addBanner(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月2日 上午10:46:55
	 * @Method: findBannerById 
	 * @Description:通过ID 查询banner信息
	 * @param id
	 * @return 
	 * @throws
	 */
	Banner findBannerById(Integer id);
	/**
	 * 
	 * @author jishangyang 2017年12月2日 上午11:20:14
	 * @Method: update 
	 * @Description: 更新banner信息
	 * @param banner
	 * @return 
	 * @throws
	 */
	void update(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午4:51:31
	 * @Method: sort 
	 * @Description: 排序
	 * @param banner 
	 * @param biaoshi 
	 * @throws
	 */
	void sort(Banner banner,Integer biaoshi);
}
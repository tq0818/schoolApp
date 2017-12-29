package com.yuxin.wx.banner.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.banner.Banner;

/**
 * 
 * @author jishangyang 2017年12月1日 下午2:01:02
 * @ClassName BannerMapper
 * @Description 
 * @version V1.0
 */
public interface BannerMapper extends BaseMapper<Banner> {
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午11:47:48
	 * @Method: findAllBanner 
	 * @Description: 查询启用状态数据
	 * @param banner
	 * @return 
	 * @throws
	 */
	List<Banner>findAllBanner(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月1日 下午11:47:54
	 * @Method: queryMessageList 
	 * @Description: 分页查询禁用状态数据
	 * @param banner
	 * @return 
	 * @throws
	 */
	List<Banner>queryMessageList(Banner banner);
	int queryMessageCount(Banner banner);
	/**
	 * 
	 * @author jishangyang 2017年12月2日 上午1:07:01
	 * @Method: insertBanner 
	 * @Description: 添加banner信息
	 * @param banner 
	 * @throws
	 */
	Integer insertBanner(Banner banner);
	
	/**
	 * 
	 * @author jishangyang 2017年12月2日 下午5:03:52
	 * @Method: sort 
	 * @Description: 排序
	 * @param banner
	 * @return 
	 * @throws
	 */
	Integer sortAsc(Banner banner);
	Integer sortAsc1(Banner banner);
	Integer sortDesc(Banner banner);
	Integer sortDesc1(Banner banner);
	Banner searchNum(Integer id);
	Banner searchNum1(Integer id);

	/**
	 * 查询最大排序
	 */
	Integer searchMaxNum();
}
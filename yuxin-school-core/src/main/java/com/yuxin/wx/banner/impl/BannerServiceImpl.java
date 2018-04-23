package com.yuxin.wx.banner.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.banner.IBannerService;
import com.yuxin.wx.banner.mapper.BannerMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.banner.Banner;
import com.yuxin.wx.model.classes.ClassType;

/**
 * Service Implementation:QuestionAnswer
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@Service
@Transactional
public class BannerServiceImpl extends BaseServiceImpl implements IBannerService {

    @Autowired
    private BannerMapper BannerMapper;

	@Override
	public List<Banner> findBannerAll(Banner banner) {
		List<Banner> list = BannerMapper.findAllBanner(banner);
		
		return list;
	}

	@Override
	public PageFinder<Banner> findBannerPage(Banner banner) {
		
		List<Banner> data=BannerMapper.queryMessageList(banner);
		int count=BannerMapper.queryMessageCount(banner);
		PageFinder<Banner> pageFinder=new PageFinder<Banner>(banner.getPage(), banner.getPageSize(), count, data);
		return pageFinder;
	}
	@Override
	public PageFinder<Banner> findAcrcoBanner(Banner banner) {
		
		List<Banner> data=BannerMapper.findAcrcoBanner(banner);
		int count=BannerMapper.countAcrcoBanner(banner);
		PageFinder<Banner> pageFinder=new PageFinder<Banner>(banner.getPage(), banner.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public Integer addBanner(Banner banner) {

		Integer sortNum = BannerMapper.searchMaxNum();
		if(null==sortNum){
			sortNum = 0;
		}else{
			sortNum+=1;
		}
		banner.setOrderByNum(sortNum);
		return BannerMapper.insertBanner(banner);
	}

	@Override
	public Banner findBannerById(Integer id) {
		return BannerMapper.findById(id);
	}

	@Override
	public void update(Banner banner) {
		BannerMapper.update(banner);
	}

	@Override
	public void sort(Banner banner, Integer biaoshi) {
		int num=banner.getOrderByNum();
		Banner bannervo=new Banner();
		if(biaoshi==1){
			 bannervo=BannerMapper.searchNum(banner.getOrderByNum());
			
		}else{
			 bannervo=BannerMapper.searchNum1(banner.getOrderByNum());
		}
		banner.setOrderByNum(bannervo.getOrderByNum());
		BannerMapper.update(banner); 
		bannervo.setOrderByNum(num);
		BannerMapper.update(bannervo);  
		
	}

	@Override
	public void forbiddenBanner() {
		BannerMapper.forbiddenBanner();
		
	}

	@Override
	public List<ClassType> findClassByName(String calssName) {
		List<ClassType> list = BannerMapper.findClassByName(calssName);
		return list;
	}

	@Override
	public Integer querHomeBannerCount() {
		// TODO Auto-generated method stub
		return BannerMapper.querHomeBannerCount();
	}
    
    
   

  
}

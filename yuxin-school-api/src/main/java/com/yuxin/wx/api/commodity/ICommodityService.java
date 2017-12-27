package com.yuxin.wx.api.commodity;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.vo.app.VideoInfoVO;
import com.yuxin.wx.vo.commodity.CommodityDto;
import com.yuxin.wx.vo.commodity.CommodityVo;

/**
 * Service Interface:Commodity
 * @author wang.zx
 * @date 2015-3-14
 */
public interface ICommodityService {
	/**
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: saveCommodity
	 * @Description: 新增Commodity
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	void insert(Commodity T);

	/**
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: batchSaveCommodity
	 * @Description: 批量新增Commodity
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	void batchInsert(List<Commodity> T);

	/**
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: updateCommodity
	 * @Description: 编辑Commodity
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	void update(Commodity T);

	/**
	 * @param id
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: deleteCommodityById
	 * @Description: 根据id删除Commodity
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	void deleteCommodityById(Integer id);

	/**
	 * @param ids
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: deleteCommodityByIds
	 * @Description: 根据id批量删除Commodity
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	void deleteCommodityByIds(Integer[] ids);

	/**
	 * @param id
	 * @return void    返回类型
	 * @throws
	 * @throws
	 * @Title: findCommodityById
	 * @Description: 根据id查询
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	Commodity findCommodityById(Integer id);

	/**
	 * @return List<Commodity>    返回类型
	 * @throws
	 * @throws
	 * @Title: findCommodityByPage
	 * @Description: 分页查询
	 * @date 2015-3-14
	 * @user by wangzx
	 */
	List<Commodity> findCommodityByPage(Commodity search);

	/**
	 * @return List<Commodity>    返回类型
	 * @throws
	 * @throws
	 * @Title: findCommodityByPage
	 * @Description: 根据自定义条件查询
	 * @date 2015-3-12
	 * @user by chopin
	 */
	List<CommodityVo> searchCommodityByItemOne(Integer schoolId, Integer itemOneId);

	/**
	 * @return List<Commodity>    返回类型
	 * @throws
	 * @throws
	 * @Title: findCommodityByPage
	 * @Description: 根据一、二级项目
	 * @date 2015-3-12
	 * @user by chopin
	 */
	List<CommodityVo> searchCommodityByItemTwo(Integer schoolId, Integer itemOneId, Integer itemSecondId);

	/**
	 * @return List<Commodity>    返回类型
	 * @throws
	 * @throws
	 * @Title: findCommodityByPage
	 * @Description: 根据自定义条件查询商品，校区Id不能为空
	 * @date 2015-3-12
	 * @user by chopin
	 */
	List<CommodityVo> searchCommodityList(CommodityVo search);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param search
	 * @return PageFinder<Commodity> 返回类型
	 * @Description: 分页和条件查询商品
	 * @author zhang.zx
	 * @date 2015-4-8
	 * @modifier
	 * @version 1.0
	 */
	PageFinder<CommodityVo> queryCommodityByPage(CommodityVo search);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param search
	 * @return PageFinder<Commodity> 返回类型
	 * @Description: 分页和条件查询商品
	 * @author chopin
	 * @date 2015-4-8
	 * @modifier
	 * @version 1.0
	 */
	public List<CommodityVo> loadData(Map search);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param search
	 * @return PageFinder<Commodity> 返回类型
	 * @Description: 根据自定义条件查询商品
	 * @author chopin
	 * @date 2015-4-8
	 * @modifier
	 * @version 1.0
	 */
	public List<CommodityVo> loadData(CommodityVo search);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param search
	 * @return PageFinder<Commodity> 返回类型
	 * @Description: 根据班型ID查询商品
	 * @author chopin
	 * @date 2015-4-8
	 * @modifier
	 * @version 1.0
	 */
	public List<CommodityVo> loadData2(Map search);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param id
	 * @return PageFinder<Commodity> 返回类型
	 * @Description: 根据商品id修改购买人数
	 * @author liyushan
	 * @date 2015-6-23
	 * @modifier
	 * @version 1.0
	 */
	void updateBuyNumById(Integer id);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param companyId
	 * @return
	 * @Description: 根据公司id查询商品信息
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:31:44
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:31:44
	 * @version 1.0
	 */
	List<Commodity> querycommByCompanyId(Integer companyId);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param commodity
	 * @return
	 * @Description: 查询分校下 学科下 商品id
	 * @author 周文斌
	 * @date 2015-12-22 下午2:55:07
	 * @version 1.0
	 */
	List<Integer> findComId(Commodity commodity);

	/**
	 * Class Name: ICommodityService.java
	 *
	 * @param param
	 * @Description: 批量修改
	 * @author 周文斌
	 * @date 2015-12-22 下午3:15:31
	 * @version 1.0
	 */
	void updateStatus(Map<String, Object> param);

	Integer findCommodityIdByClassTypeId(Integer classTypeId);

	String findCommodityName(Integer id);

	List<CommodityVo> queryCourseByTeacherIds(Map<String, Object> map);

	int updateSpecialOrder(CommodityVo commodity);

	List<CommodityVo> queryClassScheduleList(CommodityVo search);

	List<CommodityVo> findCommodityByItems(CommodityVo search);

	List<CommodityVo> getModelList();

	List<CommodityVo> getStudySectionById(Integer modelId);

	PageFinder2<CommodityDto> getModelListByIds(Map<String, Object> param);

	int getModelListByIdsCount(Map<String, Object> param);
	/**
	 * 更新或插入first_recommend表
	 * @param gradeIds
	 * @param appShelvesIds
	 * @return
	 */
	public Boolean insertOrUpdate(String gradeIds, String appShelvesIds);
	
	public void giveUpFirstCommand(String appShelvesIds);
	/**
	 * 更新表first_recommend的sort字段
	 * @param appShelvesId
	 * @param sort 推荐顺序
	 * @return
	 */
	public Boolean updateFirstRecommend(String appShelvesId,String sort);
	
	
	public Boolean deleteFirstRecommend(String appShelvesId);

	/**
	 * 通过课程id查询录播信息
	 * @param listid
	 * @return
	 */
	List<VideoInfoVO> queryVideoInfo(List listid);

}
package com.yuxin.wx.commodity.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.yuxin.wx.vo.app.VideoInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.company.mapper.CompanyFunctionSetMapper;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.system.mapper.SysConfigServiceMapper;
import com.yuxin.wx.vo.classes.FirstRecommend;
import com.yuxin.wx.vo.commodity.CommodityDto;
import com.yuxin.wx.vo.commodity.CommodityVo;

/**
 * Service Implementation:Commodity
 *
 * @author wang.zx
 * @date 2015-3-14
 */
@Service
@Transactional
public class CommodityServiceImpl extends BaseServiceImpl implements ICommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private SysConfigServiceMapper sysConfigServiceMapper;
    @Autowired
    private CompanyFunctionSetMapper companyFunctionSetMapper;
    @Autowired
    private IClassTypeService classTypeServiceImpl;

    @Override
    public void insert(Commodity entity) {
        // TODO Auto-generated method stub
        commodityMapper.insert(entity);
    }

    @Override
    public void batchInsert(List<Commodity> entity) {
        commodityMapper.batchInsert(entity);
    }

    @Override
    public void update(Commodity entity) {
        commodityMapper.update(entity);
    }

    @Override
    public void deleteCommodityById(Integer id) {
        // TODO Auto-generated method stub
        commodityMapper.deleteById(id);
    }

    @Override
    public void deleteCommodityByIds(Integer[] ids) {
        // TODO Auto-generated method stub
        commodityMapper.deleteByIds(ids);
    }

    @Override
    public Commodity findCommodityById(Integer id) {
        // TODO Auto-generated method stub
        return commodityMapper.findById(id);
    }

    @Override
    public List<Commodity> findCommodityByPage(Commodity search) {
        return commodityMapper.page(search);
    }

    @Override
    public List<CommodityVo> searchCommodityByItemOne(Integer schoolId, Integer itemOneId) {
        CommodityVo commodity = new CommodityVo();
        commodity.setSchoolId(schoolId);
        commodity.setItemOneId(itemOneId);
        return commodityMapper.queryProduct(commodity);
    }

    @Override
    public List<CommodityVo> searchCommodityByItemTwo(Integer schoolId, Integer itemOneId, Integer itemSecondId) {
        CommodityVo commodity = new CommodityVo();
        commodity.setSchoolId(schoolId);
        commodity.setItemOneId(itemOneId);
        commodity.setItemSecondId(itemSecondId);
        return commodityMapper.queryProduct(commodity);
    }

    @Override
    public List<CommodityVo> searchCommodityList(CommodityVo search) {
        if (search.getSchoolId() == null) {
            return null;
        }
        return commodityMapper.queryProduct(search);
    }

    @Override
    public List<CommodityVo> loadData(Map search) {
        return commodityMapper.findProduct(search);
    }

    @Override
    public List<CommodityVo> loadData(CommodityVo search) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", search.getCompanyId());
        param.put("groupCode", "SERVICE_MEMBER");
        SysConfigService service = this.sysConfigServiceMapper.findByCodeId(param);
        CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
        CompanyFunctionSet.setCompanyId(search.getCompanyId());
        CompanyFunctionSet.setFunctionCode("COURSE_BUY_SET");
        List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetMapper.page(CompanyFunctionSet);
        CompanyFunctionSet buyNumSet = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;

        List<CommodityVo> data = this.commodityMapper.findProduct(search);
        if (null != data && data.size() > 0) {
            for (CommodityVo comm : data) {
                if (null != comm && null != comm.getMemberFlag()) {
                    if (null != service && null != service.getDelFlag() && service.getDelFlag() == 0) {
                        comm.setMemberFlag(0);
                    }
                }
                // 判断报名人数
                if (null != buyNumSet && buyNumSet.getStatus().equals("1")) {
                    if (null != comm && null != comm.getBuyNum() && null != comm.getBuyNumMax() && comm.getBuyNumMax() > 0) {
                        if (comm.getBuyNum() >= comm.getBuyNumMax()) {
                            comm.setIsOver("yes");
                        }
                    }
                }
            }
        }
        return data;
    }

    @Override
    public List<CommodityVo> loadData2(Map search) {
        return commodityMapper.findCommodityByClassTypeId(search);
    }

    /**
     * @param id
     * @return PageFinder<Commodity> 返回类型
     * @throws @exception
     * @Title: queryCommodityByPage
     * @Description:分页条件查询
     * @date 2015-4-8
     * @user by chopin
     */
    @Override
    public PageFinder<CommodityVo> queryCommodityByPage(CommodityVo search) {
        List<CommodityVo> commodityList = commodityMapper.queryProduct(search);
        for (CommodityVo comm : commodityList) {
            Integer videoFlag = comm.getVideoFlag();
            Integer liveFlag = comm.getLiveFlag();
            Integer faceFlag = comm.getFaceFlag();
            if (comm.getBaseNum() != null && !"".equals(comm.getBaseNum())) {
                comm.setTotalNum(comm.getBaseNum() + comm.getActualNum());
            } else {
                comm.setTotalNum(comm.getActualNum());
            }
            if (videoFlag == null) {
                comm.setVideoFlag(0);
            }
            if (liveFlag == null) {
                comm.setLiveFlag(0);
            }
            if (faceFlag == null) {
                comm.setFaceFlag(0);
            }
        }
        int count = commodityMapper.queryProductCount(search);
        PageFinder<CommodityVo> pageFinder = new PageFinder<CommodityVo>(search.getPage(), search.getPageSize(), count, commodityList);
        return pageFinder;
    }

    @Override
    public void updateBuyNumById(Integer id) {
        commodityMapper.updateBuyNumById(id);
    }

    @Override
    public List<Commodity> querycommByCompanyId(Integer companyId) {
        // TODO Auto-generated method stub
        return commodityMapper.querycommByCompanyId(companyId);
    }

    @Override
    public List<Integer> findComId(Commodity commodity) {
        // TODO Auto-generated method stub
        return commodityMapper.findComId(commodity);
    }

    @Override
    public void updateStatus(Map<String, Object> param) {
        // TODO Auto-generated method stub
        commodityMapper.updateStatus(param);
    }

    @Override
    public Integer findCommodityIdByClassTypeId(Integer classTypeId) {
        return commodityMapper.findCommodityIdByClassTypeId(classTypeId);
    }

    @Override
    public String findCommodityName(Integer id) {
        // TODO Auto-generated method stub
        return commodityMapper.findCommodityName(id);
    }

    @Override
    public List<CommodityVo> queryCourseByTeacherIds(Map<String, Object> map) {
        List<CommodityVo> list = commodityMapper.queryCourseByTeacherIds(map);
        return list;
    }

    @Override
    public int updateSpecialOrder(CommodityVo commodity) {
        int row = commodityMapper.updateSpecialOrder(commodity);
        return row;
    }


    @Override
    public List<CommodityVo> queryClassScheduleList(CommodityVo search) {
        Map<String, Object> map = new HashMap<>();
        map.put("com", search);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR, 1);
        String tomorry = sdf.format(ca.getTime());
        map.put("today", tomorry);
        map.put("tomorry", tomorry);

        List<CommodityVo> data = this.commodityMapper.queryClassScheduleList(map);
        return data;
    }

    @Override
    public List<CommodityVo> findCommodityByItems(CommodityVo search) {

        return commodityMapper.findCommodityByItems(search);
    }

    @Override
    public List<CommodityVo> getModelList() {
        return commodityMapper.getModelList();
    }

    @Override
    public List<CommodityVo> getStudySectionById(Integer modelId) {
        return commodityMapper.getStudySectionById(modelId);
    }

    @Override
    public PageFinder2<CommodityDto> getModelListByIds(Map<String, Object> param) {
        List<CommodityDto> datas = new ArrayList<CommodityDto>();
        Integer count = 0;
        String modelCode = (String) param.get("modelCode");
        if (("ZHIBO").equals(modelCode)) {
            datas = commodityMapper.getZhiBoModelListByIds(param);
            count = commodityMapper.getZhiBoModelListByIdsCount(param);
        } else if (modelCode != null && modelCode != "") {
            datas = commodityMapper.getModelListByIds(param);
            count = commodityMapper.getModelListByIdsCount(param);
        }
        return new PageFinder2<CommodityDto>((Integer) param.get("page"), (Integer) param.get("pageSize"), count, datas);
    }

    @Override
    public int getModelListByIdsCount(Map<String, Object> param) {
        return commodityMapper.getModelListByIdsCount(param);
    }

    @Override
    public Boolean insertOrUpdate(String gradeIds, String appShelvesIds) {
        //任何一个为空时，都不插入数据
        if (gradeIds == null || gradeIds == "" || appShelvesIds == "" || appShelvesIds == null) return true;

        String[] gradeIdArray = gradeIds.split(",");
        String[] appShelvesIdArray = appShelvesIds.split(",");

        //通过appShelvesIds先删除先有的记录

        List<String> appShelvesIdList = Arrays.asList(appShelvesIdArray);
        commodityMapper.deleteFirstRecommendByIds(appShelvesIdList);
        //插入对应的数据
        List<FirstRecommend> firstRecommends = new ArrayList<FirstRecommend>();

        for (String appShelvesId : appShelvesIdArray) {
            if (appShelvesId == "" || appShelvesId == null || appShelvesId == "undefined") continue;
            for (String gradeId : gradeIdArray) {
                if (gradeId == "" || gradeId == null || gradeId == "undefined") continue;
                FirstRecommend firstRecommend = new FirstRecommend();
                firstRecommend.setAppShelvesId(appShelvesId);
                firstRecommend.setGradeNo(gradeId);
                firstRecommends.add(firstRecommend);
            }
        }
        classTypeServiceImpl.insertFirstRecommond(firstRecommends);
        return true;
    }

    @Override
    public Boolean updateFirstRecommend(String appShelvesId, String sort) {
        if (appShelvesId == null || appShelvesId == "") return true;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appShelvesId", appShelvesId);
        params.put("sort", sort);
        commodityMapper.updateAppShelvesSort(params);
        return true;
    }

    @Override
    public List<VideoInfoVO> queryVideoInfo(List listid){
        List<VideoInfoVO> list = commodityMapper.queryVideoInfo(listid);
        return list;
    }


}

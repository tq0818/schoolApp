package com.yuxin.wx.classes.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.vo.classes.FirstRecommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.classes.mapper.ClassModuleLessonMapper;
import com.yuxin.wx.classes.mapper.ClassModuleMapper;
import com.yuxin.wx.classes.mapper.ClassModuleNoMapper;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeModuleRelationMapper;
import com.yuxin.wx.classes.mapper.ClassTypeRemoteRelationMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseRemoteMapper;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.system.mapper.SysConfigItemTagMapper;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;

/**
 * Service Implementation:ClassType
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassTypeServiceImpl extends BaseServiceImpl implements IClassTypeService {

	@Autowired
	private ClassTypeMapper classTypeMapper;

	@Autowired
	private CourseVideoMapper courseVideoMapper;

	@Autowired
	private CourseRemoteMapper courseRemoteMapper;

	@Autowired
	private ClassModuleMapper classModuleMapper;
	@Autowired
	private ClassTypeModuleRelationMapper classTypeModuleRelationMapper;
	@Autowired
	private ClassTypeRemoteRelationMapper classTypeRemoteRelationMapper;
	@Autowired
	private CourseVideoChapterMapper courseVideoChapterMapper;
	@Autowired
	private SysConfigItemTagMapper sysConfigItemTagMapper;
	@Autowired
	private ClassModuleLessonMapper moduleLessonMapper;
	@Autowired
	private ClassModuleNoMapper moduleNoMapper;
	@Autowired
	private ClassTypeModuleRelationMapper relatiomMapper;
	@Autowired
	private SysConfigTeacherMapper teacherMapper;
	@Autowired
	private CommodityMapper  commodityMapper;

	/**
	 *
	* @Title: saveClassType
	* @Description: 新增ClassType
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassType classType){
		classTypeMapper.insert(classType);
	}

	/**
	 *
	* @Title: batchSaveClassType
	* @Description: 批量新增ClassType
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassType> classTypes){
		if(classTypes != null && !classTypes.isEmpty()){
			classTypeMapper.batchInsert(classTypes);
		}
	}

	/**
	 *
	* @Title: updateClassType
	* @Description: 编辑ClassType
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassType classType){
		classTypeMapper.update(classType);
	}

	/**
	 *
	* @Title: deleteClassTypeById
	* @Description: 根据id删除ClassType
	* @param id
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeById(Integer id){
		classTypeMapper.deleteById(id);
	}

	/**
	 *
	* @Title: deleteClassTypeByIds
	* @Description: 根据id批量删除ClassType
	* @param ids
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeByIds(Integer[] ids){
		classTypeMapper.deleteByIds(ids);
	}

	/**
	 *
	* @Title: findClassTypeById
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassType findClassTypeById(Integer id){
		return classTypeMapper.findById(id);
	}

	/**
	 *
	* @Title: findClassTypeById
	* @Description: 查询班型列表
	* @param id
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by Chopin
	 */
	@Override
	public List<ClassType> findClassTypeList(ClassType classType) {

		return classTypeMapper.findListByItem(classType);
	}

	/**
	 *
	* @Title: findClassTypeById
	* @Description: 查询班型列表
	* @param id
	* @return void    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by Chopin
	 */
	@Override
	public List<ClassType> findClassTypeList2(ClassType classType) {

		return classTypeMapper.findListByItem2(classType);
	}

	/**
	 *
	* @Title: findClassTypeByPage
	* @Description: 分页查询
	* @return
	* @return List<ClassType>    返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by zhangbo
	 */
	@Override
	public PageFinder<ClassTypeVo> queryClassTypeByKeysForPage(ClassType search){
		Integer rowCount = classTypeMapper.pageCount(search);
		List<ClassTypeVo> data=classTypeMapper.queryClassTypeByKeysForPage(search);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	/**
	 *
	* @Title: findClassTypeDetail
	* @Description: 根据id查询班级详细信息
	* @return
	* @return ClassTypeVo 返回类型
	* @throws
	* @exception
	* @date 2014-12-5
	* @user by chopin
	 */
	public ClassTypeVo findClassTypeDetail(Map<String,String> map) {
		ClassTypeVo classvo=classTypeMapper.findDetailById(map);
		List<ClassModule> classModules=classModuleMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=courseVideoChapterMapper.findByClassId(map);
		Integer totalClass=0;
		for(ClassModule classModule: classModules){
			if(classModule.getTotalClassHour()!=null){
				totalClass+=classModule.getTotalClassHour();
			}
		}

		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		Integer id=map.get("classId")!=null?Integer.parseInt(map.get("classId")):null;
		classvo.setRemotes(courseRemoteMapper.findRemotesByClassTypeId(id));
		//classvo.setVideos(videos);
		classvo.setTotalClass(totalClass);
		return classvo;
	};

	@Override
	public void updateClassTypeRalation(ClassType classType,String ralationsIdVal){
		String type=classType.getTypeCode();
		int index=ralationsIdVal.indexOf(",");
		if(index>0){
			if(type.equals("CLASS_TYPE_NOMAL")){
				List<ClassTypeModuleRelation> listCmrs=new ArrayList<ClassTypeModuleRelation>();
				String [] moduleIds=ralationsIdVal.split(",");
				for(int i=0;i<moduleIds.length;i++){
					ClassTypeModuleRelation cmr=new ClassTypeModuleRelation();
					cmr.setClassTypeId(classType.getId());
					cmr.setModuleId(Integer.parseInt(moduleIds[i]));
					listCmrs.add(cmr);
				}
				classTypeModuleRelationMapper.batchInsert(listCmrs);
			}else if(type.equals("CLASS_TYPE_REMOTE")){
				List<ClassTypeRemoteRelation> listCRrs=new ArrayList<ClassTypeRemoteRelation>();
				String [] rids=ralationsIdVal.split(",");
				for(int i=0;i<rids.length;i++){
					ClassTypeRemoteRelation crr=new ClassTypeRemoteRelation();
					crr.setClassTypeId(classType.getId());
					crr.setRemoteId(Integer.parseInt(rids[i]));
					listCRrs.add(crr);
				}
				classTypeRemoteRelationMapper.batchInsert(listCRrs);
			}
		}else{
			if(type.equals("CLASS_TYPE_NOMAL")){
				ClassTypeModuleRelation cmr=new ClassTypeModuleRelation();
				cmr.setClassTypeId(classType.getId());
				cmr.setModuleId(Integer.parseInt(ralationsIdVal));
				classTypeModuleRelationMapper.insert(cmr);
			}else if(type.equals("CLASS_TYPE_REMOTE")){
				ClassTypeRemoteRelation crr=new ClassTypeRemoteRelation();
				crr.setClassTypeId(classType.getId());
				crr.setRemoteId(Integer.parseInt(ralationsIdVal));
				classTypeRemoteRelationMapper.insert(crr);
			}
		}
	}

	@Override
	public ClassType findClassTypeByStuId(Integer id, Integer companyId,Integer payMasterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("companyId", companyId);
		map.put("payMasterId", payMasterId);
		return classTypeMapper.findClassTypeByStuId(map);
	}

	@Override
	public List<ClassType> findByItem(Integer conpanyId,Integer schoolId, Integer itemOneId,Integer itemTwoId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("companyId", conpanyId);
		map.put("itemOneId", itemOneId);
		map.put("itemTwoId", itemTwoId);
		map.put("createSchoolId", schoolId);
		return classTypeMapper.findByItem(map);
	}

	@Override
	public PageFinder<ClassTypeVo> findClassTypesByPage(ClassType search) {

		String []codes = new String [1];
		List<ClassTypeVo> data = new ArrayList<ClassTypeVo>();
		if (search.getItemFourthCode()!=null && search.getItemFourthCode().length()>0){
			codes = search.getItemFourthCode().split(",");
		}
		Map<String,Object> map  = new HashMap<>();
		map.put("classType",search);
		map.put("codes",codes);
		data=classTypeMapper.queryClassTypesByPage(map);


			for(ClassTypeVo comm:data){
				Integer videoFlag=comm.getVideoFlag();
				Integer liveFlag=comm.getLiveFlag();
				Integer faceFlag=comm.getFaceFlag();
				Integer remoteFlag=comm.getRemoteFlag();
				if(videoFlag==null){
					comm.setVideoFlag(0);
				}
				if(liveFlag==null){
					comm.setLiveFlag(0);
				}
				if(faceFlag==null){
					comm.setFaceFlag(0);
				}
				if(remoteFlag==null){
					comm.setRemoteFlag(0);
				}

				if(null!=comm.getAppPrice()){
					comm.setOriginalPrice(comm.getAppPrice());
				}

				if(null!=comm.getSalePrice()){
					comm.setRealPrice(comm.getSalePrice());
				}
				if(null!=comm.getImgUrl()){
					comm.setCover(comm.getImgUrl());
				}
				if("1".equals(comm.getIsShelves()) && null!=comm.getReserveTime()){
					if(new Date().getTime()>this.getDate(comm.getReserveTime()).getTime()){
                            comm.setIsShelves("1");
						}else{
						    comm.setIsShelves("0");
					}
				}
		}
		int rowCount=classTypeMapper.queryCounts(map);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;


	}

	@Override
	public List<ClassType> findByRemote(Integer id) {
		return classTypeMapper.findByRemote( id);
	}
	@Override
	public ClassType findClassTypeByName(String name) {
		return classTypeMapper.queryClassTypeByName(name);
	}

	@Override
	public List<ClassType> findByModule(Integer id) {
		return classTypeMapper.findByModule(id);
	}



	@Override
	public List<String> findNameById(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.findNameById(param);
	}

	@Override
	public List<ClassType> findClassTypeList3(Integer companyId) {
		return classTypeMapper.findClassTypeList3(companyId);
	}
	@Override
	public List<ClassType> findClassTypeList4(Integer companyId) {
		return classTypeMapper.findClassTypeList4(companyId);
	}

	@Override
	public List<ClassType> findByItemOne(Integer itemOneId, Integer companyId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("itemOneId", itemOneId);
		map.put("companyId", companyId);
		return classTypeMapper.findByItemOne(map);
	}

	@Override
	public void changClassTypeCollectStatus(ClassType classType) {
		classTypeMapper.changClassTypeCollectStatus(classType);
	}

	@Override
	public List<ClassType> findClassTypeExits(ClassType classType) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryClassTypeExits(classType);
	}

	@Override
	public List<ClassType> findClassTypeByCondition(ClassType search) {
		return classTypeMapper.queryClassBycondition(search);
	}

	@Override
	public List<ClassType> findClassByItem(Integer conpanyId, Integer schoolId,
			Integer itemOneId, Integer itemTwoId) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("companyId", conpanyId);
		map.put("itemOneId", itemOneId);
		map.put("itemTwoId", itemTwoId);
		map.put("createSchoolId", schoolId);
		return classTypeMapper.findClassByItem(map);
	}
	@Override
	public List<ClassType> findClassByItemRelation(Integer conpanyId, Integer schoolId,
												   String itemOneCode, String itemSecondCode, String itemThridCode) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", conpanyId);
		map.put("itemOneCode", itemOneCode);
		map.put("itemSecondCode", itemSecondCode);
		map.put("itemThirdCode", itemThridCode);
		map.put("createSchoolId", schoolId);
		return classTypeMapper.findClassByItemRelation(map);
	}



	@Override
	public List<ClassType> findClassTypeBycompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return classTypeMapper.findClassTypeBycompanyId(companyId);
	}

	@Override
	public ClassType findClassTypeByCommodity(Integer id) {
		return classTypeMapper.findClassTypeByCommodity(id);
	}

	@Override
	public List<Integer> findClassTypeByClass(ClassType ct) {
		// TODO Auto-generated method stub
		return classTypeMapper.findClassTypeByClass(ct);
	}

	@Override
	public void updateDeflag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeMapper.updateDeflag(param);
	}

	@Override
	public List<ClassTypeVo> queryClassTypesByClassPackage(
			ClassPackageConditionVo search) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryClassTypesByClassPackage(search);
	}

	@Override
	public List<ClassTypeVo> queryIsExistByClassPackage(
			ClassPackageConditionVo search) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryIsExistByClassPackage(search);
	}

	@Override
	public PageFinder<ClassTypeVo> queryClassTypeForMember(MemberLevelAndClassTypeVo mlactVo) {
		List<ClassTypeVo> classTypeVoList=classTypeMapper.queryClassTypeForMember(mlactVo);
		int count=classTypeMapper.queryClassTypeForMemberCount(mlactVo);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(mlactVo.getPage(), mlactVo.getPageSize(), count, classTypeVoList);
		return pageFinder;
	}

	@Override
	public List<ClassType> queryCourseByOneAndTwoItem(ClassType search) {
		return classTypeMapper.queryClassTypeByOneAndTwoItem(search);
	}

	@Override
	public ClassTypeVo findClassTypeVoByClassTypeId(Integer classTypeId){
		return classTypeMapper.findClassTypeVoByClassTypeId(classTypeId);
	}

	@Override
	public List<ClassType> findAllclassType(ClassType cp) {
		return classTypeMapper.findAllclassType(cp);
	}

	@Override
	public int updateSubjectClassOrder(ClassType cp) {
		int row = classTypeMapper.updateSubjectClassOrder(cp);
		return row;
	}

	@Override
	public int countSubjectClassOrder(String itemOneCode) {
		int count = classTypeMapper.countSubjectClassOrder(itemOneCode);
		return count;
	}

	@Override
	public ClassTypeVo querySingOtherClassTypeInfo(ClassTypeVo search) {
		Map<Object,Object>map = new HashMap<Object,Object>();
		map.put("classType",search);
		List<ClassTypeVo> classTypes = classTypeMapper.querySingleOtherClassTypeInfo(map);

		if(null!=classTypes && classTypes.size()>0){
			ClassTypeVo ctv = classTypes.get(0);
			if(null!=ctv.getImgUrl()&&!"".equals(ctv.getImgUrl())){
				ctv.setCover(ctv.getImgUrl());
			}
			return ctv;
		}
		return null;
	}

	@Override
	public void insertAppShelvesInfo(ClassTypeVo cto) {
		classTypeMapper.insertAppShelvesInfo(cto);
	}

	@Override
	public void insertFirstRecommond(List<FirstRecommend> frs) {
		classTypeMapper.insertFirstRecommond(frs);
	}

	@Override
	public void insertAndUpdateFirstRecommond(List<FirstRecommend> frs,String sort,String appId) {
		//删除first_recommend数据根据appid
		List<String> appShelvesIdList = new ArrayList<String>();
		appShelvesIdList.add(appId);
		commodityMapper.deleteFirstRecommendByIds(appShelvesIdList);
		//插入数据
		classTypeMapper.insertFirstRecommond(frs);
		//根据appid更新app_shelves里的sort
		Map<String,Object> params=new HashMap<>();
		params.put("appShelvesId",appId);
		params.put("sort",sort);
		commodityMapper.updateAppShelvesSort(params);
	}




	@Override
	public ClassTypeVo querySingleLiveClassTypeInfo(ClassTypeVo search) {
		Map<Object,Object>map = new HashMap<Object,Object>();
		map.put("classType",search);
		List<ClassTypeVo> classTypes = classTypeMapper.querySingleLiveClassTypeSingleInfo(map);
		if(null!=classTypes && classTypes.size()>0){
			//规则：
			//1、如果当前有正在进行的直播，则取正在进行直播最早的开始时间
			//2、如果当期没有开始时间，则取最近的直播开始时间
			Long liveSmartTime=null;
			Long smartTime=null;
			Long nowTime=new Date().getTime();
			ClassTypeVo liveSmartTimeVo=null;
			ClassTypeVo smartTimeVo=null;
			for(ClassTypeVo ctv : classTypes){
				if(ctv.getLessonDate()==null) continue;
				String lessonStartTime = ctv.getLessonDate()+" "+ctv.getLessonTimeStart()+":00";
				String lessonEndTime = ctv.getLessonDate()+" "+ctv.getLessonTimeEnd()+":00";
				Long lessonStartDateTime =this.getDate(lessonStartTime).getTime();
				Long lessonSEndDateTime = this.getDate(lessonEndTime).getTime();
				//如果当前有正在进行的直播，则取正在进行直播最早的开始时间
				if(lessonStartDateTime<nowTime&&nowTime<lessonSEndDateTime){
					if(liveSmartTime==null||liveSmartTime!=null&&liveSmartTime>lessonStartDateTime){
						liveSmartTime=lessonStartDateTime;
						liveSmartTimeVo=ctv;
					}
				}
				//如果当期没有开始时间，则取最近的直播开始时间
				if(smartTime==null&&nowTime<lessonStartDateTime||nowTime<lessonStartDateTime&&smartTime>lessonStartDateTime){
					smartTime=lessonStartDateTime;
					smartTimeVo=ctv;
				}
			}
			ClassTypeVo ctOne = classTypes.get(0);
			if(null!=ctOne.getImgUrl()&&!"".equals(ctOne.getImgUrl())){
				ctOne.setCover(ctOne.getImgUrl());
			}
			//设置最终时间
			if(smartTime!=null){
				ctOne=smartTimeVo;
			}
			if(liveSmartTime!=null){
				ctOne=liveSmartTimeVo;
			}
			return ctOne;
		}
		return null;
	}

	/**
	 * 将字符串转换为Date类型数据
	 * @param dateStr
	 * @return
     */
	public Date getDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ClassTypeVo> getGardeIdList(ClassTypeVo search) {
		return classTypeMapper.getGardeIdList(search);
	}
	
	@Override
	public List<ClassTypeVo> queryNeedPushLiveClass(){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String currtime=sdf.format(new Date());
		List<ClassTypeVo> list=classTypeMapper.queryNeedPushLiveClass();
		return list;
	}

	@Override
	public List<ClassType> queryRecordVideo(Map<String, Object> params) {
		return classTypeMapper.queryRecordVideo(params);
	}
}



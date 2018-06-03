package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.institution.InstitutionCategoryManageService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionCategoryManageMapper;
import com.yuxin.wx.institution.mapper.InstitutionInfoMapper;
import com.yuxin.wx.institution.mapper.InstitutionLabelMapper;
import com.yuxin.wx.institution.mapper.InstitutionRelationMapper;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.institution.IndexRecommendVo;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import com.yuxin.wx.model.institution.InstitutionRelationVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.user.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InstitutionInfoServiceImpl extends BaseServiceImpl implements InstitutionInfoService {
    @Autowired
    private InstitutionInfoMapper institutionInfoMapper;

    @Autowired
    private InstitutionLabelMapper institutionLabelMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private InstitutionRelationMapper institutionRelationMapper;

    @Autowired
    private IUsersService usersServiceImpl;

    @Autowired
    private IAuthUserRoleService authUserRoleServiceImpl;

    @Autowired
    private InstitutionCategoryManageService institutionCategoryService;

    @Autowired
    private InstitutionCategoryManageMapper institutionCategoryManageMapper;

    @Override
    @Transactional
    public void insert(InstitutionInfoVo institutionInfoVo) {
        try{
            Users users = new Users();
            if(null != institutionInfoVo.getUserName() && !"".equals(institutionInfoVo.getUserName())){
                users.setId(null);
                users.setUsername(institutionInfoVo.getUserName());
                users.setPassword(institutionInfoVo.getPwd());
                users.setUserType("INSTITUTION_MANAGE");
                usersMapper.insertA(users);
            }
            //添加用户关系表
            usersServiceImpl.insertUserCompanyRalation(users.getId(),18113);
            Integer curUserId = institutionInfoVo.getCurrtUser();
            String roleCode = "where ap.privilege_name in ('INSTITUTION_MANAGE')";
            Map<String,Object> params = new HashMap<>();
            params.put("roleCode",roleCode);
            List<AuthUserRole> roleIds = authUserRoleServiceImpl.queryRoleIds(params);
            for (AuthUserRole aur : roleIds){
                AuthUserRole authUserRole=new AuthUserRole();
                authUserRole.setUserId(users.getId());
                authUserRole.setRoleUid(aur.getRoleUid());
                authUserRole.setCreateTime(new Date());
                authUserRole.setCreator(curUserId+"");
                authUserRole.setUpdateTime(new Date());
                authUserRole.setUpdator(curUserId+"");
                authUserRoleServiceImpl.insert(authUserRole);
            }

            institutionInfoVo.setIsChain(Integer.parseInt(institutionInfoVo.getIsChains()));
            //插入机构表
            Date date = new Date();
            institutionInfoVo.setId(null);
            institutionInfoVo.setChainId(null);
            institutionInfoVo.setLongitude(null);
            institutionInfoVo.setLatitude(null);
            institutionInfoVo.setReservService(null);
            institutionInfoVo.setIsShelves(0);
            institutionInfoVo.setIsCertified(0);
            institutionInfoVo.setCreateTime(date);
            institutionInfoVo.setUpdateTime(date);
            institutionInfoVo.setUserId(users.getId());
            institutionInfoMapper.insert(institutionInfoVo);
            InstitutionLabelVo institutionLabelVo = new InstitutionLabelVo();
            String [] catOne=null;
            String [] catTwo=null;
            if(null != institutionInfoVo.getOneLevelId() && !"".equals(institutionInfoVo.getOneLevelId())){
                catOne = institutionInfoVo.getOneLevelId().split(",");
            }
            if(null != institutionInfoVo.getTwoLevelId() && !"".equals(institutionInfoVo.getTwoLevelId())){
                catTwo = institutionInfoVo.getTwoLevelId().split(",");
            }

            //插入机构分类关系表
            InstitutionRelationVo institutionRelationVo = new InstitutionRelationVo();
            for(int i =0;i<catOne.length;i++){
                institutionRelationVo.setId(null);
                institutionRelationVo.setInsId(institutionInfoVo.getId());
                institutionRelationVo.setOneLevelId(Integer.parseInt(catOne[i]));
                institutionRelationVo.setTwoLevelId(Integer.parseInt(catTwo[i]));
                institutionRelationMapper.insert(institutionRelationVo);
            }

            //插入机构分类推荐关系表
            //去掉重复的一级分类
            List<Integer> list = new ArrayList<Integer>();
            for (int i=0; i<catOne.length; i++) {
                if(!list.contains(Integer.parseInt(catOne[i]))) {
                    list.add(Integer.parseInt(catOne[i]));
                }
            }
            List<IndexRecommendVo> insInfo = new ArrayList<>();

            for(int i = 0;i<list.size();i++){
                IndexRecommendVo indexRecommendVo = new IndexRecommendVo();
                Map<String, Object> map = new HashMap<>();
                map.put("cateId",list.get(i));
                int maxSort = institutionCategoryManageMapper.queryInsRecommendMaxSort(map);
                if(maxSort != 0){
                    indexRecommendVo.setInsId(institutionInfoVo.getId());
                    indexRecommendVo.setStatus(0);
                    indexRecommendVo.setTid(list.get(i));
                    indexRecommendVo.setSort(maxSort+1);
                    insInfo.add(indexRecommendVo);
                }
            }

            for(int i = 0;i<catTwo.length;i++){
                IndexRecommendVo indexRecommendVo = new IndexRecommendVo();
                Map<String, Object> map = new HashMap<>();
                map.put("cateId",Integer.parseInt(catOne[i]));
                int maxSort = institutionCategoryManageMapper.queryInsRecommendMaxSort(map);
                if(maxSort != 0){
                    indexRecommendVo.setInsId(institutionInfoVo.getId());
                    indexRecommendVo.setStatus(0);
                    indexRecommendVo.setTid(Integer.parseInt(catTwo[i]));
                    indexRecommendVo.setSort(maxSort+1);
                    insInfo.add(indexRecommendVo);
                }
            }

            institutionCategoryManageMapper.batchInsertRecommendInfo(insInfo);

            if(null != institutionInfoVo.getSysLabel() && !"".equals(institutionInfoVo.getSysLabel())){
                String labels = institutionInfoVo.getSysLabel().substring(0,institutionInfoVo.getSysLabel().lastIndexOf(","));
                String[] labelArr = labels.split(",");//标签数组
                //插入机构标签表
                for(int i =0;i<labelArr.length;i++){
                    institutionLabelVo.setId(null);
                    institutionLabelVo.setCreateTime(date);
                    institutionLabelVo.setUpdateTime(date);
                    institutionLabelVo.setLabelType("0");
                    institutionLabelVo.setLabelName(labelArr[i]);
                    institutionLabelVo.setSourceFlag(0);
                    institutionLabelVo.setRelationId(institutionInfoVo.getId());//机构主键

                    institutionLabelMapper.insert(institutionLabelVo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void update(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.update(institutionInfoVo);
    }

    @Override
    public InstitutionInfoVo findInstitutionInfoById(Integer id) {
        return institutionInfoMapper.findInstitutionInfoById(id);
    }

    @Override
    public InstitutionInfoVo findInstitutionInfo(InstitutionInfoVo institutionInfoVo) {
        return institutionInfoMapper.findInstitutionInfo(institutionInfoVo);
    }


    @Override
    public PageFinder<InstitutionInfoVo> findInstitutionInfos(InstitutionInfoVo insInfoVo) {
            if(insInfoVo.getPage() == 1){
                insInfoVo.setPage(0);
            }else{
                insInfoVo.setPage((insInfoVo.getPage()-1)*insInfoVo.getPageSize());
            }
            List<InstitutionInfoVo> data = institutionInfoMapper.findInstitutionInfos(insInfoVo);
            for(int i = 0; i<data.size();i++){
                data.get(i).setSort(i+1);
            }
            Integer rowCount = institutionInfoMapper.findInstitutionInfosCount(insInfoVo);
            PageFinder<InstitutionInfoVo> pageFinder=new PageFinder<>(insInfoVo.getPage()/insInfoVo.getPageSize() ,insInfoVo.getPageSize(),rowCount,data);
            return pageFinder;
    }

    @Override
    @Transactional
    public void updateInsById(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.update(institutionInfoVo);

        //删除原系统标签,自定义标签,原特色服务标签
        institutionLabelMapper.deleteByInsId(institutionInfoVo.getId());

        //删除原机构分类
        institutionRelationMapper.delete(institutionInfoVo.getId());

        Date date = new Date();
        //插入新机构分类
        String [] catOne=null;
        String [] catTwo=null;
        if(null != institutionInfoVo.getOneLevelId() && !"".equals(institutionInfoVo.getOneLevelId())){
            catOne = institutionInfoVo.getOneLevelId().split(",");
        }
        if(null != institutionInfoVo.getTwoLevelId() && !"".equals(institutionInfoVo.getTwoLevelId())){
            catTwo = institutionInfoVo.getTwoLevelId().split(",");
        }

        //机构分类存储容器
        Map<String,Object> cateNew = new HashMap<String,Object>();
        //插入机构分类关系表
        InstitutionRelationVo institutionRelation = new InstitutionRelationVo();
        for(int i =0;i<catOne.length;i++){
            institutionRelation.setId(null);
            institutionRelation.setInsId(institutionInfoVo.getId());
            institutionRelation.setOneLevelId(Integer.parseInt(catOne[i]));
            institutionRelation.setTwoLevelId(Integer.parseInt(catTwo[i]));
            institutionRelationMapper.insert(institutionRelation);

            //存储一二级分类
            cateNew.put(catOne[i],true);
            cateNew.put(catTwo[i],true);
        }


        /**
         * 对该机构的分类推荐情况做修改
         */
        Map<String,Object> params = new HashMap<String,Object>();
        //1.查询处推荐表里该机构已经对应有的分类
        params.put("insId",institutionInfoVo.getId());
        List<String> cateOldIds = institutionCategoryManageMapper.queryOldCateIdsByInsId(params);
        if(null!=cateOldIds&&cateOldIds.size()>0){
            for(String cateId : cateOldIds){
                Object isExsit =  cateNew.get(cateId);
                if(isExsit!=null){
                    //1.1有相同的分类不做任何操作 相同的部分移除掉
                    cateNew.remove(cateId);
                    continue;
                }else{
                    //1.2新增分类没有 原有机构分类有 直接做删除操作
                    params.put("id",cateId);
                    institutionCategoryManageMapper.deletRecommendInsInfo(params);
                }
            }
        }
        //1.2新增分类有   原有机构分类里没有则需要查询该部分机构是否已经推荐
        //1.2.1推荐了  插入该分类对应机构数据
        //1.2.2没有推荐 不做处理
        if(null!=cateNew && cateNew.size()>0){
            Set<String>cateIds = cateNew.keySet();
            Map<String,Object>p = new HashMap<String,Object>();
            List<IndexRecommendVo>insInfo = new ArrayList<IndexRecommendVo>();
            for(String cateId : cateIds){
                p.put("cateId",cateId);
                int maxSort = institutionCategoryManageMapper.queryInsRecommendMaxSort(p);
                if(0!=maxSort){
                    IndexRecommendVo indexRecommendVo = new IndexRecommendVo();
                    indexRecommendVo.setInsId(institutionInfoVo.getId());
                    indexRecommendVo.setStatus(0);
                    indexRecommendVo.setTid(Integer.parseInt(cateId));
                    indexRecommendVo.setSort(maxSort+1);
                    insInfo.add(indexRecommendVo);
                }
            }
            institutionCategoryManageMapper.batchInsertRecommendInfo(insInfo);
        }






/*
        Map<String,Object> params = new HashMap<String,Object>();
        //1.查出该机构已经挂载在哪些分类下
        params.put("insId",institutionInfoVo.getId());
        List<String> cateOldIds = institutionManageMapper.queryOldCateIdsByInsId(params);
        //2.取出这些分类 和当前修复的分类作比对
        if(null==cateOldIds || cateOldIds.size()==0){//说明该机构还未挂载到任何已经推荐的分类下

        }else{//已经挂载到部分已经推荐的分类下

        }*/
        //2.1 如果分类已经存在 则不需要做任何操作
        //2.2 如果分类不存在则需要插入数据
        //2.3 如果取出分类在重新保存的分类里找不到则需要删除该数据
/*        //查询改机构的所有分类情况
        List<InstitutionRelationVo> insRsOle = institutionRelationMapper.findByinsId(institutionInfoVo.getId());
        //将insRsOle放入map注意放入的key
        Map<String,InstitutionRelationVo> mapOle = new HashMap<>();
        for(int i = 0;i<insRsOle.size();i++){
            mapOle.put(insRsOle.get(i).getOneLevelId().toString()+","+insRsOle.get(i).getTwoLevelId().toString(),insRsOle.get(i));
        }

        for(int i = 0;i<catOne.length;i++){
            if(mapOle.get(catOne[i]+","+catTwo[i]) != null){
                mapOle.remove(catOne[i]+","+catTwo[i]);
            }else{
                InstitutionRelationVo institutionRelationVo = new InstitutionRelationVo();
                institutionRelationVo.setInsId(institutionInfoVo.getId());
                institutionRelationVo.setOneLevelId(Integer.parseInt(catOne[i]));
                institutionRelationVo.setTwoLevelId(Integer.parseInt(catTwo[i]));
                institutionRelationVo.setIsRecommend(0);
                institutionRelationMapper.insert(institutionRelationVo);
            }
        }*/

       /* for(String key :mapOle.keySet()){
            if(null != mapOle.get(key).getIsRecommend() && mapOle.get(key).getIsRecommend() != 0){
                Map<String,Object> map = new HashMap<>();
                map.put("rid",mapOle.get(key).getId());
                map.put("insId",mapOle.get(key).getInsId());
                map.put("typeId",mapOle.get(key).getOneLevelId());
                map.put("level",1);
                int num = institutionCategoryService.alterIndexRecommendStatus(map);
            }
            institutionRelationMapper.delete(mapOle.get(key).getId());
        }*/


        //TODO 将修改的机构重新如表

        //1.查处该机构已经挂载在哪些分类下了

        //2.取出这些分类 和当前修复的分类作比对
            //2.1 如果分类已经存在 则不需要做任何操作
            //2.2 如果分类不存在则需要插入数据
            //2.3 如果取出分类在重新保存的分类里找不到则需要删除该数据
        //插入新系统标签
        InstitutionLabelVo institutionLabelVo = new InstitutionLabelVo();
        if(null != institutionInfoVo.getSysLabel() && !"".equals(institutionInfoVo.getSysLabel())){
            String labels = institutionInfoVo.getSysLabel().substring(0,institutionInfoVo.getSysLabel().lastIndexOf(","));
            String[] labelArr = labels.split(",");//标签数组
            //插入机构标签表
            for(int i = 0; i<labelArr.length;i++){
                institutionLabelVo.setId(null);
                institutionLabelVo.setCreateTime(date);
                institutionLabelVo.setUpdateTime(date);
                institutionLabelVo.setLabelType("0");
                institutionLabelVo.setLabelName(labelArr[i]);
                institutionLabelVo.setSourceFlag(0);
                institutionLabelVo.setRelationId(institutionInfoVo.getId());//机构主键

                institutionLabelMapper.insert(institutionLabelVo);
            }
        }
        //插入新自定义标签
        if(null != institutionInfoVo.getCustomLabel() && !"".equals(institutionInfoVo.getCustomLabel())){
            String labels = institutionInfoVo.getCustomLabel().substring(0,institutionInfoVo.getCustomLabel().lastIndexOf(","));
            String[] labelArr = labels.split(",");//标签数组
            //插入机构标签表
            for(int i = 0; i<labelArr.length;i++){
                institutionLabelVo.setId(null);
                institutionLabelVo.setCreateTime(date);
                institutionLabelVo.setUpdateTime(date);
                institutionLabelVo.setLabelType("1");
                institutionLabelVo.setLabelName(labelArr[i]);
                institutionLabelVo.setSourceFlag(0);
                institutionLabelVo.setRelationId(institutionInfoVo.getId());//机构主键

                institutionLabelMapper.insert(institutionLabelVo);
            }
        }
        //插入新特色服务标签
        if(null != institutionInfoVo.getImgUrl() && !"".equals(institutionInfoVo.getImgUrl())){
            String labels = institutionInfoVo.getImgUrl().substring(0,institutionInfoVo.getImgUrl().lastIndexOf(","));
            String[] labelArr = labels.split(",");//标签数组
            String labels2 = institutionInfoVo.getSpecialService().substring(0,institutionInfoVo.getSpecialService().lastIndexOf(","));
            String[] labelArr2 = labels2.split(",");//标签数组
            //插入机构标签表
            for(int i = 0; i<labelArr.length;i++){
                institutionLabelVo.setId(null);
                institutionLabelVo.setCreateTime(date);
                institutionLabelVo.setUpdateTime(date);
                if(labelArr[i].indexOf("institutionSpe")!=-1){
                    institutionLabelVo.setImgUrl(labelArr[i].substring(labelArr[i].indexOf("institutionSpe")));
                }else{
                    institutionLabelVo.setImgUrl("");
                }
                institutionLabelVo.setLabelType("2");
                institutionLabelVo.setLabelName(labelArr2[i]);
                institutionLabelVo.setSourceFlag(0);
                institutionLabelVo.setRelationId(institutionInfoVo.getId());//机构主键

                institutionLabelMapper.insert(institutionLabelVo);
            }
        }
    }

    @Override
    public void updateInsManageById(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.update(institutionInfoVo);
    }

    @Override
    public InstitutionInfoVo checkUser(Integer id) {
        try{
            return institutionInfoMapper.checkUser(id);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public InstitutionInfoVo queryInstitutionByUserId(Map<String, Object> params) {
        return institutionInfoMapper.queryInstitutionByUserId(params);
    }

    @Override
    public InstitutionInfoVo insCheckName(String name) {
        return institutionInfoMapper.insCheckName(name);
    }

    @Override
    public List<InstitutionInfoVo> queryInsByComment() {
        return institutionInfoMapper.queryInsByComment();
    }


    @Override
    public List<InstitutionInfoVo> queryInitInsClassList() {
        return institutionInfoMapper.queryInitInsClassList();
    }
}

package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionInfoMapper;
import com.yuxin.wx.institution.mapper.InstitutionLabelMapper;
import com.yuxin.wx.institution.mapper.InstitutionRelationMapper;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import com.yuxin.wx.model.institution.InstitutionRelationVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.user.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //删除原机构分类
        institutionRelationMapper.delete(institutionInfoVo.getId());
        //删除原系统标签,自定义标签,原特色服务标签
        institutionLabelMapper.deleteByInsId(institutionInfoVo.getId());

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

        //插入机构分类关系表
        InstitutionRelationVo institutionRelationVo = new InstitutionRelationVo();
        for(int i =0;i<catOne.length;i++){
            institutionRelationVo.setId(null);
            institutionRelationVo.setInsId(institutionInfoVo.getId());
            institutionRelationVo.setOneLevelId(Integer.parseInt(catOne[i]));
            institutionRelationVo.setTwoLevelId(Integer.parseInt(catTwo[i]));
            institutionRelationMapper.insert(institutionRelationVo);
        }
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

package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.InstitutionInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.InstitutionInfoMapper;
import com.yuxin.wx.institution.mapper.InstitutionLabelMapper;
import com.yuxin.wx.model.institution.InstitutionInfoVo;
import com.yuxin.wx.model.institution.InstitutionLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InstitutionInfoServiceImpl extends BaseServiceImpl implements InstitutionInfoService {
    @Autowired
    private InstitutionInfoMapper institutionInfoMapper;

    @Autowired
    private InstitutionLabelMapper institutionLabelMapper;

    @Override
    public void insert(InstitutionInfoVo institutionInfoVo) {
        String labels = institutionInfoVo.getSysLabel().substring(0,institutionInfoVo.getSysLabel().lastIndexOf(","));
        String[] labelArr = labels.split(",");//标签数组

        //插入机构表
        institutionInfoMapper.insert(institutionInfoVo);
        InstitutionLabelVo institutionLabelVo = new InstitutionLabelVo();
        for(int i =0;i<labelArr.length;i++){
            Date date = new Date();
            institutionLabelVo.setCreateTime(date);
            institutionLabelVo.setUpdateTime(date);
            institutionLabelVo.setLabelType("0");
            institutionLabelVo.setLabelName(labelArr[i]);
            institutionLabelVo.setSourceFlag(0);
            institutionLabelVo.setRelationId(institutionInfoVo.getId());//机构主键

            //插入关系表
            institutionLabelMapper.insert(institutionLabelVo);
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
        try{
            List<InstitutionInfoVo> data = institutionInfoMapper.findInstitutionInfos(insInfoVo);
            Integer rowCount = institutionInfoMapper.findInstitutionInfosCount(insInfoVo);
            insInfoVo.setPage(0);
            insInfoVo.setPageSize(10);
            PageFinder<InstitutionInfoVo> pageFinder=new PageFinder<>(insInfoVo.getPage(),insInfoVo.getPageSize(),rowCount,data);
            return pageFinder;
        }catch (Exception e){
            e.printStackTrace();

        }
      return null;
    }

    @Override
    public void updateInsById(InstitutionInfoVo institutionInfoVo) {
        institutionInfoMapper.update(institutionInfoVo);
        //修改已存在三类标签
        //系统标签
        InstitutionLabelVo insLabel = new InstitutionLabelVo();
        Date date = new Date();
        if(null != institutionInfoVo.getSysLabelIds() && !"".equals(institutionInfoVo.getSysLabelIds())){
            String sysLabelIds = institutionInfoVo.getSysLabelIds().substring(0,institutionInfoVo.getSysLabelIds().lastIndexOf(","));
            String sysLables = institutionInfoVo.getSysLabel().substring(0,institutionInfoVo.getSysLabel().lastIndexOf(","));
            String [] sysLabelIdsArr = sysLabelIds.split(",");
            String [] sysLablesArr = sysLables.split(",");
            for(int i = 0; i<sysLabelIdsArr.length; i++){
                insLabel.setId(Integer.parseInt(sysLabelIdsArr[i]));
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(sysLablesArr[i]);
                insLabel.setLabelType("0");
                insLabel.setSourceFlag(0);
                insLabel.setUpdateTime(date);
                institutionLabelMapper.update(insLabel);
            }

        }

        //自定义标签
        if(null != institutionInfoVo.getCustomLabelIds() && !"".equals(institutionInfoVo.getCustomLabelIds())){
            String customLableIds = institutionInfoVo.getCustomLabelIds().substring(0,institutionInfoVo.getCustomLabelIds().lastIndexOf(","));
            String customLables = institutionInfoVo.getCustomLabel().substring(0,institutionInfoVo.getCustomLabel().lastIndexOf(","));
            String [] customLableIdsArr = customLableIds.split(",");
            String [] customLablesArr = customLables.split(",");
            for(int i = 0; i<customLableIdsArr.length; i++){
                insLabel.setId(Integer.parseInt(customLableIdsArr[i]));
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(customLablesArr[i]);
                insLabel.setLabelType("1");
                insLabel.setSourceFlag(0);
                insLabel.setUpdateTime(date);
                institutionLabelMapper.update(insLabel);
            }
        }

        //特殊服务
        if(null != institutionInfoVo.getSpecialServiceIds() && !"".equals(institutionInfoVo.getSpecialServiceIds())){
            String specialServiceIds = institutionInfoVo.getSpecialServiceIds().substring(0,institutionInfoVo.getSpecialServiceIds().lastIndexOf(","));
            String specialServices = institutionInfoVo.getSpecialService().substring(0,institutionInfoVo.getSpecialService().lastIndexOf(","));
            String [] imgUrlsArr = null;
            if(null != institutionInfoVo.getImgUrl() && !"".equals(institutionInfoVo.getImgUrl())){
                String imgUrls = institutionInfoVo.getImgUrl().substring(0,institutionInfoVo.getImgUrl().lastIndexOf(","));
                imgUrlsArr = imgUrls.split(",");
            }
            String [] specialServiceIdsArr = specialServiceIds.split(",");
            String [] specialServicesArr = specialServices.split(",");
            for(int i = 0; i<specialServiceIdsArr.length; i++){
                insLabel.setId(Integer.parseInt(specialServiceIdsArr[i]));
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(specialServicesArr[i]);
                insLabel.setLabelType("2");
                insLabel.setSourceFlag(0);
                if(null != imgUrlsArr && !"".equals(imgUrlsArr[i])){
                    insLabel.setImgUrl(imgUrlsArr[i]);
                }
                insLabel.setUpdateTime(date);
                institutionLabelMapper.update(insLabel);
            }
        }


        //插入新增的标签
        //系统标签
        if(null != institutionInfoVo.getSysLabelNew() && !"".equals(institutionInfoVo.getSysLabelNew())){
            String sysLabelNews = institutionInfoVo.getSysLabelNew().substring(0,institutionInfoVo.getSysLabelNew().lastIndexOf(","));
            String [] sysLableNewArr = sysLabelNews.split(",");
            for(int i = 0; i<sysLableNewArr.length;i++){
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(sysLableNewArr[i]);
                insLabel.setLabelType("0");
                insLabel.setSourceFlag(0);
                insLabel.setCreateTime(date);
                insLabel.setUpdateTime(date);
                institutionLabelMapper.insert(insLabel);
            }
        }

        //自定义标签
        if(null != institutionInfoVo.getCustomLabelNew() && !"".equals(institutionInfoVo.getCustomLabelNew())){
            String customLabelNews = institutionInfoVo.getCustomLabelNew().substring(0,institutionInfoVo.getCustomLabelNew().lastIndexOf(","));
            String [] customLabelNewsArr = customLabelNews.split(",");
            for(int i = 0; i<customLabelNewsArr.length;i++){
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(customLabelNewsArr[i]);
                insLabel.setLabelType("1");
                insLabel.setSourceFlag(0);
                insLabel.setCreateTime(date);
                insLabel.setUpdateTime(date);
                institutionLabelMapper.insert(insLabel);
            }
        }

        //特殊服务
        if(null != institutionInfoVo.getSpecialServiceNew() && !"".equals(institutionInfoVo.getSpecialServiceNew())){
            String specialServiceNews = institutionInfoVo.getSpecialServiceNew().substring(0,institutionInfoVo.getSpecialServiceNew().lastIndexOf(","));
            String [] specialServiceNewsArr = specialServiceNews.split(",");
            String [] imgUrlsArr = null;
            if(null != institutionInfoVo.getImgUrlNew() && !"".equals(institutionInfoVo.getImgUrlNew())){
                String imgUrls = institutionInfoVo.getImgUrlNew().substring(0,institutionInfoVo.getImgUrlNew().lastIndexOf(","));
                imgUrlsArr = imgUrls.split(",");
            }
            for(int i = 0; i<specialServiceNewsArr.length;i++){
                insLabel.setRelationId(institutionInfoVo.getId());
                insLabel.setLabelName(specialServiceNewsArr[i]);
                insLabel.setLabelType("2");
                insLabel.setSourceFlag(0);
                if(null != imgUrlsArr && !"".equals(imgUrlsArr[i])){
                    insLabel.setImgUrl(imgUrlsArr[i]);
                }
                insLabel.setCreateTime(date);
                insLabel.setUpdateTime(date);
                institutionLabelMapper.insert(insLabel);
            }
        }
    }
}

package com.yuxin.wx.institution.impl;

import com.yuxin.wx.api.institution.ReServApplyService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.institution.mapper.ReServApplyMapper;
import com.yuxin.wx.model.institution.ReServApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReServApplyServiceImpl extends BaseServiceImpl implements ReServApplyService{
    @Autowired
    private ReServApplyMapper reServApplyMapper;


    @Override
    public void update(ReServApply reServApply) {
        reServApplyMapper.update(reServApply);
    }

    @Override
    public List<ReServApply> findReServApplyIns() {

        return reServApplyMapper.findReServApplyIns();
    }

    @Override
    public List<ReServApply> findReServApplyClass() {
        return reServApplyMapper.findReServApplyClass();
    }

    @Override
    public PageFinder<ReServApply> findReServApplyList(ReServApply reServApply){
        if(reServApply.getPage() == 1){
            reServApply.setPage(0);
        }else{
            reServApply.setPage((reServApply.getPage()-1)*reServApply.getPageSize());
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Integer count = 0;
        List<ReServApply> data = null;
        if(null != reServApply.getInsClassId() && !"".equals(reServApply.getInsClassId()+"")){
            //查找机构预约列表
            data = reServApplyMapper.findReServApplyListByClassId(reServApply);
            count = reServApplyMapper.findReServApplyListCountByClassId(reServApply);
       }else{
           //查找机构预约列表
           data = reServApplyMapper.findReServApplyList(reServApply);
           count = reServApplyMapper.findReServApplyListCount(reServApply);
       }

        int j=1;
        for(int i = 0;i<data.size();i++){
            data.get(i).setSort(j);
            data.get(i).setTime(format.format(data.get(i).getCreateTime()));
            j++;
        }

        PageFinder<ReServApply> pageFinder = new PageFinder<>(reServApply.getPage()/reServApply.getPageSize(),reServApply.getPageSize(),count,data);



        return pageFinder;
    }

    @Override
    public List<Map<Object,Object>> findReServApplyMap(ReServApply reServApply) {
        //查找机构预约列表
        List<Map<Object, Object>> data = reServApplyMapper.findReServApplyMap(reServApply);
        int j = 1;
        for (int i = 0; i < data.size(); i++) {
            data.get(i).put("id", j);
            j++;
            if((Integer)data.get(i).get("dealStatus") == 1){
                data.get(i).put("dealStatus","已处理");
            }else{
                data.get(i).put("dealStatus","未处理");
            }
        }
        return data;
    }


    @Override
    public List<Map<Object,Object>> findReServApplyMapByClass(ReServApply reServApply) {
        //查找机构预约列表
        List<Map<Object, Object>> data = reServApplyMapper.findReServApplyMapByClass(reServApply);
        int j = 1;
        for (int i = 0; i < data.size(); i++) {
            data.get(i).put("id", j);
            j++;
            if((Integer)data.get(i).get("dealStatus") == 1){
                data.get(i).put("dealStatus","已处理");
            }else{
                data.get(i).put("dealStatus","未处理");
            }
        }
        return data;
    }
}

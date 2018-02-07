package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.riseschool.mapper.RiseSchoolManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class RiseSchoolManageServiceImpl implements RiseSchoolManageService {
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private RiseSchoolManageMapper riseSchoolManageMapper;
    @Override
    public void insertRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        riseSchoolManageMapper.insertRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public PageFinder<RiseSchoolManageVo> queryRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        List<RiseSchoolManageVo> riseSchoolInfoVoList = riseSchoolManageMapper.queryRiseSchoolInfo(riseSchoolManageVo);
        Integer count = riseSchoolManageMapper.queryRiseSchoolCount(riseSchoolManageVo);
        PageFinder<RiseSchoolManageVo> pageFinder = new PageFinder<RiseSchoolManageVo>(riseSchoolManageVo.getPage(), riseSchoolManageVo.getPageSize(), count, riseSchoolInfoVoList);
        return pageFinder;
    }

    @Override
    public void insertRiseSchoolInfoAndUsers(Map map) throws Exception {
        RiseSchoolManageVo riseSchoolManageVo = (RiseSchoolManageVo) map.get("riseSchoolManageVo");
        Users users = (Users) map.get("users");
        //插入用户信息后再插入学校信息
        usersServiceImpl.insertSchoolManage(users);
        //判断用户是否插入成功
        if (users.getId() == null){
            throw new Exception("用户插入失败!");
        }
        riseSchoolManageVo.setUserId(users.getId());
        //插入学校信息
        this.insertRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public void updateRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        riseSchoolManageMapper.updateRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public List<SysDictVo> queryRiseSchoolDict(Map map) {
        return riseSchoolManageMapper.queryRiseSchoolDict(map);
    }

    @Override
    public PageFinder<RiseSchoolManageVo> queryDimRiseSchoolInfo(SearchRiseSchoolVo serchRiseSchoolVo) {
        List<RiseSchoolManageVo> riseSchoolInfoVoList = riseSchoolManageMapper.queryDimRiseSchoolInfo(serchRiseSchoolVo);
        Integer count = riseSchoolManageMapper.queryDimRiseSchoolCount(serchRiseSchoolVo);
        PageFinder<RiseSchoolManageVo> pageFinder = new PageFinder<RiseSchoolManageVo>(serchRiseSchoolVo.getPage(), serchRiseSchoolVo.getPageSize(), count, riseSchoolInfoVoList);
        return pageFinder;
    }
}

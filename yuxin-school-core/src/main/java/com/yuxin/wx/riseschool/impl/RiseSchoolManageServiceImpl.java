package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
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
    private RiseSchoolManageMapper riseSchoolInfoMapper;
    @Override
    public void insertRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        riseSchoolInfoMapper.insertRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public PageFinder<RiseSchoolManageVo> queryRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        List<RiseSchoolManageVo> riseSchoolInfoVoList = riseSchoolInfoMapper.queryRiseSchoolInfo(riseSchoolManageVo);
        Integer count = riseSchoolInfoMapper.queryRiseSchoolCount(riseSchoolManageVo);
        PageFinder<RiseSchoolManageVo> pageFinder = new PageFinder<RiseSchoolManageVo>(riseSchoolManageVo.getPage(), riseSchoolManageVo.getPageSize(), count, riseSchoolInfoVoList);
        return pageFinder;
    }

    @Override
    public void insertRiseSchoolInfoAndUsers(Map map) throws Exception {
        RiseSchoolManageVo riseSchoolManageVo = (RiseSchoolManageVo) map.get("riseSchoolManageVo");
        Users users = (Users) map.get("users");
        //插入用户信息后再插入学校信息
        usersServiceImpl.insert(users);
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
        riseSchoolInfoMapper.updateRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public List<SysDictVo> queryRiseSchoolInfo(Map map) {
        return riseSchoolInfoMapper.queryRiseSchoolInfo(map);
    }
}

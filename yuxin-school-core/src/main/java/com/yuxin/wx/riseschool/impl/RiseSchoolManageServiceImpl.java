package com.yuxin.wx.riseschool.impl;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.riseschool.RiseSchoolManageService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.riseschool.RiseSchoolManageVo;
import com.yuxin.wx.model.riseschool.SearchRiseSchoolVo;
import com.yuxin.wx.model.riseschool.SysDictVo;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.riseschool.mapper.RiseSchoolManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RiseSchoolManageServiceImpl implements RiseSchoolManageService {
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private RiseSchoolManageMapper riseSchoolManageMapper;
    @Autowired
    private IAuthUserRoleService authUserRoleServiceImpl;
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
        //添加用户关系表
        usersServiceImpl.insertUserCompanyRalation(users.getId(),users.getCompanyId());
        Integer curUserId = (Integer) map.get("curUserId");
        String roleCode = "where ap.privilege_name in ('rise_school')";
        Map<String,Object>params = new HashMap<String,Object>();
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

        riseSchoolManageVo.setUserId(users.getId());
        //插入学校信息
        this.insertRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public void updateRiseSchoolInfo(RiseSchoolManageVo riseSchoolManageVo) {
        riseSchoolManageMapper.updateRiseSchoolInfo(riseSchoolManageVo);
    }

    @Override
    public void updateRiseSchoolInfoTwo(RiseSchoolManageVo riseSchoolManageVo) {
        riseSchoolManageMapper.updateRiseSchoolInfoTwo(riseSchoolManageVo);
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

    @Override
    public RiseSchoolManageVo queryCurrentRiseSchoolInfo(Map<String, Object> params) {
        return riseSchoolManageMapper.queryCurrentRiseSchoolInfo(params);
    }

	@Override
	public RiseSchoolManageVo findSchoolById(Integer schoolId) {
		RiseSchoolManageVo schoolManageVo = riseSchoolManageMapper.findSchoolById(schoolId);
		return schoolManageVo;
	}

	@Override
	public String findSchoolNo() {
		String schoolNo = riseSchoolManageMapper.findSchoolNo();
		return schoolNo;
	}
}

package com.yuxin.wx.integral.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.integral.IIntegralManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.integral.ScoreDetailsAppVo;
import com.yuxin.wx.model.integral.ScoreRulsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;
import com.yuxin.wx.student.mapper.StudentMapper;
/**
 * Service Implementation:IIntegralManageService
 * 
 * @author zengdeqiang
 * @date 2017-11-28
 */
@Service
@Transactional
public class IntegralManageServiceImpl extends BaseServiceImpl implements IIntegralManageService {
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public TotalScoreVo queryTotalScoreVoByUserFrontId(String studentId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("studentId", studentId);
		List<TotalScoreVo> totalScoreVos=studentMapper.queryTotalScoreVoByUserFrontId(params);
		TotalScoreVo totalScoreVo=null;
		if(totalScoreVos!=null&&totalScoreVos.size()>0){
			totalScoreVo=totalScoreVos.get(0);
			if(totalScoreVo!=null){
				if(totalScoreVo.getTotalScoreId()!=null&&totalScoreVo.getTotalScoreId()!=""){
					params.put("totalScoreId", totalScoreVo.getTotalScoreId());
					List<ScoreDetailsAppVo> scoreDetailsAppVos=studentMapper.queryscoreDetailsAppByScoreDetailsApp(params);
					totalScoreVo.setScoreDetailsAppVos(scoreDetailsAppVos);
				}
			}
		}
		return totalScoreVo;
	}
	@Override
	public List<ScoreRulsAppVo> queryScoreRulsAppVos() {
		return studentMapper.queryScoreRulsAppVos();
	}
	@Override
	public Boolean saveOrUpdateTotalScore(String[] strVal,String userFrontId) {
		if(strVal==null||strVal.length==0) return true;
		//整理积分详细数据
		List<ScoreDetailsAppVo> scoreDetailsAppVos=new ArrayList<ScoreDetailsAppVo>();
		Integer totalScore=0;
		for(String str:strVal){
			if(str==null||str=="")continue;
			ScoreDetailsAppVo scoreDetailsAppVo=new ScoreDetailsAppVo();
			String[]strArray=str.split("_");
			scoreDetailsAppVo.setOrigin(strArray[0]);
			String itemScore=strArray[1];
			if(itemScore.contains("-")){
				scoreDetailsAppVo.setItemScore(itemScore.substring(1));
				scoreDetailsAppVo.setReduceAddFlag("0");
				totalScore-=Integer.valueOf(itemScore.substring(1));
			}else{
				if(itemScore.contains("+")){
					scoreDetailsAppVo.setItemScore(itemScore.substring(1));
					totalScore+=Integer.valueOf(itemScore.substring(1));
				}else{
					scoreDetailsAppVo.setItemScore(itemScore);
					totalScore+=Integer.valueOf(itemScore);
				}
				scoreDetailsAppVo.setReduceAddFlag("1");
			}
			scoreDetailsAppVo.setCreateTime(strArray[2]);
			scoreDetailsAppVo.setFixedPerson(strArray[3]);
			scoreDetailsAppVos.add(scoreDetailsAppVo);
		}
		//查询对应用户的积分情况
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userFrontId", userFrontId);
		List<TotalScoreVo> totalScoreVos=studentMapper.queryTotalScoreVoByUserId(params);
		//用户有总积分记录情况下
		if(totalScoreVos!=null&&totalScoreVos.size()>0){
			TotalScoreVo totalScoreVo=totalScoreVos.get(0);
			String allTotalScore=totalScoreVo.getTotalScore();
			if(allTotalScore!=null&&allTotalScore!=""){
				totalScoreVo.setTotalScore(Integer.valueOf(allTotalScore)+totalScore+"");
			}else{
				totalScoreVo.setTotalScore(totalScore+"");
			}
			//更新总积分表
			studentMapper.updateTotalScore(totalScoreVo);
			//插入积分详细表
			for(ScoreDetailsAppVo scoreDetailsAppVo:scoreDetailsAppVos){
				scoreDetailsAppVo.setTotalScoreId(totalScoreVo.getTotalScoreId());
			}
			studentMapper.insertScoreDetailsApp(scoreDetailsAppVos);
		}else{
			//用户没有总积分记录情况
			TotalScoreVo totalScoreVo=new TotalScoreVo();
			totalScoreVo.setTotalScore(totalScore+"");
			totalScoreVo.setUserId(userFrontId);
			//插入保存
			studentMapper.insertTotalScore(totalScoreVo);
			
			//查询刚插入的主键
			List<TotalScoreVo> prekeyTotalScoreVos=studentMapper.queryTotalScoreVoByUserFrontId(params);
			if(prekeyTotalScoreVos!=null&&prekeyTotalScoreVos.size()>0){
				for(ScoreDetailsAppVo scoreDetailsAppVo:scoreDetailsAppVos){
					scoreDetailsAppVo.setTotalScoreId(prekeyTotalScoreVos.get(0).getTotalScoreId());
				}
				studentMapper.insertScoreDetailsApp(scoreDetailsAppVos);
			}
		}
		return true;
	}

}

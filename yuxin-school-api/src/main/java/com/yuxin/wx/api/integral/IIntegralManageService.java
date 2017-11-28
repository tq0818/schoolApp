package com.yuxin.wx.api.integral;

import java.util.List;

import com.yuxin.wx.model.integral.ScoreRulsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;

/**
 * 学生积分接口
 * @author zengdeqiang
 *
 */
public interface IIntegralManageService {
	/**
	 * 通过学生用户标识号查询学生积分总体情况
	 * @param userFrontId 用户标识号
	 * @return 用户集合
	 */
	TotalScoreVo queryTotalScoreVoByUserFrontId(String studentId);
	/**
	 * 查询积分规则
	 * @return 
	 */
	List<ScoreRulsAppVo> queryScoreRulsAppVos();
	
	Boolean saveOrUpdateTotalScore(String[]strVal,String userFrontId);
}

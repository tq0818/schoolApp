package com.yuxin.wx.integral.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.integral.ScoreDetailsAppVo;
import com.yuxin.wx.model.integral.ScoreRulsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;

public interface TotalScoreMapper extends BaseMapper<TotalScoreVo>{
	/**
	 * 通过学生标识号返回集合
	 * @param studentId 学生标识号
	 * @return 返回对象值
	 */
	List<TotalScoreVo> queryTotalScoreVoByUserFrontId(String studentId);
	
	/**
	 * 返回积分详情集合
	 * @param totalScoreId 积分标识号
	 * @return 返回积分集合
	 */
	List<ScoreDetailsAppVo> queryscoreDetailsAppByScoreDetailsApp(String totalScoreId);
	/**
	 * 查询所有积分规则
	 * @return 返回积分规则集合
	 */
	List<ScoreRulsAppVo> queryScoreRulsAppVos();
}

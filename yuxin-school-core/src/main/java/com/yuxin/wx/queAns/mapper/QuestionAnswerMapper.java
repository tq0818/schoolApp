package com.yuxin.wx.queAns.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.integral.ScoreDetailsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionAnswer;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
	
	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询回复，根据问题id，等级
	 * @author 周文斌
	 * @date 2015-12-9 下午12:44:53
	 * @version 1.0
	 * @param ans
	 * @return
	 */
	List<QuestionAnswer> findAnsByQueId(QuestionAnswer ans);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 根据回复id 查询二级回复list
	 * @author 周文斌
	 * @date 2015-12-10 下午12:21:34
	 * @version 1.0
	 * @param answerId
	 * @return
	 */
	List<Integer> findTwoAns(Integer answerId);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 批量假删除  回复
	 * @author 周文斌
	 * @date 2015-12-10 下午12:27:12
	 * @version 1.0
	 * @param list
	 */
	void updateList(List<Integer> list);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 根据回复id 查询二级回复list
	 * @author 周文斌
	 * @date 2015-12-10 下午5:34:59
	 * @version 1.0
	 * @param answerId
	 * @return
	 */
	List<QuestionAnswer> findEntityTwoAns(QuestionAnswer one);
	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询总个数
	 * @author 周文斌
	 * @date 2015-12-10 下午7:24:57
	 * @version 1.0
	 * @param ans
	 * @return
	 */
	Integer findAnsCountByQueId(QuestionAnswer ans);
	/**
	 * 
	 * @author jishangyang 2017年11月29日 下午4:18:06
	 * @Method: updatethumbs 
	 * @Description:点赞操作
	 * @param ans
	 * @return 
	 * @throws
	 */
	Integer updatQuestionAnswer(QuestionAnswer ans);
	Integer updatQuestionAnswer1(QuestionAnswer ans);
	Integer insertThumbs(QuestionAnswer ans);
	Integer updatethumbs(QuestionAnswer ans);
	/**
	 * 
	 * @author jishangyang 2017年11月30日 下午5:09:12
	 * @Method: updateQuestionAdoptFlag 
	 * @Description: 更新采纳状态
	 * @param que
	 * @return 
	 * @throws
	 */
	Integer updateQuestionAdoptFlag( QueQuestion que);
	Integer updateAnswerAccept(QuestionAnswer ans);
	/**
	 * 
	 * @author jishangyang 2017年11月30日 下午9:09:51
	 * @Method: searchTotalScore 
	 * @Description: 通过提问人ID查询 是否有积分记录
	 * @param ans
	 * @return 
	 * @throws
	 */
	String searchTotalScore(TotalScoreVo ans);
	Integer inserTotalScore(TotalScoreVo vo);
	Integer updateTotalScore(TotalScoreVo vo);
	Integer insertScoreDetailsApp(ScoreDetailsAppVo vo);
	/**
	 * 查询回答数量
	 * @param questionId
	 * @return
	 */
	Integer queryAnswerCount(Integer questionId);
}
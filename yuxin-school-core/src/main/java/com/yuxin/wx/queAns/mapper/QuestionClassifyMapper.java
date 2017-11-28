package com.yuxin.wx.queAns.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.queAns.QuestionClassify;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface QuestionClassifyMapper extends BaseMapper<QuestionClassify> {
	List<QuestionClassify> findQuestionClassify(QuestionClassify search);
	List<QuestionClassify> findQuestionClassifyKc(QuestionClassify search);
	

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询标签
	 * @author 周文斌
	 * @date 2015-12-29 下午8:23:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<QuestionClassify> findClassifyByCompany(Map<String, Object> param);
	/**
	 * 
	 * @author jishangyang 2017年11月26日 下午7:59:51
	 * @Method: findSystemTag 
	 * @Description: 不分页查询系统标签
	 * @param param
	 * @return 
	 * @throws
	 */
	List<QuestionClassify> findSystemTag(QuestionClassify search);
	/**
	 * 
	 * @author jishangyang 2017年11月28日 下午3:06:19
	 * @Method: insertLab 
	 * @Description: 添加标签
	 * @param questionClassify
	 * @return 
	 * @throws
	 */
	Integer  insertLab(QuestionClassify questionClassify);
	/**
	 * 
	 * @author jishangyang 2017年11月28日 下午3:59:18
	 * @Method: deletLab 
	 * @Description: 删除标签
	 * @param questionClassify
	 * @return 
	 * @throws
	 */
	Integer  deletLab(QuestionClassify questionClassify);
}
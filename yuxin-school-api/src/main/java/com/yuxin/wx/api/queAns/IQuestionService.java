package com.yuxin.wx.api.queAns;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionClassify;
import com.yuxin.wx.vo.queAns.QuestionVo;
/**
 * Service Interface:Question
 * @author wang.zx
 * @date 2015-12-9
 */
public interface IQuestionService  {
	/**
	 * 
	* @Title: saveQuestion
	* @Description: 新增Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void insert(QueQuestion entity);
	
	/**
	 * 
	* @Title: batchSaveQuestion 
	* @Description: 批量新增Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void batchInsert(List<QueQuestion> list);
	
	/**
	 * 
	* @Title: updateQuestion 
	* @Description: 编辑Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void update(QueQuestion entity);
	
	/**
	 * 
	* @Title: deleteQuestionById 
	* @Description: 根据id删除Question
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionById(Integer id);
	
	/**
	 * 
	* @Title: deleteQuestionByIds 
	* @Description: 根据id批量删除Question
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findQuestionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	QueQuestion findQuestionById(Integer id);
	
	/**
	 * 
	* @Title: findQuestionByPage 
	* @Description: 分页查询
	* @return
	* @return List<Question>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	List<QueQuestion> findQuestionByPage(QueQuestion search);
	
	/**
	 * 
	 * Class Name: IQuestionService.java
	 * @Description: 分页查询vo
	 * @author yuchanglong
	 * @date 2015年12月9日 下午5:19:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<QuestionVo> findVoByPage(QueQuestion search);

	List<QuestionVo> findTeacherQuestion(Map<String, Object> search);
	/**
	 * 
	 * @author jishangyang 2017年11月28日 下午2:58:06
	 * @Method: insert 
	 * @Description: 添加标签
	 * @param questionClassify
	 * @return 
	 * @throws
	 */
	boolean  insert(QuestionClassify questionClassify);
	/**
	 * 
	 * @author jishangyang 2017年11月29日 上午10:21:38
	 * @Method: insertLabReturnId 
	 * @Description: 点击发布按钮 保存自定义标签 保存发布问题
	 * @param arrTagName
	 * @param arrTagId
	 * @param queQuestion
	 * @return 
	 * @throws
	 */
	boolean  insertLabReturnId(String [] arrTagName,String [] arrTagId,QueQuestion queQuestion);
	/**
	 * 
	 * @author jishangyang 2017年11月28日 下午3:58:09
	 * @Method: delet 
	 * @Description: 删除标签
	 * @param questionClassify
	 * @return 
	 * @throws
	 */
	boolean  delet(QuestionClassify questionClassify);
	/**
	 * 
	 * @author jishangyang 2017年11月29日 下午1:14:04
	 * @Method: check 
	 * @Description: 检验标签是否重名
	 * @param questionClassify
	 * @return 
	 * @throws
	 */
	boolean  check(QuestionClassify questionClassify);
}
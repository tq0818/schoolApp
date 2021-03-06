package com.yuxin.wx.queAns.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.queAns.IQuestionAnswerService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.integral.ScoreDetailsAppVo;
import com.yuxin.wx.model.integral.TotalScoreVo;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.queAns.mapper.QuestionAnswerMapper;

/**
 * Service Implementation:QuestionAnswer
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@Service
@Transactional
public class QuestionAnswerServiceImpl extends BaseServiceImpl implements IQuestionAnswerService {

    @Autowired
    private QuestionAnswerMapper QuestionAnswerMapper;
    
    
   

    /**
     * 
     * @Title: saveQuestionAnswer
     * @Description: 新增QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void insert(QuestionAnswer entity) {
        QuestionAnswerMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveQuestionAnswer
     * @Description: 批量新增QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void batchInsert(List<QuestionAnswer> T) {
        QuestionAnswerMapper.batchInsert(T);
    };

    /**
     * 
     * @Title: updateQuestionAnswer
     * @Description: 编辑QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void update(QuestionAnswer T) {
        QuestionAnswerMapper.update(T);
    };

    /**
     * 
     * @Title: deleteQuestionAnswerById
     * @Description: 根据id删除QuestionAnswer
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void deleteQuestionAnswerById(Integer id) {
        QuestionAnswerMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteQuestionAnswerByIds
     * @Description: 根据id批量删除QuestionAnswer
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void deleteQuestionAnswerByIds(Integer[] ids) {
        QuestionAnswerMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findQuestionAnswerById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public QuestionAnswer findQuestionAnswerById(Integer id) {
        return QuestionAnswerMapper.findById(id);
    };

    /**
     * 
     * @Title: findQuestionAnswerByPage
     * @Description: 分页查询
     * @return
     * @return List<QuestionAnswer> 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public List<QuestionAnswer> findQuestionAnswerByPage(QuestionAnswer search) {
        return QuestionAnswerMapper.page(search);
    }

    @Override
    public List<QuestionAnswer> findAnsByQueId(QuestionAnswer ans) {
        return QuestionAnswerMapper.findAnsByQueId(ans);
    }

    @Override
    public List<Integer> findTwoAns(Integer answerId) {
        return QuestionAnswerMapper.findTwoAns(answerId);
    }

    @Override
    public void updateList(List<Integer> list) {
        QuestionAnswerMapper.updateList(list);
    }

    @Override
    public List<QuestionAnswer> findEntityTwoAns(QuestionAnswer one) {
        return QuestionAnswerMapper.findEntityTwoAns(one);
    }

    @Override
    public Integer findAnsCountByQueId(QuestionAnswer ans) {
        // TODO Auto-generated method stub
        return QuestionAnswerMapper.findAnsCountByQueId(ans);
    }

	@Override
	public boolean updatethumbs(QuestionAnswer ans) {
		 
		if(ans.getIsThumbs()==1){
			//更新点赞数量+1
			QuestionAnswerMapper.updatQuestionAnswer(ans);
			if(ans.getThumbsFlag()==1){
				//插入点赞状态
				QuestionAnswerMapper.insertThumbs(ans);	
			}else{
				//更新点赞状态
				QuestionAnswerMapper.updatethumbs(ans);
			}
			
		}else{
			//更新点赞数量-1
			QuestionAnswerMapper.updatQuestionAnswer1(ans);
			if(ans.getThumbsFlag()==1){
				//插入点赞状态
				QuestionAnswerMapper.insertThumbs(ans);	
			}else{
				//更新点赞状态
				QuestionAnswerMapper.updatethumbs(ans);
			}
		}
		return true;
	}

	@Override
	public boolean updateQAndA(QuestionAnswer ans, QueQuestion que) {
		TotalScoreVo vo =new TotalScoreVo();
		TotalScoreVo voo =new TotalScoreVo();
		ScoreDetailsAppVo vo1 =new ScoreDetailsAppVo();
		ScoreDetailsAppVo vo2 =new ScoreDetailsAppVo();
		vo.setUserId(ans.getUserId().toString());
		vo.setTotalScore(que.getQuestionscore().toString());
		QuestionAnswerMapper.updateQuestionAdoptFlag(que);
		QuestionAnswerMapper.updateAnswerAccept(ans);
		vo1.setReduceAddFlag("0");//0减少
		vo1.setOrigin("采纳");
		vo1.setItemScore(que.getQuestionscore().toString());
		vo2.setReduceAddFlag("1");//1增加
		vo2.setOrigin("采纳");
		vo2.setItemScore(que.getQuestionscore().toString());
		//查询问题回答人是否有记录 没有就插入 有就更新
		voo.setUserId(ans.getUserId().toString());
		String id=QuestionAnswerMapper.searchTotalScore(voo);
		if(null!=id && !"".equals(id)){
			QuestionAnswerMapper.updateTotalScore(vo);
			vo1.setTotalScoreId(String.valueOf(id));
			vo2.setTotalScoreId(String.valueOf(id));
			QuestionAnswerMapper.insertScoreDetailsApp(vo1);
			QuestionAnswerMapper.insertScoreDetailsApp(vo2);
		}else{
			QuestionAnswerMapper.inserTotalScore(vo);
			vo1.setTotalScoreId(String.valueOf(vo.getId()));
			vo2.setTotalScoreId(String.valueOf(vo.getId()));
			QuestionAnswerMapper.insertScoreDetailsApp(vo1);
			QuestionAnswerMapper.insertScoreDetailsApp(vo2);
		}
		
		return true;
	};
}

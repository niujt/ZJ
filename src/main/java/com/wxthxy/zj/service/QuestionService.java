package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.ApplicationQuestion;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    /**
     * 获取所有题目的数目
     * @return
     */
    Map<String,Integer> getQuestionCounts();

    /**
     * 获取各类型题目列表
     * @param type
     * @return
     */
    List findAllQuestions(String type);

    /**
     * 添加选择题
     * @param question
     * @return
     */
    String addChoiceQuestion(Choicequestion question);

    /**
     * 删除选择题
     * @param id
     * @return
     */
    String delChoiceQuestion(Integer id);

    /**
     * 选择题详情
     * @param id
     * @return
     */
    Choicequestion getChoicequestion(Integer id);

    /**
     * 更新选择题
     * @param choicequestion
     * @return
     */
    String updateChoiceQuestion(Choicequestion choicequestion);

    /**
     * 删除应用题
     * @param id
     * @return
     */
    String deleteAppQue(Integer id);

    /**
     * 添加应用题
     * @param applicationQuestion
     * @return
     */
    String addApplicationQue(ApplicationQuestion applicationQuestion);

    /**
     * 应用题详情
     * @param id
     * @return
     */
    ApplicationQuestion loadApplication(Integer id);

    /**
     * 更新应用题
     * @param applicationQuestion
     * @return
     */
    String updateApplication(ApplicationQuestion applicationQuestion);
/**出选择题,应用题以外，其他使用Question数据模型*/
    /**
     * 添加
     * @param question
     * @return
     */
    String addQuestion(Question question);

    /**
     * 更新
     * @param question
     * @return
     */
    String updateQuestion(Question question);

    /**
     * 详情
     * @param id
     * @return
     */
    Question findQuestionByid(Integer id,String type);

    /**
     * 删除
     * @param id
     * @return
     */
    String delQuestion(Integer id,String type);
}

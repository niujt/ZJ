package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Choicequestion;

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
}

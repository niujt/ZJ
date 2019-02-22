package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;

import java.util.List;

public interface JudgementquestionDAO {
    Integer getCount();
    List<Question> getAllJudgementquestions();
    int addJudgementQuestion(Question question);
}

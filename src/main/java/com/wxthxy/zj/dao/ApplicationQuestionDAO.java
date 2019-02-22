package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;

import java.util.List;

public interface ApplicationQuestionDAO {
    Integer getCount();
    List<Question> getAllApplicationQuestion();
    int addApplicationQuestion(Question question);
}

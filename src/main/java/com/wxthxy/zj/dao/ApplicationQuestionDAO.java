package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.ApplicationQuestion;

import java.util.List;

public interface ApplicationQuestionDAO {
    Integer getCount();
    List<ApplicationQuestion> getAllApplicationQuestion();
    int addApplicationQuestion(ApplicationQuestion completion);
}

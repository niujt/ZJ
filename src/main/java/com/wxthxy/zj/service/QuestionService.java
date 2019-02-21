package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    Map<String,Integer> getQuestionCounts();
    List findAllQuestions(String type);
}

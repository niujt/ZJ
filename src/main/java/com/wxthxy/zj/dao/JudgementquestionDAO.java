package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Judgementquestion;

import java.util.List;

public interface JudgementquestionDAO {
    Integer getCount();
    List<Judgementquestion> getAllJudgementquestions();
    int addJudgementQuestion(Judgementquestion judgementquestion);
}

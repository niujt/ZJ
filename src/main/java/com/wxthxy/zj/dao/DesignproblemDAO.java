package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;

import java.util.List;

public interface DesignproblemDAO {
    Integer getCount();
    List<Question> getAllDesignproblems();
    int addDesignProblem(Question question);
}

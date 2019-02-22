package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Designproblem;

import java.util.List;

public interface DesignproblemDAO {
    Integer getCount();
    List<Designproblem> getAllDesignproblems();
    int addDesignProblem(Designproblem designproblem);
}

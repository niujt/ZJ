package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DesignproblemDAO {
    Integer getCount();
    List<Question> getAllDesignproblems();
    int addDesignProblem(Question question);
    List<Question> findDesignProblem4Paper(@Param("dpids") List<Integer> dpids);
}

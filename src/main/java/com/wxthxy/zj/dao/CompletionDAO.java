package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompletionDAO {
    Integer getCount();
    List<Question> getAllCompletions();
    int addCompletion(Question question);
    Question findCompletionByid(@Param("id") Integer id);
    int updateCompletionByid(Question question);
    int deleteCompletionByid(@Param("id") Integer id);
    List<Question> findCompletion4Paper(@Param("cpids")List<Integer> cpids);
}

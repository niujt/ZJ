package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface JudgementquestionDAO {
    Integer getCount();
    List<Question> getAllJudgementquestions();
    int addJudgementQuestion(Question question);
    List<Question> findJudgementQuestion4Paper(@Param("jqids") List<Integer> jqids);
    int deleteJudgementQuestionById(@Param("id") Integer id);
}

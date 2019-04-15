package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface JudgementquestionDAO {
    /**
     * 判断题的数量
     * @return
     */
    Integer getCount();

    /**
     * 获取所有的判断题详情
     * @return
     */
    List<Question> getAllJudgementquestions();

    List<Question> getAllJudgementquestionsByChapter(@Param("chapter")String chapter);

    /**
     * 添加判断题
     * @param question
     * @return
     */
    int addJudgementQuestion(Question question);

    /**
     * 试题所有的判断题
     * @param jqids
     * @return
     */
    List<Question> findJudgementQuestion4Paper(@Param("jqids") List<Integer> jqids);

    /**
     * 根据id删除判断题
     * @param id
     * @return
     */
    int deleteJudgementQuestionById(@Param("id") Integer id);

    /**
     * 更新判断题
     * @param question
     * @return
     */
    int updateJudgementQuestionById(Question question);

    /**
     * 根据id查找判断题
     * @param id
     * @return
     */
    Question findJudgementquestionsById(@Param("id") Integer id);
}

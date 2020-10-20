package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompletionDAO {
    /**
     * 获取填空题数量
     * @return
     */
    Integer getCount();

    List<Question> getAllCompletionsByChapter(@Param("chapter")String chapter);
    /**
     * 获取所有的填空题详情
     * @return
     */
    List<Question> getAllCompletions();

    /**
     * 添加填空题
     * @param question
     * @return
     */
    int addCompletion(Question question);

    /**
     * 填空题详情
     * @param id
     * @return
     */
    Question findCompletionByid(@Param("id") Integer id);

    /**
     * 更新填空题
     * @param question
     * @return
     */
    int updateCompletionById(Question question);

    /**
     * 删除填空题
     * @param id
     * @return
     */
    int deleteCompletionByid(@Param("id") Integer id);

    /**
     * 试卷所有的填空题
     * @param cpids
     * @return
     */
    List<Question> findCompletion4Paper(@Param("cpids")List<Integer> cpids);
}

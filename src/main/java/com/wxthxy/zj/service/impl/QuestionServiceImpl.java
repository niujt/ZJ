package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.ChoicequestionDAO;
import com.wxthxy.zj.dao.CompletionDAO;
import com.wxthxy.zj.dao.DesignproblemDAO;
import com.wxthxy.zj.dao.JudgementquestionDAO;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    CompletionDAO completionDAO;
    @Autowired
    ChoicequestionDAO choicequestionDAO;
    @Autowired
    DesignproblemDAO designproblemDAO;
    @Autowired
    JudgementquestionDAO judgementquestionDAO;
    @Override
    public Map<String, Integer> getQuestionCounts() {
        Map<String,Integer> counts=new HashMap<String,Integer>();
        counts.put("选择题",choicequestionDAO.getCount());
        counts.put("填空题",completionDAO.getCount());
        counts.put("判断题",judgementquestionDAO.getCount());
        counts.put("应用题",designproblemDAO.getCount());
        counts.put("简答题",10);
        return counts;
    }

    @Override
    public List findAllQuestions(String type) {
        List questions=null;
        if(type.equals("选择题")){
            questions=choicequestionDAO.findAllChoicequestion();
        }
        else if(type.equals("填空题")){
            questions=completionDAO.getAllCompletions();
        }
        else if(type.equals("判断题")){
            questions=judgementquestionDAO.getAllJudgementquestions();
        }
        else if(type.equals("简答题")){
            questions=designproblemDAO.getAllDesignproblems();
        }
        else if(type.equals("应用题")){
            //未开发
            questions=null;
        }
        return questions;

    }
}

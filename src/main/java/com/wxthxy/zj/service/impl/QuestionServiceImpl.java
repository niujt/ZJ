package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.*;
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
    @Autowired
    ApplicationQuestionDAO applicationQuestionDAO;
    @Override
    public Map<String, Integer> getQuestionCounts() {
        Map<String,Integer> counts=new HashMap<String,Integer>();
        counts.put("选择题",choicequestionDAO.getCount());
        counts.put("填空题",completionDAO.getCount());
        counts.put("判断题",judgementquestionDAO.getCount());
        counts.put("应用题",applicationQuestionDAO.getCount());
        counts.put("简答题",designproblemDAO.getCount());
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
            questions=applicationQuestionDAO.getAllApplicationQuestion();
        }
        return questions;

    }

    @Override
    public String addChoiceQuestion(Choicequestion question) {
      return choicequestionDAO
              .addChoicequestion((Choicequestion) question)>0? ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();

    }

    @Override
    public String delChoiceQuestion(Integer id) {
        return choicequestionDAO
                .delChoicequestion(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }

    @Override
    public Choicequestion getChoicequestion(Integer id) {
        return choicequestionDAO.getChoiceQuestionByid(id);
    }

    @Override
    public String updateChoiceQuestion(Choicequestion choicequestion) {
        return choicequestionDAO.updateChoiceQuestion(choicequestion)>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public String addQuestion(Question question) {
        int index=0;
        switch (question.getType()){
            case "填空题" :
                index=completionDAO.addCompletion(question);
                break;
            case "判断题":
                index=judgementquestionDAO.addJudgementQuestion(question);
                break;
            case "简答题":
                index=designproblemDAO.addDesignProblem(question);
                break;
        }
            return index>0?ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    @Override
    public String updateQuestion(Question question) {
        return completionDAO.updateCompletionByid(question)>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public Question findQuestionByid(Integer id) {
        return completionDAO.findCompletionByid(id);
    }

    @Override
    public String delQuestion(Integer id) {
        return completionDAO.deleteCompletionByid(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }
}

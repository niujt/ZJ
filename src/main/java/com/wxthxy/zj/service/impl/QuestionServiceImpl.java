package com.wxthxy.zj.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.*;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    public PageBean findAllQuestions(String type,Integer currentPage) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageBean pageData=null;
        PageHelper.startPage(currentPage, PageBean.pageSize);
        if(type.equals("选择题")){
            List<Choicequestion> choicequestions=choicequestionDAO.findAllChoicequestion();
            int countNums = choicequestionDAO.getCount();           //总记录数
            pageData= new PageBean<>(currentPage, PageBean.pageSize, countNums);
            pageData.setItems(choicequestions);
        }
        else if(type.equals("填空题")){
            List<Question> questions=completionDAO.getAllCompletions();
            int countNums = completionDAO.getCount();           //总记录数
            pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
            pageData.setItems(questions);
        }
        else if(type.equals("判断题")){
            List<Question> questions=judgementquestionDAO.getAllJudgementquestions();
            int countNums = judgementquestionDAO.getCount();           //总记录数
            pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
            pageData.setItems(questions);
        }
        else if(type.equals("简答题")){
            List<Question> questions=designproblemDAO.getAllDesignproblems();
            int countNums = designproblemDAO.getCount();           //总记录数
            pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
            pageData.setItems(questions);
        }
        else if(type.equals("应用题")){
            List<ApplicationQuestion> questions=applicationQuestionDAO.getAllApplicationQuestion();
            int countNums = applicationQuestionDAO.getCount();           //总记录数
            pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
            pageData.setItems(questions);
        }
        return pageData;

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
        int index=0;
        switch (question.getType()){
            case "填空题" :
                index=completionDAO.updateCompletionById(question);
                break;
            case "判断题":
                index=judgementquestionDAO.updateJudgementQuestionById(question);
                break;
            case "简答题":
                index=designproblemDAO.updateDesignProblemById(question);
                break;
        }
        return index>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public Question findQuestionByid(Integer id,String type) {
        Question question=null;
       switch (type){
           case "填空题" :
               question=completionDAO.findCompletionByid(id);
               break;
           case "判断题":
               question=judgementquestionDAO.findJudgementquestionsById(id);
               break;
           case "简答题":
               question=designproblemDAO.findDesignproblemByid(id);
               break;
       }
       return question;
    }

    @Override
    public String delQuestion(Integer id,String type) {
        int index=0;
        switch (type){
            case "填空题" :
                index=completionDAO.deleteCompletionByid(id);
                break;
            case "判断题":
                index=judgementquestionDAO.deleteJudgementQuestionById(id);
                break;
            case "简答题":
                index=designproblemDAO.deleteDesignProblemById(id);
                break;
        }
        return index>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }

    @Override
    public String deleteAppQue(Integer id) {
        return applicationQuestionDAO.deleteById(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }

    @Override
    public String addApplicationQue(ApplicationQuestion applicationQuestion) {
        return  applicationQuestionDAO.addApplicationQuestion(applicationQuestion)>0?ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    @Override
    public ApplicationQuestion loadApplication(Integer id) {
        return applicationQuestionDAO.getApplicationQuestionById(id);
    }

    @Override
    public String updateApplication(ApplicationQuestion applicationQuestion) {
        return applicationQuestionDAO.updateApplicationQue(applicationQuestion)>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public Map findAllQuestions(String type) {
        List questions=null;
        Map map=new LinkedHashMap();
        if(type.equals("选择题")){
            questions=choicequestionDAO.findAllChoicequestionByChapter("第一章");
            map.put("第一章",questions);
            questions=choicequestionDAO.findAllChoicequestionByChapter("第二章");
            map.put("第二章",questions);
            questions=choicequestionDAO.findAllChoicequestionByChapter("第三章");
            map.put("第三章",questions);
            questions=choicequestionDAO.findAllChoicequestionByChapter("第四章");
            map.put("第四章",questions);
            questions=choicequestionDAO.findAllChoicequestionByChapter("第五章");
            map.put("第五章",questions);
        }

        else if(type.equals("填空题")){
            questions=completionDAO.getAllCompletionsByChapter("第一章");
            map.put("第一章",questions);
            questions=completionDAO.getAllCompletionsByChapter("第二章");
            map.put("第二章",questions);
            questions=completionDAO.getAllCompletionsByChapter("第三章");
            map.put("第三章",questions);
            questions=completionDAO.getAllCompletionsByChapter("第四章");
            map.put("第四章",questions);
            questions=completionDAO.getAllCompletionsByChapter("第五章");
            map.put("第五章",questions);
        }
        else if(type.equals("判断题")){
            questions=judgementquestionDAO.getAllJudgementquestionsByChapter("第一章");
            map.put("第一章",questions);
            questions=judgementquestionDAO.getAllJudgementquestionsByChapter("第二章");
            map.put("第二章",questions);
            questions=judgementquestionDAO.getAllJudgementquestionsByChapter("第三章");
            map.put("第三章",questions);
            questions=judgementquestionDAO.getAllJudgementquestionsByChapter("第四章");
            map.put("第四章",questions);
            questions=judgementquestionDAO.getAllJudgementquestionsByChapter("第五章");
            map.put("第五章",questions);
        }
        else if(type.equals("简答题")){
            questions=designproblemDAO.getAllDesignproblemsByChapter("第一章");
            map.put("第一章",questions);
            questions=designproblemDAO.getAllDesignproblemsByChapter("第二章");
            map.put("第二章",questions);
            questions=designproblemDAO.getAllDesignproblemsByChapter("第三章");
            map.put("第三章",questions);
            questions=designproblemDAO.getAllDesignproblemsByChapter("第四章");
            map.put("第四章",questions);
            questions=designproblemDAO.getAllDesignproblemsByChapter("第五章");
            map.put("第五章",questions);
        }
        else if(type.equals("应用题")){
            questions=applicationQuestionDAO.getAllApplicationQuestionByChapter("第一章");
            map.put("第一章",questions);
            questions=applicationQuestionDAO.getAllApplicationQuestionByChapter("第二章");
            map.put("第二章",questions);
            questions=applicationQuestionDAO.getAllApplicationQuestionByChapter("第三章");
            map.put("第三章",questions);
            questions=applicationQuestionDAO.getAllApplicationQuestionByChapter("第四章");
            map.put("第四章",questions);
            questions=applicationQuestionDAO.getAllApplicationQuestionByChapter("第五章");
            map.put("第五章",questions);
        }
        return map;
    }
}

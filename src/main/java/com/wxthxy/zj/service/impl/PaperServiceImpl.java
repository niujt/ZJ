package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.*;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl  implements PaperService {
    @Autowired
    PaperDAO paperDAO;
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
    public List<Paper> getAll() {

        return paperDAO.findAllPapers();
    }

    @Override
    public Map getPaperById(Integer id) {
        Map map=new HashMap();
        Paper paper=paperDAO.findPaperById(id);
        map.put("paper",paper);
        //获取所有选择题id
        List<Integer> cqids= PaperUtils.getQuestionIds(paper.getCq());
        //获取选择题列表
        List<Choicequestion> cqs=choicequestionDAO.findChoicequestion4Paper(cqids);
        //创建选择题答案列表
        List<String> cqanswers=new ArrayList<>();
        //创建答案编号
        int cqindex=1,cpindex=1,aqindex=1,jqindex=1,dpindex=1;
        //答案
        Answer answer=new Answer();
        for(Choicequestion q:cqs){
            //添加选择题答案
            cqanswers.add(cqindex+"."+q.getAnswer());
            answer.setCqanwsers(cqanswers);
            cqindex++;
        }
        map.put("choicequestions",cqs);
        //获取应用题id
        List<Integer> aqids= PaperUtils.getQuestionIds(paper.getAq());
        //获取应用题列表
        List<ApplicationQuestion> aqs=applicationQuestionDAO.findApplicationQuestion4Paper(aqids);
        List<String> aqanswers=new ArrayList<>();
        for(ApplicationQuestion aq:aqs){
            //添加应用题答案
            aqanswers.add(aqindex+"."+aq.getAnswer());
            answer.setAqanswers(aqanswers);
            aqindex++;
        }
        map.put("applicationquestions",aqs);
        //获取填空题id
        List<Integer> cpids= PaperUtils.getQuestionIds(paper.getCp());
        //获取填空题列表
        List<Question> cps=completionDAO.findCompletion4Paper(cpids);
        List<String> cpanswers=new ArrayList<>();
        //创建填空题答案列表
        for(Question q:cps){
            //添加填空题答案
            cpanswers.add(cpindex+"."+q.getAnswer());
            answer.setCpanwsers(cpanswers);
            cpindex++;
        }
        map.put("completions",cps);
        //获取简答题id
        List<Integer> dpids= PaperUtils.getQuestionIds(paper.getDp());
        //获取简单题列表
        List<Question> dps=designproblemDAO.findDesignProblem4Paper(dpids);
        //创建简答题答案列表
        List<String> dpanswers=new ArrayList<>();
        for(Question q:dps){
            //添加简答题答案
            dpanswers.add(dpindex+"."+q.getAnswer());
            answer.setDpanwsers(dpanswers);
            dpindex++;
        }
        map.put("designproblems",dps);
        //获取判断题id
        List<Integer> jqids= PaperUtils.getQuestionIds(paper.getJq());
        //获取判断题列表
        List<Question> jqs=judgementquestionDAO.findJudgementQuestion4Paper(jqids);
        //创建判断题答案列表
        List<String> jqanswers=new ArrayList<>();
        for(Question q:jqs){
            //添加判断题答案
            jqanswers.add(jqindex+"."+q.getAnswer());
            answer.setJqanwsers(jqanswers);
            jqindex++;
        }
        map.put("judgementQuestions",jqs);
        map.put("answers",answer);
        return map;
    }
}

package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        map.put("name",paper.getName());
        List<Integer> cqids= PaperUtils.getQuestionIds(paper.getCq());
        map.put("choicequestions",choicequestionDAO.findChoicequestion4Paper(cqids));
        //List<Integer> aqids= PaperUtils.getQuestionIds(paper.getAq());
        List<Integer> cpids= PaperUtils.getQuestionIds(paper.getCp());
        map.put("completions",completionDAO.findCompletion4Paper(cpids));
        List<Integer> dpids= PaperUtils.getQuestionIds(paper.getDp());
        map.put("designproblems",designproblemDAO.findDesignProblem4Paper(dpids));
        List<Integer> jqids= PaperUtils.getQuestionIds(paper.getJq());
        map.put("judgementQuestions",judgementquestionDAO.findJudgementQuestion4Paper(jqids));
        return map;
    }
}

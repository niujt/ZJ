package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.common.ServiceMessage;
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
    public List<Paper> getAll(Integer pageNum,Integer pageSize) {

        return paperDAO.findAllPapers(pageNum,pageSize);
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

    @Override
    public String getPaperByAuto(AutoPaper paper) {
        //该map用于存放随机的各类型题号
        Map map=new HashMap();
        //试题总分
        double score=0.0;
        //获取随机选择题id的集合
        List<Integer> cqids=new ArrayList<>();
        for(Choicequestion choicequestion:choicequestionDAO.findAllChoicequestion(0,10000)){
            cqids.add(choicequestion.getId());
        }
        map.put("cqids",getIds(cqids,paper.getChoiceQueNumber()));
        //选择题总分
        List<Choicequestion> list1=choicequestionDAO.findChoicequestion4Paper(PaperUtils.getQuestionIds(getIds(cqids,paper.getChoiceQueNumber())));
        for(Choicequestion q:list1){
            score+=Double.parseDouble(q.getScore());
        }
        //获取随机应用id的集合
        List<Integer> aqids=new ArrayList<>();
        for(ApplicationQuestion applicationQuestion:applicationQuestionDAO.getAllApplicationQuestion(1,10000)){
            aqids.add(applicationQuestion.getId());
        }
        map.put("aqids",getIds(aqids,paper.getAppQueNumber()));
        //应用题总分
        List<ApplicationQuestion> list2=applicationQuestionDAO.findApplicationQuestion4Paper(PaperUtils.getQuestionIds(getIds(aqids,paper.getAppQueNumber())));
        for(ApplicationQuestion q:list2){
            score+=Double.parseDouble(q.getScore());
        }
        //获取随机填空题id的集合
        List<Integer> cpids=new ArrayList<>();
        for(Question question:completionDAO.getAllCompletions(0,10000)){
            cpids.add(question.getId());
        }
        map.put("cpids",getIds(cpids,paper.getCompQueNumber()));
        //填空题总分
        List<Question> list3=completionDAO.findCompletion4Paper(PaperUtils.getQuestionIds(getIds(cpids,paper.getCompQueNumber())));
        for(Question q:list3){
            score+=Double.parseDouble(q.getScore());
        }
        //获取随机判断题id的集合
        List<Integer> jqids=new ArrayList<>();
        for(Question question:judgementquestionDAO.getAllJudgementquestions(0,10000)){
            jqids.add(question.getId());
        }
        map.put("jqids",getIds(jqids,paper.getJudgeQueNumber()));
        //判断题总分
        List<Question> list4=judgementquestionDAO.findJudgementQuestion4Paper(PaperUtils.getQuestionIds(getIds(jqids,paper.getJudgeQueNumber())));
        for(Question q:list4){
            score+=Double.parseDouble(q.getScore());
        }
        //获取随机简答题id的集合
        List<Integer> dpids=new ArrayList<>();
        for(Question question:designproblemDAO.getAllDesignproblems(0,10000)){
            dpids.add(question.getId());
        }
        map.put("dpids",getIds(dpids,paper.getDesignQueNumber()));
        //简答题总分
        List<Question> list5=designproblemDAO.findDesignProblem4Paper(PaperUtils.getQuestionIds(getIds(dpids,paper.getDesignQueNumber())));
        for(Question q:list5){
            score+=Double.parseDouble(q.getScore());
        }
        //添加考题
        Paper paper1=new Paper();
        paper1.setJq((String)map.get("jqids"));
        paper1.setAq((String)map.get("aqids"));
        paper1.setCq((String)map.get("cqids"));
        paper1.setDp((String)map.get("dpids"));
        paper1.setCp((String)map.get("cpids"));
        paper1.setName(paper.getName());
        paper1.setScore(score+"");
        return paperDAO.addPaper(paper1)>0? ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    /**
     * 从题库中id集合抽取随机id集合
     * @param oldids
     * @param number
     * @return
     */
    public String getIds(List<Integer> oldids,int number){
        return PaperUtils.autoQustionId(oldids,number);

    }

    @Override
    public String getPaperByManual(ManualPaper manualPaper) {
        String score="";
        int _score=2*manualPaper.getCq().length+1*manualPaper.getJq().length+5*manualPaper.getCp().length+10*manualPaper.getDp().length+20*manualPaper.getAq().length;
        score=_score+".0";
        Paper paper=new Paper(manualPaper.getName()
                ,PaperUtils.array2String(manualPaper.getCq())
                ,PaperUtils.array2String(manualPaper.getCp())
                ,PaperUtils.array2String(manualPaper.getDp())
                ,PaperUtils.array2String(manualPaper.getJq())
                ,PaperUtils.array2String(manualPaper.getAq()),score);
        return  paperDAO.addPaper(paper)>0? ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    @Override
    public int getCount() {
        return paperDAO.getCount();
    }
}

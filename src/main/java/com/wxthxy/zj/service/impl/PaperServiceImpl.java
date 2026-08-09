package com.wxthxy.zj.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.*;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.utils.PageBean;
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
    public PageBean<Paper> getAll(Integer currentPage,String name) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, PageBean.pageSize);
        List<Paper> allItems = paperDAO.findAllPapers(name);
        int countNums = paperDAO.getCount(name);           //总记录数
        PageBean<Paper> pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
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
        //返回信息
        StringBuilder message=new StringBuilder();
        //获取随机选择题id的集合
        List<Integer> cqids=new ArrayList<>();
        for(Choicequestion choicequestion:choicequestionDAO.findAllChoicequestionByChapter(paper.getChoiceQueChapter())){
            cqids.add(choicequestion.getId());
        }
        map.put("cqids",getIds(cqids,paper.getChoiceQueNumber()));
        //选择题总分
        if(!getIds(cqids,paper.getChoiceQueNumber()).equals("error")){
            List<Choicequestion> list1=choicequestionDAO.findChoicequestion4Paper(PaperUtils.getQuestionIds(getIds(cqids,paper.getChoiceQueNumber())));
            for(Choicequestion q:list1){
                score+=Double.parseDouble(q.getScore());
            }
        }
        //获取随机应用id的集合
        List<Integer> aqids=new ArrayList<>();
        for(ApplicationQuestion applicationQuestion:applicationQuestionDAO.getAllApplicationQuestionByChapter(paper.getAppQueChapter())){
            aqids.add(applicationQuestion.getId());
        }
        map.put("aqids",getIds(aqids,paper.getAppQueNumber()));
        //应用题总分
        if(!getIds(aqids,paper.getAppQueNumber()).equals("error")){
            List<ApplicationQuestion> list2=applicationQuestionDAO.findApplicationQuestion4Paper(PaperUtils.getQuestionIds(getIds(aqids,paper.getAppQueNumber())));
            for(ApplicationQuestion q:list2){
                score+=Double.parseDouble(q.getScore());
            }
        }
        //获取随机填空题id的集合
        List<Integer> cpids=new ArrayList<>();
        for(Question question:completionDAO.getAllCompletionsByChapter(paper.getCompQueChapter())){
            cpids.add(question.getId());
        }
        map.put("cpids",getIds(cpids,paper.getCompQueNumber()));
        //填空题总分
        if(!getIds(cpids,paper.getCompQueNumber()).equals("error")){
            List<Question> list3=completionDAO.findCompletion4Paper(PaperUtils.getQuestionIds(getIds(cpids,paper.getCompQueNumber())));
            for(Question q:list3){
                score+=Double.parseDouble(q.getScore());
            }
        }
        //获取随机判断题id的集合
        List<Integer> jqids=new ArrayList<>();
        for(Question question:judgementquestionDAO.getAllJudgementquestionsByChapter(paper.getJudgeQueChapter())){
            jqids.add(question.getId());
        }
        map.put("jqids",getIds(jqids,paper.getJudgeQueNumber()));
        //判断题总分
        if(!getIds(jqids,paper.getJudgeQueNumber()).equals("error")){
            List<Question> list4=judgementquestionDAO.findJudgementQuestion4Paper(PaperUtils.getQuestionIds(getIds(jqids,paper.getJudgeQueNumber())));
            for(Question q:list4){
                score+=Double.parseDouble(q.getScore());
            }
        }
        //获取随机简答题id的集合
        List<Integer> dpids=new ArrayList<>();
        for(Question question:designproblemDAO.getAllDesignproblemsByChapter(paper.getDesignQueChapter())){
            dpids.add(question.getId());
        }
        map.put("dpids",getIds(dpids,paper.getDesignQueNumber()));
        //简答题总分
        if(!getIds(dpids,paper.getDesignQueNumber()).equals("error")){
            List<Question> list5=designproblemDAO.findDesignProblem4Paper(PaperUtils.getQuestionIds(getIds(dpids,paper.getDesignQueNumber())));
            for(Question q:list5){
                score+=Double.parseDouble(q.getScore());
            }
        }
        //添加考题
        Paper paper1=new Paper();
        String jqidfinal=(String)map.get("jqids");
        String aqidfinal=(String)map.get("aqids");
        String cqidfinal=(String)map.get("cqids");
        String dpidfinal=(String)map.get("dpids");
        String cpidfinal=(String)map.get("cpids");
        if(cqidfinal.equals("error")){
            message.append("选择题数量不足");
        }
        else if(cpidfinal.equals("error")){
            message.append(" 填空题数量不足");
        }
        else if(jqidfinal.equals("error")){
            message.append(" 判断题数量不足");
        }
        else if(dpidfinal.equals("error")){
            message.append(" 简答题数量不足");
        }
        else if(aqidfinal.equals("error")){
            message.append(" 应用题数量不足");
        }
        else{
            paper1.setJq(jqidfinal);
            paper1.setAq(aqidfinal);
            paper1.setCq(cqidfinal);
            paper1.setDp(dpidfinal);
            paper1.setCp(cpidfinal);
            paper1.setName(paper.getName());
            paper1.setScore(score+"");
            if(!paper1.getScore().contains("100")){
                message=new StringBuilder();
                message.append("总分为"+score+"分，不符合100分要求，请重新组卷");
            }
            else{
                message=new StringBuilder();
                message.append(paperDAO.addPaper(paper1)>0? ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText());
            }
        }

        return message.toString();
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
        StringBuilder message =new StringBuilder();
        if(manualPaper.getCq()==null){
            message.append("请选择至少一道选择题");
        }
        if(manualPaper.getJq()==null){
            message.append("  请选择至少一道判断题");

        }
        if(manualPaper.getCp()==null){
            message.append("  请选择至少一道填空题");
        }
        if(manualPaper.getDp()==null){
            message.append("  请选择至少一道简答题");
        }
        if(manualPaper.getAq()==null){
            message.append("  请选择至少一道应用题");
        }
        else{
            int _score=2*manualPaper.getCq().length+1*manualPaper.getJq().length+5*manualPaper.getCp().length+10*manualPaper.getDp().length+20*manualPaper.getAq().length;
            score=_score+".0";
            if(!score.contains("100")){
                message =new StringBuilder();
                message.append("分数为"+score+"不符合满分100分要求要求");
            }
            else{
                Paper paper=new Paper(manualPaper.getName()
                        ,PaperUtils.array2String(manualPaper.getCq())
                        ,PaperUtils.array2String(manualPaper.getCp())
                        ,PaperUtils.array2String(manualPaper.getDp())
                        ,PaperUtils.array2String(manualPaper.getJq())
                        ,PaperUtils.array2String(manualPaper.getAq()),score);
                message =new StringBuilder();
                message.append(paperDAO.addPaper(paper)>0? ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText());
            }
        }
        return message.toString();
    }

    @Override
    public int getCount(String name) {
        return paperDAO.getCount(name);
    }

    @Override
    public String deletePaperById(Integer id) {
        return paperDAO.deletePaperById(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }
}

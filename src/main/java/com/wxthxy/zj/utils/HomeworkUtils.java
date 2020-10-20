package com.wxthxy.zj.utils;

import com.wxthxy.zj.dao.PaperDAO;
import com.wxthxy.zj.dao.StudentDAO;
import com.wxthxy.zj.entity.Answer;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.entity.Student;

import java.util.*;

/**
 * 作业工具类
 */
public class HomeworkUtils {
    /**
     * 获取作业答案
     * @param str
     * @return
     */
    public static List<String> getHomeworkAnswers(String str){
        String[] answers=str.split(";");
        List<String> homeworkAnswers=new ArrayList<>();
        if(answers==null||str.equals("")||str==null||answers.equals("")){
            homeworkAnswers.add(str);
            return homeworkAnswers;
        }
        else{
            for(String answer:answers){
                homeworkAnswers.add(answer);
            }
            return homeworkAnswers;
        }

    }

    public static HomeWork subHomework(Map<String,String> map, Student student){
        HomeWork homeWork=new HomeWork();
        String cpanswer="";
        String cqanswer="";
        String jqanswer="";
        String dpanswer="";
        String aqanswer="";
        for (String questionType : map.keySet()) {
           //map.keySet()返回的是所有key的值
            String answer = map.get(questionType);//得到每个key多对用value的值
            if(questionType.startsWith("comp")){
                cpanswer+=answer+";";
            }
            else if(questionType.startsWith("choice")){
                cqanswer+=answer+";";
            }
            else if(questionType.startsWith("judge")){
                jqanswer+=answer+";";
            }
            else if(questionType.startsWith("design")){
                dpanswer+=answer+";";
            }
            else if(questionType.startsWith("application")){
                aqanswer+=answer+";";
            }
            else if(questionType.startsWith("id")){
                homeWork.setPaperid(Integer.parseInt(answer));
            }
            }
        cpanswer=cpanswer.substring(0,cpanswer.length()-1);
        homeWork.setCpanswer(cpanswer);
        cqanswer=cqanswer.substring(0,cqanswer.length()-1);
        homeWork.setCqanswer(cqanswer);
        jqanswer=jqanswer.substring(0,jqanswer.length()-1);
        homeWork.setJqanswer(jqanswer);
        dpanswer=dpanswer.substring(0,dpanswer.length()-1);
        homeWork.setDpanswer(dpanswer);
        aqanswer=aqanswer.substring(0,aqanswer.length()-1);
        homeWork.setApanswer(aqanswer);
        homeWork.setStuid(student.getStuid());
        Integer major=student.getMajorid();
        String _major="";
        if(major==1){
            _major="软件工程";
        }
        else if(major==2){
            _major="通讯";
        }
        else if(major==3){
            _major="计科";
        }
        else if(major==4){
            _major="物联网";
        }
        homeWork.setClassname(_major+student.getClassname());
        homeWork.setStuname(student.getName());
        return  homeWork;
    }

    public  static Map<String,List> getPaperIdsAndStudentIds(String papername, PaperDAO paperDAO, String studentname, StudentDAO studentDAO){
        Map<String,List> map=new HashMap<>();
        Integer paperid=99999999;
        List<Integer> paperids=new ArrayList<>();
        if(papername!="" &&papername!=null){
            papername=papername.trim();
            List<Paper> paperList=paperDAO.findPaperByName(papername);
            if(paperList!=null){
                paperids.add(paperid);
                for(Paper paper:paperList){
                    paperid=paper.getId();
                    paperids.add(paperid);
                }
            }
        }
        else{
            paperids=null;
        }
        String studentid="99999999";
        List<String> studentids=new ArrayList<>();
        if(studentname!="" &&studentname!=null){
            studentname=studentname.trim();
            List<Student> studentList=studentDAO.findStudentByName(studentname);
            if(studentList!=null){
                studentids.add(studentid);
                for(Student student:studentList){
                    studentid=student.getStuid();
                    studentids.add(studentid);
                }
            }
        }
        else{
            studentids=null;
        }
        map.put("paperids",paperids);
        map.put("studentids",studentids);
        return map;
    }

    /**
     * 主观题得分
     */
    public static String scoreBySelf(Map map, Answer answer){
        //填空题答案
        double score=0.0;
        List<String> cpanswers=(List<String>)map.get("cpanswers");
        for(int i=0;i<cpanswers.size();i++){
            String an=answer.getCpanwsers().get(i);
            an=an.substring(2,an.length());
            if(cpanswers.get(i).equals(an)){
                score=score+5;
            }
        }
        //选择题答案
        List<String> cqanswers=(List<String>)map.get("cqanswers");
        for(int i=0;i<cqanswers.size();i++){
            String an=answer.getCqanwsers().get(i);
            an=an.substring(2,an.length());
            if(cqanswers.get(i).equals(an)){
                score=score+2;
            }
        }
        //判断题答案
        List<String> jqanswers=(List<String>)map.get("jqanswers");
        for(int i=0;i<jqanswers.size();i++){
            String an=answer.getJqanwsers().get(i);
            an=an.substring(2,an.length());
            if(jqanswers.get(i).equals(an)){
                score=score+1;
            }
        }
        return score+"";
    }

    public static Map scoreMain(Map map, Answer answer){
        Map map2=new HashMap();
        //填空题答案
        Double score=0.0;
        List<String> cpanswers=(List<String>)map.get("cpanswers");
        for(int i=0;i<cpanswers.size();i++){
            String an=answer.getCpanwsers().get(i);
            an=an.substring(2,an.length());
            if(cpanswers.get(i).equals(an)){
                score=score+5;
            }
        }
        map2.put("cpscore",score);
        score=0.0;
        //选择题答案
        List<String> cqanswers=(List<String>)map.get("cqanswers");
        for(int i=0;i<cqanswers.size();i++){
            String an=answer.getCqanwsers().get(i);
            an=an.substring(2,an.length());
            if(cqanswers.get(i).equals(an)){
                score=score+2;
            }
        }
        map2.put("cqscore",score);
        score=0.0;
        //判断题答案
        List<String> jqanswers=(List<String>)map.get("jqanswers");
        for(int i=0;i<jqanswers.size();i++){
            String an=answer.getJqanwsers().get(i);
            an=an.substring(2,an.length());
            if(jqanswers.get(i).equals(an)){
                score=score+1;
            }
        }
        map2.put("jqscore",score);
        return map2;
    }

    public static Map check(List<String> list,int length){
        Set<String> keyWord=new HashSet<>();
        Map<String,List<String>> map=new HashMap<>();

        for(int k=0;k<list.size();k++){
            for(int i=0;i<list.get(k).length();i++){
                for(int j=i+1;j<list.get(k).length();j++){
                    String regex=list.get(k).substring(i,j);
                    keyWord.add(regex);
                }
            }
        }

        keyWord.forEach(key->{
            List<String> newlist=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                if(list.get(i).contains(key)){
                    newlist.add(list.get(i));
                }
            }
            map.put(key,newlist);
        });
        Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, List<String>> entry=it.next();
            String key=entry.getKey();
            if(key.length()<length||map.get(key).size()<=1){
                it.remove();        //OK
            }
        }
        for(String key:map.keySet()){
            System.out.println(key+"===="+map.get(key));
        }
        return map;
    }
}

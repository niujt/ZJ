package com.wxthxy.zj.utils;

import com.wxthxy.zj.dao.PaperDAO;
import com.wxthxy.zj.dao.StudentDAO;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        homeWork.setClassname(student.getClassname());
        homeWork.setStudentname(student.getName());
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
}

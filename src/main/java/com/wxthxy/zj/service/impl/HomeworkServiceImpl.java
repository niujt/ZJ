package com.wxthxy.zj.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.HomeworkDAO;
import com.wxthxy.zj.dao.KeyDAO;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Key;
import com.wxthxy.zj.entity.TeacherCorrection;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.utils.HomeworkUtils;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDAO dao;
    @Autowired
    KeyDAO keyDAO;
    @Override
    public PageBean<HomeWork> getAll(Integer currentPage) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, PageBean.pageSize);
        List<HomeWork> allItems = dao.findAll();
        int countNums = dao.getCount();    //总记录数
        PageBean<HomeWork> pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    @Override
    public Map getHomeworkAnswer(Integer id) {
        Map map=new HashMap();
        //获取作业详情
        HomeWork homeWork=dao.findHomeworkById(id);
        //获取填空答案
        List<String> cpanswers= HomeworkUtils.getHomeworkAnswers(homeWork.getCpanswer());
        //获取选择题答案
        List<String> cqanswers= HomeworkUtils.getHomeworkAnswers(homeWork.getCqanswer());
        //获取判断题答案
        List<String> jqanswers= HomeworkUtils.getHomeworkAnswers(homeWork.getJqanswer());
        //获取简答题题答案
        List<String> dpanswers= HomeworkUtils.getHomeworkAnswers(homeWork.getDpanswer());
        //获取应用题答案
        List<String> aqanswers= HomeworkUtils.getHomeworkAnswers(homeWork.getApanswer());
        map.put("cpanswers",cpanswers);
        map.put("cqanswers",cqanswers);
        map.put("jqanswers",jqanswers);
        map.put("dpanswers",dpanswers);
        map.put("aqanswers",aqanswers);
        return map;
    }

    @Override
    public String score(TeacherCorrection teacherCorrection) {
        HomeWork homeWork=new HomeWork();
        homeWork.setId(teacherCorrection.getId());
        double totalScore=teacherCorrection.getAqScore()
                +teacherCorrection.getCpScore()+teacherCorrection.getCqScore()+teacherCorrection.getDpScore()+teacherCorrection.getJqScore();
        homeWork.setScore(totalScore+"");
        return dao.updateScoreById(homeWork)>0? ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public String eva(HomeWork homeWork) {
        return dao.updateOtherById(homeWork)>0? ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public HomeWork findHomeWork(Integer id) {
        return dao.findHomeworkById(id);
    }

    @Override
    public String subHomework(HomeWork homeWork) {
        homeWork.setDotype(0);
        return dao.addHomework(homeWork)>0?ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    @Override
    public PageBean<HomeWork> getAllByStuid(String stuid,Integer currentPage) {
        PageHelper.startPage(currentPage, PageBean.pageSize);
        List<HomeWork> allItems =dao.findAllByStuid(stuid);
        int countNums =dao.getCountByStuId(stuid);    //总记录数
        PageBean<HomeWork> pageData = new PageBean<>(currentPage, PageBean.pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    @Override
    public List<Key> check() {
        List<Key> list=keyDAO.findAll();

        return list;
    }
    @Override
    public List<HomeWork> getAll() {
        return dao.findAll();
    }

    @Override
    public String subHomeworkBySelf(HomeWork homeWork) {
        homeWork.setDotype(1);
        homeWork.setState(1);

       // homeWork.setScore();
        return dao.addHomework(homeWork)>0?ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
    }

    @Override
    public String scoreBySelf(HomeWork homeWork) {
        return dao.updateScoreById(homeWork)>0? ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }
}

package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.HomeworkDAO;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.utils.HomeworkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDAO dao;
    @Override
    public List<HomeWork> getAll() {
        return dao.findAll();
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
}
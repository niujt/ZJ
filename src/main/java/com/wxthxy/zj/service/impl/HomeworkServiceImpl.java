package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.HomeworkDAO;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDAO dao;
    @Override
    public List<HomeWork> getAll() {
        return dao.findAll();
    }
}

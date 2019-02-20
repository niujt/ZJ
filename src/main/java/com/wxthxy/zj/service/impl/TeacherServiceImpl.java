package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.TeacherDAO;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDAO dao;
    @Override
    public List<Teacher> getAllTeacher() {
        return dao.findAllTeacher();
    }
}

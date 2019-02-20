package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.StudentDAO;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDAO dao;
    @Override
    public List<Student> findAllStudents() {
        return dao.findAllStudents();
    }
}

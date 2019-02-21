package com.wxthxy.zj.service;

import com.wxthxy.zj.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    String addStudent(Student student);
    String delStu(Integer id);
    String updateStu(Student student);
}

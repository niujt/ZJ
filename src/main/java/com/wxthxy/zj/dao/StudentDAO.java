package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAllStudents();
}

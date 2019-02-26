package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.common.ServiceMessage;
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

    @Override
    public String addTea(Teacher teacher) {
        String message;
        if(dao.findTeacherByTeaid(teacher.getTeaid())!=null){
            message=updateTea(teacher);
        }
        else{
            message=dao.addTeacher(teacher)>0?ServiceMessage.Common_message_01.getText():ServiceMessage.Common_message_06.getText();
        }
        return message;
    }

    @Override
    public String delTea(Integer id) {
        return dao.deleteTeacherById(id)>0?ServiceMessage.Common_message_02.getText():ServiceMessage.Common_message_03.getText();
    }

    @Override
    public String updateTea(Teacher teacher) {
        return dao.updateTeacherByTeaId(teacher)>0?ServiceMessage.Common_message_04.getText():ServiceMessage.Common_message_05.getText();
    }

    @Override
    public Teacher findTeadcher(Integer id) {
        return dao.findTeacherByLoginId(id);
    }
}

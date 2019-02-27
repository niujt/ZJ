package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.LoginDAO;
import com.wxthxy.zj.dao.StudentDAO;
import com.wxthxy.zj.dao.TeacherDAO;
import com.wxthxy.zj.entity.Login;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    public LoginDAO loginDAO;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    StudentDAO studentDAO;
    @Override
    public String dologin(String username,String password,String identity) {

        if(loginDAO.findLoginByUsername(username)==null){
            return ServiceMessage.login_message_01.getText();
        }
        else if(!password.equals( loginDAO.findLoginByUsername(username).getPassword())){
            return ServiceMessage.login_message_02.getText();
        }
        else{
            if(identity.equals("student")){
                if(studentDAO.findStudentByLoginId(loginDAO.findLoginByUsername(username).getId())==null){
                    return "该账户不是学生";
                }
            }
            else if(identity.equals("teacher")){
                 if(teacherDAO.findTeacherByLoginId(loginDAO.findLoginByUsername(username).getId())==null){
                    return "该账户不是老师";
                }
            }
            else if(identity.equals("admin")&&!username.equals("admin")){
                return "该账户不是admin管理员";
            }
            return ServiceMessage.login_message_03.getText();
        }
    }

    @Override
    public String doRegist(String username, String password, String userid, String identity) {
        String message="";
            if(loginDAO.findLoginByUsername(username)!=null){
                message=ServiceMessage.regist_message_01.getText();
            }
            else{
                Login login=new Login();
                login.setUsername(username);
                login.setPassword(password);
                if(teacherDAO.findTeacherByTeaid(userid)==null||studentDAO.findStudentByStuid(userid)==null){
                    message=ServiceMessage.regist_message_03.getText();
                }
                else{
                    loginDAO.addLogin(login);
                    Integer id=loginDAO.findLoginByUsername(username).getId();
                    message=ServiceMessage.regist_message_02.getText();
                    if(identity.equals("teacher")){
                        Teacher teacher=new Teacher();
                        teacher.setLoginid(id);
                        teacher.setTeaid(userid);
                        teacherDAO.updateLoginId(teacher);
                    }
                    else if(identity.equals("student")){
                        Student student=new Student();
                        student.setLoginid(id);
                        student.setStuid(userid);
                        studentDAO.updateLoginId(student);
                    }
                }
            }
        return message;
    }

    @Override
    public Login findLoginByUserName(String username) {
        return loginDAO.findLoginByUsername(username);
    }
}

package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.dao.LoginDAO;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    public LoginDAO dao;
    @Override
    public String dologin(String username,String password) {

        if(dao.findLoginByUsername(username)==null){
            return ServiceMessage.login_message_01.getText();
        }
        else if(!password.equals( dao.findLoginByUsername(username).getPassword())){
            return ServiceMessage.login_message_02.getText();
        }
        else{
            return ServiceMessage.login_message_03.getText();
        }
    }
}

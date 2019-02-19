package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.LoginDAO;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    public LoginDAO dao;
    @Override
    public String dologin(String username) {
        return  dao.findLoginByUsername(username).toString();
    }
}

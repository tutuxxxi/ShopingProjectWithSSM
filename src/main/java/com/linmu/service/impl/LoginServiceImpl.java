package com.linmu.service.impl;

import com.linmu.dao.LoginDao;
import com.linmu.entity.Login;
import com.linmu.entity.User;
import com.linmu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

    LoginDao loginDao;

    @Autowired
    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }





    public Login login(User user) {

        Login login = new Login(user.getUuid(), new Date());
        loginDao.addLoginData(login);

        return login;
    }

    public void logout(Login login) {
        login.setLogoutDate(new Date());
        loginDao.addLogoutData(login);
    }
}

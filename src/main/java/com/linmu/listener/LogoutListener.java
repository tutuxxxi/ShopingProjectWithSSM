package com.linmu.listener;

import com.linmu.entity.Login;
import com.linmu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class LogoutListener implements HttpSessionListener {

    private LoginService loginService;


    public void sessionCreated(HttpSessionEvent se) {

    }

    public void sessionDestroyed(HttpSessionEvent se) {

        //不能使用自动注入，原因暂时未知， 使用手动注入
        loginService = new ClassPathXmlApplicationContext("application-context.xml").
                getBean("loginService", LoginService.class);

        Login login = (Login) se.getSession().getAttribute("login");

        if(login != null){
            loginService.logout(login);
        }
    }
}

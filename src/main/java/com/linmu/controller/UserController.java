package com.linmu.controller;


import com.linmu.entity.Login;
import com.linmu.entity.User;
import com.linmu.service.LoginService;
import com.linmu.service.UserService;
import com.linmu.util.DataCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {


    UserService userService;
    LoginService loginService;

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }



    @PostMapping(value = "/register")
    public String userRegister(String username, String email, String password, String validCode, HttpServletRequest request){

        if(DataCheckUtil.checkValidCode(validCode, request)){
            request.setAttribute("errorInfo", "验证码错误");
            return "error";
        }

        try {
            if(userService.register(username, email, password)){
                return "注册成功";
            }else{
                request.setAttribute("errorInfo", "注册失败：数据库写入数据失败，请联系管理员");
                return "error";
            }
        } catch (Exception e) {
            request.setAttribute("errorInfo", "注册失败：" + e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public String userLogin(String email, String password, String validCode, HttpServletRequest request){

        if(!DataCheckUtil.checkValidCode(validCode, request))
            return "验证码错误";

        try {

            HttpSession session = request.getSession();

            //将这一次的登陆信息暂存在session中
            Login login = userService.login(email, password);
            session.setAttribute("login", login);
            User user = userService.getUserInfo(login.getUserId(), null);
            session.setAttribute("user", user);

            session.setMaxInactiveInterval(10 * 60);
            return "登陆成功";

        } catch (Exception e) {
            return "登陆失败：" + e.getMessage();
        }
    }


    @ResponseBody
    @GetMapping(value = "/logout")
    public String userLogout(HttpServletRequest request){
        Login login = (Login) request.getSession().getAttribute("login");
        if(login == null)
            return "您还没有登陆，不能点击注销";

        loginService.logout(login);
        request.getSession().invalidate();
        return "注销成功";
    }

}

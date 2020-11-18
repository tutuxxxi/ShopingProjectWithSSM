package com.linmu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.linmu.dao.UserDao;
import com.linmu.entity.Login;
import com.linmu.entity.User;
import com.linmu.service.LoginService;
import com.linmu.service.UserService;
import com.linmu.util.DataCheckUtil;
import com.linmu.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    UserDao userDao;
    LoginService loginService;


    @Autowired
    public UserServiceImpl(UserDao userDao, LoginService loginService) {
        this.userDao = userDao;
        this.loginService = loginService;
    }

    public boolean register(String username, String email, String password) throws Exception {
        //检验email和username
        if(DataCheckUtil.checkEmail(email) && DataCheckUtil.checkUsername(username)){
            password = Md5.Encryption(password);
            return userDao.addUser(new User(username, email, password)) == 1;
        }
        return false;
    }

    public Login login(String email, String password) throws Exception {
        if(DataCheckUtil.checkEmail(email)){

            User user = userDao.getUserByEmail(email);

            //没有该用户
            if(user == null)
                throw new Exception("检查您的邮箱地址输入是否有误 —— 该邮箱未注册");

            if(Md5.Verification(password, user.getPassword())){
                //密码正确，需要开始添加登陆信息
                return loginService.login(user);
            }else
                //密码错误
                throw new Exception("检查您的密码输入是否有误 —— 密码与邮箱不匹配");
        }

        return null;
    }


    public User getUserInfo(String id, String email){
        if(StringUtils.isEmpty(id) && StringUtils.isEmpty(email)){
            return null;
        }

        if(!StringUtils.isEmpty(id))
            return userDao.getUserById(id);
        else
            return userDao.getUserByEmail(email);
    }
}

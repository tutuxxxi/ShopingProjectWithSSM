package com.linmu.service;


import com.linmu.entity.Login;
import com.linmu.entity.User;

public interface UserService {

    boolean register(String username, String email, String password) throws Exception;


    Login login(String email, String password) throws Exception;

    User getUserInfo(String id, String email);
}

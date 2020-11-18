package com.linmu.service;

import com.linmu.entity.Login;
import com.linmu.entity.User;

public interface LoginService {

    Login login(User user);

    void logout(Login login);

}

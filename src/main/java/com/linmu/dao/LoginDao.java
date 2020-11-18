package com.linmu.dao;

import com.linmu.entity.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

    void addLoginData(Login login);

    void addLogoutData(Login login);

}

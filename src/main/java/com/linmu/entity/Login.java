package com.linmu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Login {

    private String id;

    private String userId;

    private Date loginDate;

    private Date logoutDate;

    public Login() {
    }

    public Login(String userId, Date loginDate) {
        this.userId = userId;
        this.loginDate = loginDate;
    }

    public Login(String id, String userId, Date logoutDate) {
        this.id = id;
        this.userId = userId;
        this.logoutDate = logoutDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 hh:mm:ss")
    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }
}

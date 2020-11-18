package com.linmu.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCheckUtil {

    public static boolean checkEmail(String email) throws Exception {
        if(email.length() <= 30 ){
            if(!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"))
                throw new Exception("邮箱格式错误");
        }else
            throw new Exception("邮箱地址过长");

        return true;
    }


    public static boolean checkUsername(String username) throws Exception {
        if(username.length() > 24)
            throw new Exception("用户名过长");

        return true;
    }

    public static boolean checkValidCode(String validCode, HttpServletRequest request){
        return validCode != null && validCode.equals(request.getSession().getAttribute("validCode"));
    }

}

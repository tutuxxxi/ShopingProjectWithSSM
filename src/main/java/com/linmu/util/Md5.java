package com.linmu.util;

import org.springframework.util.DigestUtils;

public class Md5{

    private static final String SLAT = "nice to meet you";


    public static String Encryption(String str){
        int index = str.length() / 2;
        StringBuffer stringBuffer = new StringBuffer();

        //拼接加密盐
        stringBuffer.append(str.substring(0, index));
        stringBuffer.append(SLAT);
        stringBuffer.append(str.substring(index, str.length()));

        return DigestUtils.md5DigestAsHex(stringBuffer.toString().getBytes());
    }


    public static boolean Verification(String str1, String str2){
        return Encryption(str1).equals(str2);
    }

}

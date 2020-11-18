package com.linmu.controller;


import com.linmu.util.ValidCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/util")
public class UtilController {


    @GetMapping("validCode")
    public void getValidCode(HttpServletRequest request, HttpServletResponse response) {

        int width = 200, height = 80;

        //随机生成字符串
        String baseStr = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        StringBuffer randomStr = new StringBuffer("");
        Random random = new Random();
        for(int i = 0; i<4; i++){
            int index = random.nextInt(baseStr.length());
            randomStr.append(baseStr.charAt(index));
        }

        //验证码保存至会话
        request.getSession().setAttribute("validCode", randomStr.toString().toLowerCase());

        //根据随机串生成图片
        BufferedImage nice = ValidCodeUtil.getValidCodePicture( width, height, randomStr.toString());

        //响应图片
        response.setContentType("image/png");
        try{
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(nice, "png", outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

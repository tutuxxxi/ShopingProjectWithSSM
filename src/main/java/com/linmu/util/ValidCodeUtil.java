package com.linmu.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidCodeUtil {

    //根据传入参数获取验证码图片
    public static BufferedImage getValidCodePicture(int width, int height, String validCode){
        // 新建图片对象
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画布
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        //设置背景色
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);

        //设置字体
        graphics.setFont(new Font("黑体", Font.BOLD, 40));


        //定义每一个字符打印时的坐标
        int x;
        int y = (height-40)/2 + 30;
        //定义每一次x的递增数值
        int x_addSize = (width-20)/validCode.length();
        x = (x_addSize-40)/2 + 20;

        Random random = new Random();
        for(int i = 0; i<validCode.length(); i++){
            //对每一个字符进行随机的旋转偏移颜色

            //随机色不余256 防止颜色变为白色
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255)%255,random.nextInt(255)));

            //随机偏移
            int degree = random.nextInt()%15;

            //两次旋转为了让字体偏移而之后的画布不偏移
            graphics.rotate(degree * Math.PI / 180, x, height);

            graphics.drawString(validCode.charAt(i) + "", x, y);

            graphics.rotate(-degree * Math.PI / 180, x, height);

            x += x_addSize;

        }

        //干扰信息添加
        for(int i = 0; i<10; i++){
            //随机画线
            //随机色
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255)%255,random.nextInt(255)));
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
        for(int i = 0; i<80; i++){
            //随机加点
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255)%255,random.nextInt(255)));
            graphics.drawRect(random.nextInt(width), random.nextInt(height), 1,1);
        }

        return bufferedImage;
    }

}

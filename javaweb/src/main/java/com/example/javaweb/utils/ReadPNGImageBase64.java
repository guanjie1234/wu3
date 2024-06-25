package com.example.javaweb.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadPNGImageBase64 {

    public static String ReadPngImageBase64(String url) {
        if (url!=null){
        try {
            // 图片文件的路径
            System.out.println(url);
            String imagePath = url;

            File input = new File(imagePath);

            // 读取图片
            BufferedImage image = ImageIO.read(input);

            // 验证图片是否成功读取
            if (image == null) {
                System.out.println("图片读取失败");
                return null;
            } else {
                System.out.println("图片读取成功，宽度：" + image.getWidth() + "，高度：" + image.getHeight());
                String imageBase64 = ImageToBase64.imageToBase64(image);
                var imagebase64 = "data:image/png;base64," + imageBase64;
                return imagebase64;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
        return null;
    }}

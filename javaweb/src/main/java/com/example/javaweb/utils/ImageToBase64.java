package com.example.javaweb.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64 {
    public static String imageToBase64(BufferedImage image) {
        try {
            // 将BufferedImage转换为ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // 将字节数组转换为Base64编码的字符串
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

            return encodedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

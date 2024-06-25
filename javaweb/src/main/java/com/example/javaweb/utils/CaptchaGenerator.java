package com.example.javaweb.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import javax.imageio.ImageIO;

public class CaptchaGenerator {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_COUNT = 4;
    private static final Font FONT = new Font("Fixedsys", Font.BOLD, 24);
    private static final Random RANDOM = new Random();

    public static CaptchaResponse generateCaptcha() throws IOException {
        // 1. 创建一个BufferedImage对象
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 2. 在BufferedImage上绘制文本
        StringBuilder code = new StringBuilder();
        g.setFont(FONT);
        g.setColor(Color.BLACK);
        for (int i = 0; i < CODE_COUNT; i++) {
            int index = RANDOM.nextInt(26) + 97; // 生成小写字母的ASCII码
            char c = (char) index;
            code.append(c);
            g.drawString(String.valueOf(c), 15 * i + 6, 28);
        }

        // 3. 添加噪点或干扰线（可选）
        // ...（此处省略添加噪点和干扰线的代码）

        // 4. 将BufferedImage编码为Base64字符串
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageData = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        var imageBase = "data:image/png;base64," + base64Image;

        // 5. 清理资源
        g.dispose();

        // 6. 返回CaptchaResponse对象，包含随机字符串和Base64图片
        return new CaptchaResponse(code.toString(), imageBase);
    }

    // 定义一个简单的CaptchaResponse类来保存结果
    public static class CaptchaResponse {
        private final String captchaText;
        private final String captchaImageBase64;

        public CaptchaResponse(String captchaText, String captchaImageBase64) {
            this.captchaText = captchaText;
            this.captchaImageBase64 = captchaImageBase64;
        }

        public String getCaptchaText() {
            return captchaText;
        }

        public String getCaptchaImageBase64() {
            return captchaImageBase64;
        }
    }


}

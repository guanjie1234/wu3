package com.example.javaweb.utils;

import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileUtils {

    /**
     * 将MultipartFile保存为PNG格式的文件
     *
     * @param multipartFile 要保存的MultipartFile
     * @param outputPath    保存PNG文件的路径
     * @return 如果保存成功返回true，否则返回false
     */
    public static boolean saveMultipartFileAsPng(MultipartFile multipartFile, String outputPath) {
        try {
            // 检查MultipartFile是否为空
            if (multipartFile == null || multipartFile.isEmpty()) {
                return false;
            }

            // 尝试读取MultipartFile为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                // 如果无法读取为BufferedImage（即可能不是图像文件），则返回false
                return false;
            }

            // 保存为PNG格式
            File outputFile = new File(outputPath);
            ImageIO.write(bufferedImage, "png", outputFile);
            return true;
        } catch (IOException e) {
            // 处理IO异常
            e.printStackTrace();
            return false;
        }
    }
    public static String getFileExtension(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.getOriginalFilename() == null) {
            return null;
        }

        String fileName = multipartFile.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return null; // 没有找到点，即没有文件后缀
        }

        return fileName.substring(dotIndex + 1); // 返回点之后的字符串，即文件后缀
    }


}
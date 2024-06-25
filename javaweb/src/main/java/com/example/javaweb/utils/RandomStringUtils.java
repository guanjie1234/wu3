package com.example.javaweb.utils;

import java.util.Random;

public class RandomStringUtils {

    private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CHAR_LIST_LENGTH = CHAR_LIST.length();
    private static final Random RANDOM = new Random();

    /**
     * 生成一个随机8位的字符串
     *
     * @return 随机字符串
     */
    public static String generateRandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHAR_LIST_LENGTH);
            sb.append(CHAR_LIST.charAt(index));
        }
        return sb.toString();
    }


}
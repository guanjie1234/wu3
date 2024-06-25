package com.example.javaweb.utils;

import org.springframework.util.DigestUtils;

public class Md5 {
    public static  String Md5(String a) {
        // 基于spring框架中的DigestUtils工具类进行密码加密
        return DigestUtils.md5DigestAsHex((a).getBytes());
    }
}

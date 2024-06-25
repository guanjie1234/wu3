package com.example.javaweb.controller;

import com.example.javaweb.pojo.CaptchaImage;
import com.example.javaweb.pojo.LoginInformation;
import com.example.javaweb.pojo.Message;
import com.example.javaweb.service.UserService;
import com.example.javaweb.utils.CaptchaGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/basedata")
    public ResponseEntity<Message> baseDataUser(@RequestBody String userid) throws JsonProcessingException {
         ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(userid);
        // 从JsonNode中提取"userid"字段的值，并转换为整数
        int userId = rootNode.get("userid").asInt();
        System.out.println(userId);
        Message message = userService.baseDataUser(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//登录
    @PostMapping("/login")
    public ResponseEntity<Message> LoginUser(HttpServletResponse response, HttpServletRequest request, @RequestBody LoginInformation loginInformation) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        Message message = userService.loginUser(response,loginInformation,(String) session.getAttribute("captcha"));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //注册
    @PostMapping("/register")
    public ResponseEntity<Message> registerUser(HttpServletRequest request, @RequestBody LoginInformation loginInformation) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        Message message = userService.registerUser(loginInformation,(String) session.getAttribute("captcha"));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //获取验证码
    @PostMapping("/captcha")
    public ResponseEntity<CaptchaImage> getCaptcha(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        CaptchaGenerator.CaptchaResponse captcha = userService.getCapacha();
            session.setAttribute("captcha", captcha.getCaptchaText());
        System.out.println(session.getAttribute("captcha"));
        System.out.println(captcha.getCaptchaImageBase64());
        return new ResponseEntity<>(new CaptchaImage(captcha.getCaptchaImageBase64()), HttpStatus.OK);
    }


}
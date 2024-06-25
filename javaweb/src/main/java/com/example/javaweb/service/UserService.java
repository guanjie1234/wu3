package com.example.javaweb.service;

import com.example.javaweb.dao.UserRepository;
import com.example.javaweb.entity.User;
import com.example.javaweb.pojo.LoginInformation;
import com.example.javaweb.pojo.Message;
import com.example.javaweb.utils.CaptchaGenerator;
import com.example.javaweb.utils.Md5;
import com.example.javaweb.utils.ReadPNGImageBase64;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

import static com.example.javaweb.utils.RandomStringUtils.generateRandomString;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Value("${user.avatar.path}")
    public String url;
    @Transactional
    public User addUser(User user) {

        return userRepository.save(user);
    }

    public Message baseDataUser(int userid) {

        User user = userRepository.findUserById(userid);
        user.setPassword(null);
        user.setIsLogout(null);
        user.setStatus(null);
        user.setLogouttime(null);
        String vurl = url + user.getAvatar();
        String imageBase64 = ReadPNGImageBase64.ReadPngImageBase64(vurl);
        user.setAvatar(imageBase64);
        Message message = new Message();
        message.setStatus("0");
        message.setMessage("用户信息获取成功");
        message.setUserdata(user);
        return message;
    }

    public Message loginUser(HttpServletResponse response, LoginInformation loginInformation, String captcha) {
        if (captcha.equals(loginInformation.getCaptcha())) {
            User user = userRepository.findUser(loginInformation.getAccount());
            if (user != null && user.getPassword().equals(Md5.Md5(loginInformation.getPassword()))) {
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("登录成功");
                message.setUserdata(user);
                Cookie useridcookie = new Cookie("userid", String.valueOf(user.getId()));
                useridcookie.setPath("/");
                useridcookie.setMaxAge(3600); // 设置cookie有效期为1小时
                response.addCookie(useridcookie);
                return message;
            }
            Message message = new Message();
            message.setStatus("1");
            message.setMessage("该账号不存在或密码错误");
            return message;

        }
        Message message = new Message();
        message.setStatus("1");
        message.setMessage("验证码错误");
        return message;
    }
//注册
    public Message registerUser(LoginInformation loginInformation, String captcha) {
        if (captcha.equals(loginInformation.getCaptcha())) {
            User user = userRepository.findUser(loginInformation.getAccount());
            if (user == null) {
                userRepository.resisterUser(loginInformation.getAccount(),Md5.Md5(loginInformation.getPassword()), generateRandomString(8), java.sql.Date.valueOf(LocalDate.now()), "defaultPicture/defaultImage1.png");
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("注册成功");
                return message;
            }
            Message message = new Message();
            message.setStatus("1");
            message.setMessage("该账号已存在");
            return message;

        }
        Message message = new Message();
        message.setStatus("1");
        message.setMessage("验证码错误");
        return message;
    }

    //获取验证码
    public CaptchaGenerator.CaptchaResponse getCapacha() throws IOException {
        CaptchaGenerator.CaptchaResponse captcha = CaptchaGenerator.generateCaptcha();
        return captcha;
    }

//    public LoginMessage getUserById(Long id) {
//        LoginMessage message = new LoginMessage();
//        message.setUserdata(userRepository.findById(Math.toIntExact(id)));
//        return message;
//    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }

    // 其他业务逻辑方法...
}
package com.example.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);

    }

}
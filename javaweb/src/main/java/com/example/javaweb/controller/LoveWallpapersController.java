package com.example.javaweb.controller;

import com.example.javaweb.pojo.Message;
import com.example.javaweb.pojo.UseridAndWallpapersid;
import com.example.javaweb.pojo.WallpapersPagination;
import com.example.javaweb.service.CollectWallpapersService;
import com.example.javaweb.service.LoveWallpapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/love")
public class LoveWallpapersController {
    private final LoveWallpapersService loveWallpapersService;

    @Autowired
    public LoveWallpapersController(LoveWallpapersService loveWallpapersService) {
        this.loveWallpapersService = loveWallpapersService;
    }

    //添加喜欢
    @PostMapping("/add")
    public ResponseEntity<Message> addCollect(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = loveWallpapersService.addLove(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //取消喜欢
    @PostMapping("/remove")
    public ResponseEntity<Message> deteleCollect(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = loveWallpapersService.deleteLove(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取喜欢壁纸
    @PostMapping("/get")
    public ResponseEntity<Message> getCollect(@RequestBody WallpapersPagination wallpapersPagination) {
        Message message = loveWallpapersService.findLove(wallpapersPagination);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取喜欢壁纸条数
    @PostMapping("/total")
    public ResponseEntity<Message> getCollecttotal(@RequestBody int userid) {
        System.out.println(userid);
        Message message = loveWallpapersService.getLovetotal(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}


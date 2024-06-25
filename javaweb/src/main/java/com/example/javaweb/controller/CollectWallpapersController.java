package com.example.javaweb.controller;

import com.example.javaweb.pojo.Message;
import com.example.javaweb.pojo.UseridAndWallpapersid;
import com.example.javaweb.pojo.WallpapersPagination;
import com.example.javaweb.service.CollectWallpapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectWallpapersController {
    private final CollectWallpapersService collectWallpapersService;

    @Autowired
    public CollectWallpapersController(CollectWallpapersService collectWallpapersService) {
        this.collectWallpapersService = collectWallpapersService;
    }

    //添加收藏
    @PostMapping("/add")
    public ResponseEntity<Message> addCollect(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = collectWallpapersService.addCollect(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //取消收藏
    @PostMapping("/remove")
    public ResponseEntity<Message> deteleCollect(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = collectWallpapersService.deleteCollect(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取收藏壁纸
    @PostMapping("/get")
    public ResponseEntity<Message> getCollect(@RequestBody WallpapersPagination wallpapersPagination) {
        Message message = collectWallpapersService.findCollect(wallpapersPagination);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取收藏壁纸条数
    @PostMapping("/total")
    public ResponseEntity<Message> getCollecttotal(@RequestBody int userid) {
        System.out.println(userid);
        Message message = collectWallpapersService.getCollecttotal(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}


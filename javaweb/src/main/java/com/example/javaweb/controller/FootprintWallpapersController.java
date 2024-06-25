package com.example.javaweb.controller;

import com.example.javaweb.pojo.Message;
import com.example.javaweb.pojo.UseridAndWallpapersid;
import com.example.javaweb.pojo.WallpapersPagination;
import com.example.javaweb.service.CollectWallpapersService;
import com.example.javaweb.service.FootprintWallpapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/footprint")
public class FootprintWallpapersController {
    private final FootprintWallpapersService tootprintWallpapersService;

    @Autowired
    public FootprintWallpapersController(FootprintWallpapersService tootprintWallpapersService) {
        this.tootprintWallpapersService = tootprintWallpapersService;
    }

    //添加历史
    @PostMapping("/add")
    public ResponseEntity<Message> addFootprint(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = tootprintWallpapersService.addFootprint(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //取消历史
    @PostMapping("/remove")
    public ResponseEntity<Message> deteleFootprint(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = tootprintWallpapersService.addFootprint(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取历史壁纸
    @PostMapping("/get")
    public ResponseEntity<Message> getFootprint(@RequestBody WallpapersPagination wallpapersPagination) {
        Message message = tootprintWallpapersService.findFootprint(wallpapersPagination);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //获取历史壁纸条数
    @PostMapping("/total")
    public ResponseEntity<Message> getFootprint(@RequestBody int userid) {
        System.out.println(userid);
        Message message = tootprintWallpapersService.getFootprinttotal(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}


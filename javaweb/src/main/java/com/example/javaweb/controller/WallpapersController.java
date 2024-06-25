package com.example.javaweb.controller;

import com.example.javaweb.entity.Wallpapers;
import com.example.javaweb.pojo.*;
import com.example.javaweb.service.WallpapersDetailService;
import com.example.javaweb.service.WallpapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Wallpapers")
public class WallpapersController {

    private final WallpapersService wallpapersService;
    private final WallpapersDetailService wallpapersDetailService;

    @Autowired
    public WallpapersController(WallpapersService wallpapersService,WallpapersDetailService wallpapersDetailService) {
        this.wallpapersService = wallpapersService;
        this.wallpapersDetailService=wallpapersDetailService;
    }

    @PostMapping("/search")
    public ResponseEntity<Message> searchWallpapers(@RequestBody Search searchvalue) {

        Message  message = wallpapersService.getWallpapers(searchvalue);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //添加壁纸
    @PostMapping("/upload")
    public ResponseEntity<Message> registerUser(@RequestParam("userid") int userid,
                                                @RequestParam("wallpapertype") int wallpapertype,
                                                @RequestParam(name = "wallpaperprofile", required = false) String wallpaperprofile,
                                                @RequestParam("file") MultipartFile file) {

        UploadWallpapers uploadWallpapers = new UploadWallpapers(wallpapertype, file, wallpaperprofile, userid);
        System.out.println("userid: " + userid);
        System.out.println("wallpapertype: " + wallpapertype);
        System.out.println("wallpaperprofile: " + wallpaperprofile);
        Message message = wallpapersService.addWallpapers(uploadWallpapers);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //获取作品
    @PostMapping("/works")
    public ResponseEntity<Message> showWorks(@RequestBody WallpapersPagination wallpapersPagination) {
        int recordsperpage= wallpapersPagination.getRecordsPerPage();
        Message message = wallpapersService.showWallpapers(wallpapersPagination);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //类型查找壁纸
    @PostMapping("/type")
    public ResponseEntity<Message> findTypeWallpapers(@RequestBody int wallpaperstypetype) {
        System.out.println(wallpaperstypetype);
        Message message = wallpapersService.findTypeWallpapers(Integer.valueOf(wallpaperstypetype) );
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    // 其他请求处理方法...
    @PostMapping("/detail")
    public ResponseEntity<Message> getdetailWallpapers(@RequestBody UseridAndWallpapersid useridAndWallpapersid) {
        Message message = wallpapersDetailService.getWallpapersDetail(useridAndWallpapersid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
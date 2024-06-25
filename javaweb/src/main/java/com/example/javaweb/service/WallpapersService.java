package com.example.javaweb.service;

import com.example.javaweb.dao.WallpapersRepository;
import com.example.javaweb.entity.Wallpapers;
import com.example.javaweb.pojo.*;
import com.example.javaweb.utils.FileUtils;
import com.example.javaweb.utils.ReadPNGImageBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class WallpapersService {
    @Value("${wallpapers.path}")
    String url;
    private final WallpapersRepository wallpapersRepository;

    @Autowired
    public WallpapersService(WallpapersRepository wallpapersRepository) {
        this.wallpapersRepository = wallpapersRepository;
    }
//添加壁纸
    @Transactional
    public Message addWallpapers(UploadWallpapers uploadWallpapers) {


        if (FileUtils.getFileExtension(uploadWallpapers.getWallpaper()).equals("png")) {
            String type = (WallpaperType.values())[uploadWallpapers.getWallpaperType()].getMsg();
            String path = url + type + "/" + uploadWallpapers.getUserid() + System.currentTimeMillis() + ".png";
            System.out.println(path);
            if (FileUtils.saveMultipartFileAsPng(uploadWallpapers.getWallpaper(), path)) {
                try {
                    Date date = java.sql.Date.valueOf(LocalDate.now());
                    Wallpapers wallpapers = new Wallpapers();
                    wallpapers.setWallpaperType(uploadWallpapers.getWallpaperType());
                    wallpapers.setUserid(uploadWallpapers.getUserid());
                    wallpapers.setWallpaperProfile(uploadWallpapers.getWallpaperProfile());
                    wallpapers.setWallpaperAddress(path);
                    wallpapers.setLove(0);
                    wallpapers.setCollect(0);
                    wallpapers.setIsRemove(0);
                    wallpapers.setUploadTime(date);
                    wallpapers.setLastUpdate(date);
                    wallpapersRepository.save(wallpapers);
                    Message message = new Message();
                    message.setStatus("0");
                    message.setMessage("上传成功");
                    return message;

                } catch (Exception e) {
                    throw new RuntimeException(e);

                }

            }
            Message message = new Message();
            message.setStatus("1");
            message.setMessage("上传失败");
            return message;
        }
        Message message = new Message();
        message.setStatus("1");
        message.setMessage("上传失败");
        return message;
    }
//搜索壁纸
    public Message getWallpapers(Search search) {
       String value= search.getSearchvalue();
       int page =search.getPage();
      int recordsperpage= search.getRecordsperpage();
        int  offset=(page - 1) * recordsperpage;
       int total  =wallpapersRepository.findWallpapersTotal(value);
        List<Wallpapers> wallpapersList = wallpapersRepository.findWallpapers(value,offset,recordsperpage);
        for (Wallpapers item : wallpapersList) {
            System.out.println(item);
            String imageBase64 = ReadPNGImageBase64.ReadPngImageBase64(item.getWallpaperAddress());
            item.setWallpaperAddress(imageBase64);

        }
        Message message = new Message();
        message.setTotal(total);
        message.setStatus("0");
        message.setMessage("获取成功");
        message.setWallpapersList(wallpapersList);

        return message;
    }
//显示作品
public Message showWallpapers(WallpapersPagination wallpapersPagination) {
    int userid= wallpapersPagination.getUserid();
    int page =wallpapersPagination.getPage();
    int recordsperpage= wallpapersPagination.getRecordsPerPage();
    int  offset=(page - 1) * recordsperpage;
    int total  =wallpapersRepository.findworksTotal(userid);
    List<Wallpapers> wallpapersList = wallpapersRepository.showWallpapers(userid,offset,recordsperpage);
    for (Wallpapers item : wallpapersList) {
        String imageBase64 = ReadPNGImageBase64.ReadPngImageBase64(item.getWallpaperAddress());
        item.setWallpaperAddress(imageBase64);
    }
    Message message = new Message();
    message.setStatus("0");
    message.setMessage("获取成功");
    message.setTotal(total);
    message.setWallpapersList(wallpapersList);

    return message;
}
    public Message findTypeWallpapers(int wallpaperType) {
        List<Wallpapers> wallpapersList = wallpapersRepository.findTypeWallpapers(wallpaperType);
        for (Wallpapers item : wallpapersList) {
            String imageBase64 = ReadPNGImageBase64.ReadPngImageBase64(item.getWallpaperAddress());
            item.setWallpaperAddress(imageBase64);
        }
        Message message = new Message();
        message.setStatus("0");
        message.setMessage("获取成功");
        message.setWallpapersList(wallpapersList);

        return message;
    }
}
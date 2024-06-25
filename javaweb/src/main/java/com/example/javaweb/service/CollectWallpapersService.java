package com.example.javaweb.service;

import com.example.javaweb.dao.CollectWallpapersRepository;
import com.example.javaweb.dao.WallpapersRepository;
import com.example.javaweb.entity.Collectwallpapers;
import com.example.javaweb.entity.Wallpapers;
import com.example.javaweb.pojo.Message;
import com.example.javaweb.pojo.UseridAndWallpapersid;
import com.example.javaweb.pojo.WallpapersPagination;
import com.example.javaweb.utils.ReadPNGImageBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CollectWallpapersService {
    private final CollectWallpapersRepository collectWallpapersRepository;
    private final WallpapersRepository wallpapersRepository;

    @Autowired
    public CollectWallpapersService(CollectWallpapersRepository collectWallpapersRepository, WallpapersRepository wallpapersRepository) {
        this.collectWallpapersRepository = collectWallpapersRepository;
        this.wallpapersRepository = wallpapersRepository;
    }

    //添加收藏
    @Transactional
    public Message addCollect(UseridAndWallpapersid useridAndWallpapersid) {
        try {
            collectWallpapersRepository.save(new Collectwallpapers(useridAndWallpapersid.getWallpapersid(), useridAndWallpapersid.getUserid(), java.sql.Date.valueOf(LocalDate.now())));
            wallpapersRepository.updateAddCollectWallpapers(useridAndWallpapersid.getWallpapersid());
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("历史添加成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    //取消收藏壁纸
    @Transactional
    public Message deleteCollect(UseridAndWallpapersid useridAndWallpapersid) {
        try {
            collectWallpapersRepository.deleteCollectWallpapers(useridAndWallpapersid.getUserid(), useridAndWallpapersid.getWallpapersid());
            wallpapersRepository.updateSubCollectWallpapers(useridAndWallpapersid.getWallpapersid());
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("取消收藏成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

    //查找收藏壁纸
    public Message findCollect(WallpapersPagination wallpapersPagination) {
        try {
         int userId=  wallpapersPagination.getUserid();
         int recordsperpage=wallpapersPagination.getRecordsPerPage();
         int page=wallpapersPagination.getPage();
          int  offset=(page - 1) * recordsperpage;

            List<Wallpapers> collectList = wallpapersRepository.findCollectWallpapers(userId,offset,recordsperpage);
            for (Wallpapers item : collectList) {
            String  url= item.getWallpaperAddress();
            item.setWallpaperAddress(ReadPNGImageBase64.ReadPngImageBase64(url));
            }
            Message message = new Message();
            message.setWallpapersList(collectList);
            message.setStatus("0");
            message.setMessage("获取收藏壁纸成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取收藏壁纸条数
    public Message getCollecttotal(int userId) {
        try {
            int collecttotal = collectWallpapersRepository.getcollectWallpaperstotal(userId);
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("获取壁纸条数成功");
            message.setTotal(collecttotal);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


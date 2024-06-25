package com.example.javaweb.service;

import com.example.javaweb.dao.CollectWallpapersRepository;
import com.example.javaweb.dao.LoveWallpapersRepository;
import com.example.javaweb.dao.WallpapersRepository;
import com.example.javaweb.entity.Collectwallpapers;
import com.example.javaweb.entity.Lovewallpapers;
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
public class LoveWallpapersService {
    private final LoveWallpapersRepository loveWallpapersRepository;
    private final WallpapersRepository wallpapersRepository;

    @Autowired
    public LoveWallpapersService(LoveWallpapersRepository loveWallpapersRepository, WallpapersRepository wallpapersRepository) {
        this.loveWallpapersRepository = loveWallpapersRepository;
        this.wallpapersRepository = wallpapersRepository;
    }

    //添加喜欢
    @Transactional
    public Message addLove(UseridAndWallpapersid useridAndWallpapersid) {
        try {
            loveWallpapersRepository.save(new Lovewallpapers(useridAndWallpapersid.getWallpapersid(), useridAndWallpapersid.getUserid(), java.sql.Date.valueOf(LocalDate.now())));
            wallpapersRepository.updateAddLoveWallpapers(useridAndWallpapersid.getWallpapersid());
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("喜欢成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    //取消喜欢壁纸
    @Transactional
    public Message deleteLove(UseridAndWallpapersid useridAndWallpapersid) {
        try {
            loveWallpapersRepository.deleteLoveWallpapers(useridAndWallpapersid.getUserid(), useridAndWallpapersid.getWallpapersid());
            wallpapersRepository.updateSubLoveWallpapers(useridAndWallpapersid.getWallpapersid());
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("取消喜欢成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

    //查找喜欢壁纸
    public Message findLove(WallpapersPagination wallpapersPagination) {
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
            message.setMessage("获取喜欢壁纸成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取收藏壁纸条数
    public Message getLovetotal(int userId) {
        try {
            int collecttotal = loveWallpapersRepository.getLoveWallpaperstotal(userId);
            Message message = new Message();
            message.setStatus("0");
            message.setMessage("获取喜欢条数成功");
            message.setTotal(collecttotal);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


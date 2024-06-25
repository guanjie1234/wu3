package com.example.javaweb.service;

import com.example.javaweb.dao.FootprintWallpapersRepository;
import com.example.javaweb.dao.WallpapersRepository;
import com.example.javaweb.entity.Footprintwallpapers;
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
public class FootprintWallpapersService {
    private final FootprintWallpapersRepository footprintWallpapersRepository;
    private final WallpapersRepository wallpapersRepository;

    @Autowired
    public FootprintWallpapersService(FootprintWallpapersRepository footprintWallpapersRepository, WallpapersRepository wallpapersRepository) {
        this.footprintWallpapersRepository = footprintWallpapersRepository;
        this.wallpapersRepository = wallpapersRepository;
    }

    //添加历史
    @Transactional
    public Message addFootprint(UseridAndWallpapersid useridAndWallpapersid) {
            if (footprintWallpapersRepository.getIsFootprint(useridAndWallpapersid.getUserid(), useridAndWallpapersid.getWallpapersid()) != 0) {
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("历史已存在");
                return message;
            } else {
                footprintWallpapersRepository.save(new Footprintwallpapers(useridAndWallpapersid.getWallpapersid(), useridAndWallpapersid.getUserid(), java.sql.Date.valueOf(LocalDate.now())));
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("历史添加成功");
                return message;
            }
        }

        //取消历史壁纸
        @Transactional
        public Message deleteFootprint ( int userId, int wallpapersId){
            try {
                footprintWallpapersRepository.deleteFootprintWallpapers(userId, wallpapersId);
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("取消收藏成功");
                return message;
            } catch (Exception e) {
                throw new RuntimeException(e);

            }


        }

        //查找历史壁纸
        public Message findFootprint (WallpapersPagination wallpapersPagination){
            try {
                int userId = wallpapersPagination.getUserid();
                int recordsperpage = wallpapersPagination.getRecordsPerPage();
                int page = wallpapersPagination.getPage();
                int offset = (page - 1) * recordsperpage;
                int total = footprintWallpapersRepository.getFootprintWallpaperstotal(userId);
                List<Wallpapers> footprintList = wallpapersRepository.findFootprintWallpapers(userId, offset, recordsperpage);
                for (Wallpapers item : footprintList) {
                    String url = item.getWallpaperAddress();
                    item.setWallpaperAddress(ReadPNGImageBase64.ReadPngImageBase64(url));
                }
                Message message = new Message();
                message.setWallpapersList(footprintList);
                message.setTotal(total);
                message.setStatus("0");
                message.setMessage("获取历史壁纸成功");
                return message;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //获取历史壁纸条数
        public Message getFootprinttotal ( int userId){
            try {
                int collecttotal = footprintWallpapersRepository.getFootprintWallpaperstotal(userId);
                Message message = new Message();
                message.setStatus("0");
                message.setMessage("获取历史条数成功");
                message.setTotal(collecttotal);
                return message;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


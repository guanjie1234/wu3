package com.example.javaweb.service;

import com.example.javaweb.dao.CollectWallpapersRepository;
import com.example.javaweb.dao.LoveWallpapersRepository;
import com.example.javaweb.dao.UserRepository;
import com.example.javaweb.dao.WallpapersRepository;
import com.example.javaweb.entity.Wallpapers;
import com.example.javaweb.pojo.Message;
import com.example.javaweb.pojo.UseridAndWallpapersid;
import com.example.javaweb.pojo.WallpapersDetail;
import com.example.javaweb.utils.ReadPNGImageBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WallpapersDetailService {

    private final WallpapersRepository wallpapersRepository;
    private final UserRepository userRepository;
    private final LoveWallpapersRepository loveWallpapersRepository;
    private final CollectWallpapersRepository collectWallpapersRepository;

    @Autowired
    public WallpapersDetailService(WallpapersRepository wallpapersRepository, UserRepository userRepository, LoveWallpapersRepository loveWallpapersRepository, CollectWallpapersRepository collectWallpapersRepository) {
        this.wallpapersRepository = wallpapersRepository;
        this.userRepository = userRepository;
        this.loveWallpapersRepository = loveWallpapersRepository;
        this.collectWallpapersRepository = collectWallpapersRepository;

    }

    public Message getWallpapersDetail(UseridAndWallpapersid useridAndWallpapersid) {
        try {
            WallpapersDetail wallpapersDetail = new WallpapersDetail();
            Wallpapers wallpapers = wallpapersRepository.findById(useridAndWallpapersid.getWallpapersid()).get();
            int wallpaperType = wallpapers.getWallpaperType();
            String wallpaperProfile = wallpapers.getWallpaperProfile();
            String wallpaperAddress = ReadPNGImageBase64.ReadPngImageBase64(wallpapers.getWallpaperAddress());
            int love = wallpapers.getLove();
            int collect = wallpapers.getCollect();
            int userId = wallpapers.getUserid();
            String nikename = userRepository.findUserById(userId).getNikename();
            String url = "D:/lingdongwallpaper/images/Picture/";
            String vurl = url + userRepository.findUserById(userId).getAvatar();
            String avatar = ReadPNGImageBase64.ReadPngImageBase64(vurl);
            int islove = loveWallpapersRepository.getIsLoveWallpapers(useridAndWallpapersid.getWallpapersid(),useridAndWallpapersid.getUserid());
            int iscollect = collectWallpapersRepository.getIsCollectWallpaper(useridAndWallpapersid.getWallpapersid(),useridAndWallpapersid.getUserid());
            wallpapersDetail.setNikename(nikename);
            wallpapersDetail.setWallpaperType(wallpaperType);
            wallpapersDetail.setWallpaperAddress(wallpaperAddress);
            wallpapersDetail.setWallpaperProfile(wallpaperProfile);
            wallpapersDetail.setCollect(collect);
            wallpapersDetail.setAvatar(avatar);
            wallpapersDetail.setLove(love);
            wallpapersDetail.setIscollect(iscollect);
            wallpapersDetail.setIslove(islove);
            wallpapersDetail.setUserid(userId);
            Message message = new Message();
            message.setWallpapersDetail(wallpapersDetail);
            message.setStatus("0");
            message.setMessage("获取壁纸详细成功");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

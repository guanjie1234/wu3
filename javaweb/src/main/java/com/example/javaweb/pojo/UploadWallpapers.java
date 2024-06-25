package com.example.javaweb.pojo;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class UploadWallpapers {
    /**
     * 壁纸类型
     */

    private Integer wallpaperType;

    /**
     * 壁纸路径
     */

    private MultipartFile wallpaper;

    /**
     * 壁纸简介
     */

    private String wallpaperProfile;
    /**
     * 作者
     */
    private Integer userid;

    public Integer getWallpaperType() {
        return wallpaperType;
    }

    public void setWallpaperType(Integer wallpaperType) {
        this.wallpaperType = wallpaperType;
    }

    public MultipartFile getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(MultipartFile wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getWallpaperProfile() {
        return wallpaperProfile;
    }

    public void setWallpaperProfile(String wallpaperProfile) {
        this.wallpaperProfile = wallpaperProfile;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public UploadWallpapers(Integer wallpaperType, MultipartFile wallpaper, String wallpaperProfile, Integer userid) {
        this.wallpaperType = wallpaperType;
        this.wallpaper = wallpaper;
        this.wallpaperProfile = wallpaperProfile;
        this.userid = userid;
    }

    public UploadWallpapers() {
    }
}

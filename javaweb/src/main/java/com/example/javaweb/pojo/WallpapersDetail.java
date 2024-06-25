package com.example.javaweb.pojo;

public class WallpapersDetail {
    private int wallpaperType;
    private String wallpaperAddress;

    public WallpapersDetail() {
    }

    private String wallpaperProfile;
    private int love;
    private int collect;
    private int userid;
    private String avatar;
    private String nikename;

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    private int islove;
    private int iscollect;

    public int getWallpaperType() {
        return wallpaperType;
    }

    public void setWallpaperType(int wallpaperType) {
        this.wallpaperType = wallpaperType;
    }

    public String getWallpaperAddress() {
        return wallpaperAddress;
    }

    public void setWallpaperAddress(String wallpaperAddress) {
        this.wallpaperAddress = wallpaperAddress;
    }

    public String getWallpaperProfile() {
        return wallpaperProfile;
    }

    public void setWallpaperProfile(String wallpaperProfile) {
        this.wallpaperProfile = wallpaperProfile;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIslove() {
        return islove;
    }

    public void setIslove(int islove) {
        this.islove = islove;
    }

    public int getIscollect() {
        return iscollect;
    }

    public void setIscollect(int iscollect) {
        this.iscollect = iscollect;
    }

    public WallpapersDetail(int wallpaperType, String wallpaperAddress, String wallpaperProfile, int love, int collect, int userid, String avatar, int islove, int iscollect) {
        this.wallpaperType = wallpaperType;
        this.wallpaperAddress = wallpaperAddress;
        this.wallpaperProfile = wallpaperProfile;
        this.love = love;
        this.collect = collect;
        this.userid = userid;
        this.avatar = avatar;
        this.islove = islove;
        this.iscollect = iscollect;
    }
}

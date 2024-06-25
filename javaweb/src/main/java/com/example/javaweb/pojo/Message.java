package com.example.javaweb.pojo;

import com.example.javaweb.entity.User;
import com.example.javaweb.entity.Wallpapers;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // 排除null值
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) // 自动检测所有字段（包括private）
public class Message {
    private String status;
    private String message;
    private User userdata;
    private int total;
    private List<Wallpapers> wallpapersList;
  private WallpapersDetail  wallpapersDetail;

    public WallpapersDetail getWallpapersDetail() {
        return wallpapersDetail;
    }

    public void setWallpapersDetail(WallpapersDetail wallpapersDetail) {
        this.wallpapersDetail = wallpapersDetail;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserdata() {
        return userdata;
    }

    public void setUserdata(User userdata) {
        this.userdata = userdata;
    }

    public List<Wallpapers> getWallpapersList() {
        return wallpapersList;
    }

    public void setWallpapersList(List<Wallpapers> wallpapersList) {
        this.wallpapersList = wallpapersList;
    }
}
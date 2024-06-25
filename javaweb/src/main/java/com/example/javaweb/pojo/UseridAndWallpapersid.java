package com.example.javaweb.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // 排除null值
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UseridAndWallpapersid {
  private int  userid;
  private int wallpapersid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getWallpapersid() {
        return wallpapersid;
    }

    public void setWallpapersid(int wallpapersid) {
        this.wallpapersid = wallpapersid;
    }

    public UseridAndWallpapersid(int userid, int wallpapersid) {
        this.userid = userid;
        this.wallpapersid = wallpapersid;
    }
}

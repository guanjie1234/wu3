package com.example.javaweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * &#064;description  壁纸表
 * @author BEJSON
 * @date 2024-03-25
 */
@Entity
@Data
@Table(name="wallpapers")
public class Wallpapers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
     * id
     */
    @Column(name="id")
    private Integer id;

    /**
     * 壁纸类型
     */
    @Column(name="wallpapertype")
    private Integer wallpaperType;

    /**
     * 壁纸路径
     */
    @Column(name="wallpaperaddress")
    private String wallpaperAddress;

    /**
     * 壁纸简介
     */
    @Column(name="wallpaperprofile")
    private String wallpaperProfile;

    /**
     * 喜欢人数
     */
    @Column(name="love")
    private Integer love;
    /**
     * 收藏人数
     */
    @Column(name="collect")
    private Integer collect;
    /**
     * 最后修改日期
     */
    @Column(name="lastupdate")
    private Date lastUpdate;

    /**
     * 上传日期
     */
    @Column(name="uploadtime")
    private Date uploadTime;

    /**
     * 是否删除
     */
    @Column(name="isremove")
    private Integer isRemove;

    /**
     * 删除日期
     */
    @Column(name="lastremovetime")
    private Date lastRemoveTime;

    /**
     * 作者
     */
    @Column(name="userid")
    private Integer userid;

    public Wallpapers(Integer wallpaperType, String wallpaperAddress, String wallpaperProfile, Integer love, Date lastUpdate, Date uploadTime, Integer isRemove, Date lastRemoveTime, Integer userid) {
        this.wallpaperType = wallpaperType;
        this.wallpaperAddress = wallpaperAddress;
        this.wallpaperProfile = wallpaperProfile;
        this.love = love;
        this.lastUpdate = lastUpdate;
        this.uploadTime = uploadTime;
        this.isRemove = isRemove;
        this.lastRemoveTime = lastRemoveTime;
        this.userid = userid;
    }

    public Wallpapers() {
    }
}
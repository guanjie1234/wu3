package com.example.javaweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name="collectwallpapers")
public class Collectwallpapers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
     * id
     */
    @Column(name="id")
    private Integer id;

    /**
     * 壁纸id
     */
    @Column(name="wallpapers_id")
    private Integer wallpapersId;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private Integer userId;

    /**
     * 收藏日期
     */
    @Column(name="created_at")
    private Date createdAt;

    public Collectwallpapers() {
    }

    public Collectwallpapers(Integer wallpapersId, Integer userId, Date createdAt) {
        this.wallpapersId = wallpapersId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
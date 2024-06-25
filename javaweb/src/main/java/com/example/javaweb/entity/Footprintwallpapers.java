package com.example.javaweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name="footprintwallpapers")
public class Footprintwallpapers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
     * id
     */
    @Column(name="id")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private Integer userId;

    /**
     * 壁纸id
     */
    @Column(name="wallpapers_id")
    private Integer wallpapersId;

    /**
     * 创建时间
     */
    @Column(name="created_at")
    private Date createdAt;
    

    public Footprintwallpapers( Integer wallpapersId, Integer userId,Date createdAt) {
        this.userId = userId;
        this.wallpapersId = wallpapersId;
        this.createdAt = createdAt;
    }
}
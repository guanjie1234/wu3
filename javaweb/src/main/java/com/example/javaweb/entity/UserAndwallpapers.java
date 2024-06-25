package com.example.javaweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="userandwallpapers")
public class UserAndwallpapers implements Serializable {

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
    @Column(name="wallpapers")
    private Integer wallpapers;

    public UserAndwallpapers() {
    }

}
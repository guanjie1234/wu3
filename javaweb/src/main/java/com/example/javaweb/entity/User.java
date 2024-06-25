package com.example.javaweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @description 用户表
 * @author BEJSON
 * @date 2024-03-25
 */
@Entity
@Data
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
     * 用户id
     */
    @Column(name="id")
    private Integer id;

    /**
     * 账号
     */
    @Column(name="account")
    private String account;

    /**
     * 密码
     */
    @Column(name="password")
    private String password;

    /**
     * 昵称
     */
    @Column(name="nikename")
    private String nikename;

    /**
     * 头像
     */
    @Column(name="avatar")
    private String avatar;

    /**
     * 性别
     */
    @Column(name="sex")
    private Integer sex;

    /**
     * 个性化签名
     */
    @Column(name="personaprofile")
    private String personaProfile;

    /**
     * 状态
     */
    @Column(name="status")
    private Integer status;

    /**
     * 最后登录时间
     */
    @Column(name="lastlogintime")
    private Date lastLoginTime;

    /**
     * 注册时间
     */
    @Column(name="registrationtime")
    private Date registrationTime;

    /**
     * 是否注销
     */
    @Column(name="islogout")
    private Integer isLogout;

    /**
     * 注销时间
     */
    @Column(name="logouttime")
    private Date logouttime;

    public User() {
    }

}
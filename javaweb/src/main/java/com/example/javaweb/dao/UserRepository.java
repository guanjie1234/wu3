package com.example.javaweb.dao;

import com.example.javaweb.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface UserRepository extends JpaRepository<User, Integer> {
    //根据ID查找用户
    @Query(value = "select * from user where id=?1", nativeQuery = true)
    User findUserById(Integer Id);

    //根据用户名查找用户
    @Query(value = "select * from user where account=?1", nativeQuery = true)
    User findUser(String user);

    //添加用户
    @Transactional
    @Modifying
    @Query(value = "insert into user(account,password,nikename,status,registrationtime,lastlogintime,avatar) values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void addUser(String userAccount, String userPassword, String userName, int status, Date enrollTime, Date loginTime, String shippingAddress);

    //用户注册
    @Transactional
    @Modifying
    @Query(value = "insert into user(account,password,nikename,registrationtime,avatar) values(?1,?2,?3,?4,?5)", nativeQuery = true)
    void resisterUser(String userAccount, String userPassword, String userName, Date enrollTime, String shippingAddress);


    //修改登录状态
    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET status = ?1 WHERE id = ?2" , nativeQuery = true)
    void updateLoginStatus(Long id, String status);

    //用户注销
    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET islogout = ?1 WHERE id = ?2", nativeQuery = true)
    void updateIsLogoutStatus(Long id, String isLogout);

    //用户修改密码
    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET password = ?1 WHERE id = ?2", nativeQuery = true)
    void updateUserPassword(Long id, String isLogout);

    //修改用户信息
    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET nikename = ?1 ,avatar = ?2,sex = ?3,personaProfile = ?4 WHERE id = ?5", nativeQuery = true)
    void updateUserInformation(String nikename, String avatar, Integer sex, String personaProfile);
}

package com.example.javaweb.dao;

import com.example.javaweb.entity.Collectwallpapers;
import com.example.javaweb.entity.Lovewallpapers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoveWallpapersRepository extends JpaRepository<Lovewallpapers, Integer> {



    //取消喜欢
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lovewallpapers WHERE user_id= ?1 AND wallpapers_id ", nativeQuery = true)
    void deleteLoveWallpapers(Integer userId, Integer wallpapersId );

    //获取喜欢条数
    @Query(value = "SELECT COUNT(*) FROM lovewallpapers WHERE user_id= ?1 ", nativeQuery = true)
    int getLoveWallpaperstotal(Integer userId);
    //是否喜欢
    @Query(value = "SELECT COUNT(*) FROM lovewallpapers WHERE wallpapers_id= ?1 AND user_id=?2 ", nativeQuery = true)
    int getIsLoveWallpapers(Integer wallpapersId,Integer userId);
}

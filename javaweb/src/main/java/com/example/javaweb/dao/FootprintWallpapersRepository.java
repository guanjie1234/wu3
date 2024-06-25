package com.example.javaweb.dao;

import com.example.javaweb.entity.Collectwallpapers;
import com.example.javaweb.entity.Footprintwallpapers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FootprintWallpapersRepository extends JpaRepository<Footprintwallpapers, Integer> {
    //添加历史
    Footprintwallpapers save(Footprintwallpapers Footprintwallpapers);

    //取消历史
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM footprintwallpapers WHERE user_id= ?1 AND wallpapers_id =?2", nativeQuery = true)
    void deleteFootprintWallpapers(Integer userId, Integer wallpapersId );

    //获取历史条数
    @Query(value = "SELECT COUNT(*) FROM footprintwallpapers WHERE user_id= ?1 ", nativeQuery = true)
    int getFootprintWallpaperstotal(Integer userId);
    @Query(value = "SELECT COUNT(*) FROM footprintwallpapers WHERE user_id= ?1 AND wallpapers_id =?2", nativeQuery = true)
    int getIsFootprint(Integer userId, Integer wallpapersId);
}

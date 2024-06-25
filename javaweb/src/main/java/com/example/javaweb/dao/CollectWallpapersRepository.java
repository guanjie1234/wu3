package com.example.javaweb.dao;

import com.example.javaweb.entity.Collectwallpapers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CollectWallpapersRepository extends JpaRepository<Collectwallpapers, Integer> {
    //添加收藏
    Collectwallpapers save(Collectwallpapers collectwallpapers);

    //取消收藏
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collectwallpapers WHERE user_id= ?1 AND wallpapers_id ", nativeQuery = true)
    void deleteCollectWallpapers(Integer userId, Integer wallpapersId );

    //获取收藏条数
    @Query(value = "SELECT COUNT(*) FROM collectwallpapers WHERE user_id= ?1 ", nativeQuery = true)
    int getcollectWallpaperstotal(Integer userId);
    //是否收藏
    @Query(value = "SELECT COUNT(*) FROM collectwallpapers WHERE wallpapers_id= ?1 AND user_id= ?2", nativeQuery = true)
    int getIsCollectWallpaper(Integer wallpapersId, Integer userId);
}

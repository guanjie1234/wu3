package com.example.javaweb.dao;

import com.example.javaweb.entity.Wallpapers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WallpapersRepository extends JpaRepository<Wallpapers, Integer> {
    //搜索壁纸

    @Query(value = "select * from wallpapers where wallpaperProfile like %?1% LIMIT ?2, ?3", nativeQuery = true)
    List<Wallpapers> findWallpapers(String searchvalue, int offset, int recordsPerPage);
    @Query(value = "select COUNT(*) from wallpapers where wallpaperProfile like %?1% ", nativeQuery = true)
    int findWallpapersTotal(String searchvalue);
//获取作品条数
    @Query(value = "select COUNT(*) from wallpapers where userid = ?1 ", nativeQuery = true)
    int findworksTotal(int userid);
    //添加壁纸
    Wallpapers save(Wallpapers wallpapers);

    //查找收藏壁纸
    @Query(value = "SELECT * FROM wallpapers  WHERE id IN (SELECT wallpapers_id FROM collectwallpapers WHERE user_id = ?1)LIMIT ?2, ?3", nativeQuery = true)
    List<Wallpapers> findCollectWallpapers(int userId, int offset, int recordsPerPage);

    //查找历史壁纸
    @Query(value = "SELECT * FROM wallpapers  WHERE id IN (SELECT wallpapers_id FROM footprintwallpapers WHERE user_id = ?1)LIMIT ?2, ?3", nativeQuery = true)
    List<Wallpapers> findFootprintWallpapers(int userId, int offset, int recordsPerPage);
    //查找作品
    @Query(value = "select * from wallpapers where userid =?1 LIMIT ?2, ?3", nativeQuery = true)
    List<Wallpapers> showWallpapers(int userid,int offset ,int recordsperpage );
    //类型查找壁纸
    @Query(value = "select * from wallpapers where wallpaperType =?1", nativeQuery = true)
    List<Wallpapers> findTypeWallpapers(int type);
    @Modifying
    @Transactional
    @Query(value = "UPDATE wallpapers SET collect=collect+1 where id=?1", nativeQuery = true)
    void updateAddCollectWallpapers(int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE wallpapers SET collect=collect-1 where id=?1", nativeQuery = true)
    void updateSubCollectWallpapers(int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE wallpapers SET love=love+1 where id=?1", nativeQuery = true)
    void updateAddLoveWallpapers(int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE wallpapers SET love=love-1 where id=?1", nativeQuery = true)
    void updateSubLoveWallpapers(int id);

}

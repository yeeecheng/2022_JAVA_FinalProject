package com.plcoding.instagramui.saveplace.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.plcoding.instagramui.saveplace.data.db.entities.StoreItem;

import java.util.List;

@Dao
public interface StoreDao {


    @Query("DELETE FROM store_items WHERE store_item_uid = :uid")
    public void deleteByUid(int uid);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(StoreItem item);

    @Query("SELECT * FROM store_items ")
    public List<StoreItem> getAll();

    @Query("SELECT * FROM store_items where store_item_uid = :uid")
    public List<StoreItem> findByUid(int uid);

    @Query("SELECT * FROM store_items where store_item_type = :Type")
    public List<StoreItem> findByType(String Type);

    @Query("SELECT * FROM store_items where store_item_information = :Info")
    public List<StoreItem> findByInfo(String Info);

    @Query("SELECT * FROM store_items where store_item_lng = :lng AND store_item_lat =:lat")
    public List<StoreItem> findByLngLat(Double lng,Double lat);

    @Query("SELECT * FROM store_items where ((store_item_timeStart > store_item_timeEnd) AND ((store_item_timeStart <= :Time OR :Time <=store_item_timeEnd)))OR" +
            "((store_item_timeStart < store_item_timeEnd) AND ((store_item_timeStart <= :Time AND :Time <=store_item_timeEnd)))")
    public  List<StoreItem> findByTime(int Time);


}

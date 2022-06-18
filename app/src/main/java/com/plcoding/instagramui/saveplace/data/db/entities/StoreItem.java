package com.plcoding.instagramui.saveplace.data.db.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "store_items")
public class StoreItem {

    @PrimaryKey
    @ColumnInfo(name="store_item_uid")
    public int uid;
    @ColumnInfo(name="store_item_name")
    public String name;
    @ColumnInfo(name="store_item_type")
    public String type;
    @ColumnInfo(name="store_item_information")
    public String information;
    @ColumnInfo(name="store_item_lng")
    public Double lng;
    @ColumnInfo(name="store_item_lat")
    public Double lat;
    @ColumnInfo(name="store_item_timeStart")
    public int time_start;
    @ColumnInfo(name="store_item_timeEnd")
    public int time_end;

}

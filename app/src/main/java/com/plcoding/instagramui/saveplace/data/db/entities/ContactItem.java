package com.plcoding.instagramui.saveplace.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_items")
public class ContactItem {


    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "contact_item_name")
    public String name;
    @ColumnInfo(name = "contact_item_phone_number")
    public String phoneNumber;

}

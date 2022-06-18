package com.plcoding.instagramui.saveplace.data.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.plcoding.instagramui.saveplace.data.db.entities.ContactItem;

import java.util.List;

@Dao
public interface ContactDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void upsert(ContactItem item);

    @Delete
    public void delete(ContactItem item);

    @Update
    public void update(ContactItem item);

    @Query("SELECT * FROM contact_items WHERE id  > 0")
    public LiveData<List<ContactItem>> getAllContactItems();

    @Query ("SELECT id FROM contact_items WHERE contact_item_name = :name AND id>0")
    public int getIdByName (String name);

    @Query("SELECT contact_item_phone_number FROM contact_items  WHERE id > 0")
    public List<String> getContactPhoneNumber();

    @Query ("SELECT contact_item_name FROM contact_items where id = :id   ")
    public String getItemNameById (int id);

    @Query ("SELECT contact_item_phone_number FROM contact_items where id =:id ")
    public String getItemPhoneNumberById(int id);

}

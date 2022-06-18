package com.plcoding.instagramui.saveplace.data.repository;

import androidx.lifecycle.LiveData;

import com.plcoding.instagramui.saveplace.data.db.ContactDatabase;
import com.plcoding.instagramui.saveplace.data.db.entities.ContactItem;

import java.util.List;

public class ContactRepository {

    private ContactDatabase db;
    public ContactRepository(ContactDatabase database){
        db =database;
    }

    public void upsert(ContactItem item) {
        db.getContactDao().upsert(item);
    }

    public void delete(ContactItem item) {
        db.getContactDao().delete(item);
    }
    public void update(ContactItem item) {
        db.getContactDao().update(item);

    }
    public LiveData<List<ContactItem>> getAllContactItems() {
        return db.getContactDao().getAllContactItems();
    }
    public int getIdByName (String name) {
        return db.getContactDao().getIdByName(name);
    }
    public List<String> getContactPhoneNumber() {
        return db.getContactDao().getContactPhoneNumber();
    }
    public String getItemNameById(int id ) {
        return db.getContactDao().getItemNameById(id);
    }
    public String getItemPhoneNumberById(int id) {
        return db.getContactDao().getItemPhoneNumberById(id);
    }

}
